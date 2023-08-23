package com.puhj.rye.controller.api.v1;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.puhj.rye.bo.PasswordBO;
import com.puhj.rye.common.constant.Permissions;
import com.puhj.rye.common.constant.ResultCode;
import com.puhj.rye.common.exception.HttpException;
import com.puhj.rye.common.utils.JwtUtil;
import com.puhj.rye.dto.LoginDTO;
import com.puhj.rye.dto.PasswordDTO;
import com.puhj.rye.dto.UserDTO;
import com.puhj.rye.entity.User;
import com.puhj.rye.service.UserService;
import com.puhj.rye.vo.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.util.DigestUtils;
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

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "登陆", description = "用户登陆接口")
    @PostMapping("/login")
    public TokenVO login(@RequestBody LoginDTO user) {
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

        String token = JwtUtil.createToken(loginUser.getUsername());
        return new TokenVO(token);
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
    public UserInfoVO getInfo() {
        return this.userService.getInfo();
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
    public PageVO<UserListVO> list(@RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
                                   @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                   @RequestParam(value = "username", required = false) String username,
                                   @RequestParam(value = "phone", required = false) String phone,
                                   @RequestParam(value = "email", required = false) String email) {
        Page<UserListVO> page = new Page<>(pageNum, pageSize);
        return this.userService.list(page, username, phone, email);
    }

    @Operation(summary = "重置/修改密码", description = "type=1为重置密码,type=2为修改密码")
    @PutMapping("/password")
    @RequiresAuthentication
    public int updatePassword(@RequestBody PasswordDTO passwordDTO) {
        Subject subject = SecurityUtils.getSubject();
        String token = subject.getPrincipal().toString();
        User currentUser = this.userService.getByUsername(JwtUtil.getTokenInfo(token));
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
    public AvatarVO modifyAvatar(@RequestBody MultipartFile[] files, HttpServletRequest request) throws IOException {
        return this.userService.modifyAvatar(files, request);
    }

}
