package com.puhj.rye.controller.api.v1;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.puhj.rye.bo.*;
import com.puhj.rye.common.constant.Permissions;
import com.puhj.rye.common.constant.ResultCode;
import com.puhj.rye.common.exception.HttpException;
import com.puhj.rye.common.utils.JwtUtil;
import com.puhj.rye.common.utils.SubjectUtil;
import com.puhj.rye.dto.LoginDTO;
import com.puhj.rye.dto.PasswordDTO;
import com.puhj.rye.dto.UserDTO;
import com.puhj.rye.entity.User;
import com.puhj.rye.service.*;
import com.puhj.rye.vo.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author puhanjie
 * @since 2022-11-30
 */
@Tag(name = "用户接口", description = "用户操作相关接口")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    private final RoleService roleService;

    private final PostService postService;

    private final DepartmentService departmentService;

    private final DictionaryService dictionaryService;

    private final FileService fileService;

    public UserController(UserService userService,
                          RoleService roleService,
                          PostService postService,
                          DepartmentService departmentService,
                          DictionaryService dictionaryService,
                          FileService fileService) {
        this.userService = userService;
        this.roleService = roleService;
        this.postService = postService;
        this.departmentService = departmentService;
        this.dictionaryService = dictionaryService;
        this.fileService = fileService;
    }

    @Operation(summary = "登陆", description = "用户登陆接口")
    @PostMapping("/login")
    public String login(@RequestBody LoginDTO user) {
        User loginUser = this.userService.getByUsername(user.getUsername());
        String encryptPassword = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());

        if (loginUser == null) {
            throw new HttpException(ResultCode.NOT_FOUND_USER);
        }
        if (!loginUser.getPassword().equals(encryptPassword)) {
            throw new HttpException(ResultCode.PASSWORD_ERROR);
        }
        // 用户状态异常
        if (!"0".equals(loginUser.getUserStatus())) {
            throw new HttpException(ResultCode.USER_STATUS_ERROR);
        }

        return JwtUtil.createToken(loginUser.getId(), loginUser.getUsername());
    }

    @Operation(summary = "新增用户", description = "增加一个用户")
    @PostMapping
    @RequiresPermissions(Permissions.User.ADD)
    public boolean add(@RequestBody UserDTO userDTO) {
        String password = userDTO.getPassword();
        // 加密密码
        userDTO.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        return this.userService.add(userDTO);
    }

    @Operation(summary = "删除用户", description = "根据用户id数组删除用户")
    @DeleteMapping
    @RequiresPermissions(Permissions.User.DELETE)
    public boolean remove(@RequestBody List<Integer> ids) {
        return this.userService.removeByIds(ids);
    }

    @Operation(summary = "编辑用户", description = "编辑用户信息")
    @PutMapping
    @RequiresPermissions(Permissions.User.EDIT)
    public boolean edit(@RequestBody UserDTO userDTO) {
        return this.userService.edit(userDTO);
    }

    @Operation(summary = "获取当前用户", description = "通过token获取当前登陆用户信息")
    @GetMapping("/info")
    @RequiresAuthentication
    public UserBasicInfoVO getInfo() {
        return this.userService.getBasicInfo();
    }

    @Operation(summary = "编辑基本信息", description = "编辑用户基本信息")
    @PutMapping("/info")
    @RequiresAuthentication
    public boolean editInfo(@RequestBody UserDTO userDTO) {
        return this.userService.editBasicInfo(userDTO);
    }

    @Operation(summary = "查询用户列表", description = "分页查询用户列表")
    @Parameters({
            @Parameter(name = "pageNum", description = "分页查询页码"),
            @Parameter(name = "pageSize", description = "每页数据大小"),
            @Parameter(name = "username", description = "用户名"),
            @Parameter(name = "phone", description = "手机"),
            @Parameter(name = "email", description = "邮箱")
    })
    @GetMapping("/list")
    @RequiresPermissions(Permissions.User.VIEW)
    public PageVO<UserInfoVO> list(@RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
                                   @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                   @RequestParam(value = "username", required = false) String username,
                                   @RequestParam(value = "name", required = false) String name,
                                   @RequestParam(value = "phone", required = false) String phone,
                                   @RequestParam(value = "email", required = false) String email) {
        Page<UserInfoVO> page = new Page<>(pageNum, pageSize);
        return this.userService.list(page, username, name, phone, email);
    }

    @Operation(summary = "重置/修改密码", description = "type=1为重置密码,type=2为修改密码")
    @PutMapping("/password")
    @RequiresAuthentication
    public int updatePassword(@RequestBody PasswordDTO passwordDTO) {
        User currentUser = this.userService.getByUsername(SubjectUtil.getSubjectName());
        User user = this.userService.getById(passwordDTO.getUserId());

        // 重置密码校验
        if (passwordDTO.getType() == 1 && !"admin".equals(currentUser.getUsername())) {
            throw new HttpException(ResultCode.NOT_ADMIN);
        }

        // 修改密码校验
        if (passwordDTO.getType() == 2) {
            String currentPassword = DigestUtils.md5DigestAsHex(passwordDTO.getCurrentPassword().getBytes());
            if (!currentPassword.equals(user.getPassword())) {
                throw new HttpException(ResultCode.CURRENT_PASSWORD_ERROR);
            }
        }

        String newPassword = DigestUtils.md5DigestAsHex(passwordDTO.getNewPassword().getBytes());
        return this.userService.updatePassword(new PasswordBO(passwordDTO.getUserId(), newPassword));
    }

    @Operation(summary = "修改头像", description = "修改用户头像")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(content = {@Content(
            mediaType = "multipart/form-data",
            schema = @Schema(type = "object"),
            schemaProperties = {
                    @SchemaProperty(
                            name = "files",
                            schema = @Schema(type = "string", format = "binary")
                    )
            }
    )})
    @PutMapping("/avatar")
    @RequiresAuthentication
    public String modifyAvatar(@RequestBody MultipartFile[] files, HttpServletRequest request) throws IOException {
        User user = this.userService.getByUsername(SubjectUtil.getSubjectName());

        // 删除原有头像文件
        if (StringUtils.hasLength(user.getAvatar())) {
            this.fileService.remove(user.getAvatar(), request);
        }
        FileVO avatar = this.fileService.upload(files, request).get(0);
        return this.userService.modifyAvatar(user, avatar);
    }

    @Operation(summary = "获取选项数据", description = "获取用户表单选项及字典数据")
    @GetMapping("/options")
    @RequiresPermissions(Permissions.User.VIEW)
    public UserOptionsVO getUserOptions() {
        List<DepartmentTreeBO> departments = this.departmentService.getOptions();
        List<PostBO> posts = this.postService.getOptions();
        List<RoleBO> roles = this.roleService.getOptions();
        List<DictionaryBO> sex = this.dictionaryService.getItems("sex");
        List<DictionaryBO> userStatus = this.dictionaryService.getItems("user_status");
        return new UserOptionsVO(departments, posts, roles, sex, userStatus);
    }

}
