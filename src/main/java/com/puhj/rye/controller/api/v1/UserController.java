package com.puhj.rye.controller.api.v1;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.puhj.rye.bo.PasswordBO;
import com.puhj.rye.common.constant.Permissions;
import com.puhj.rye.common.constant.ResultCode;
import com.puhj.rye.common.exception.NotFoundUserException;
import com.puhj.rye.common.exception.PasswordErrorException;
import com.puhj.rye.common.utils.JwtUtil;
import com.puhj.rye.dto.LoginDTO;
import com.puhj.rye.dto.PasswordDTO;
import com.puhj.rye.dto.UserDTO;
import com.puhj.rye.entity.User;
import com.puhj.rye.service.UserService;
import com.puhj.rye.vo.PageVO;
import com.puhj.rye.vo.TokenVO;
import com.puhj.rye.vo.UserInfoVO;
import com.puhj.rye.vo.UserListVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

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

        if (loginUser == null) {
            throw new NotFoundUserException(ResultCode.NOT_FOUND_USER.getCode(), ResultCode.NOT_FOUND_USER.getMessage());
        }
        if (!loginUser.getPassword().equals(user.getPassword())) {
            throw new PasswordErrorException(ResultCode.PASSWORD_ERROR.getCode(), ResultCode.PASSWORD_ERROR.getMessage());
        }

        String token = JwtUtil.createToken(loginUser.getUsername());
        return new TokenVO(token);
    }

    @Operation(summary = "新增用户", description = "增加一个用户")
    @PostMapping
    @RequiresPermissions(value = {Permissions.ADMIN, Permissions.User.ADD}, logical = Logical.OR)
    public boolean add(@RequestBody UserDTO userDTO) {
        return this.userService.add(userDTO);
    }

    @Operation(summary = "删除用户", description = "根据用户id数组删除用户")
    @DeleteMapping
    @RequiresPermissions(value = {Permissions.ADMIN, Permissions.User.DELETE}, logical = Logical.OR)
    public boolean remove(@RequestBody List<Integer> ids) {
        return this.userService.removeByIds(ids);
    }

    @Operation(summary = "修改用户", description = "修改用户信息")
    @PutMapping
    @RequiresPermissions(value = {Permissions.ADMIN, Permissions.User.UPDATE}, logical = Logical.OR)
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
    @GetMapping
    @RequiresPermissions(value = {Permissions.ADMIN, Permissions.User.VIEW}, logical = Logical.OR)
    public PageVO<UserListVO> query(@RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
                                    @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                    @RequestParam(value = "username", required = false) String username,
                                    @RequestParam(value = "phone", required = false) String phone,
                                    @RequestParam(value = "email", required = false) String email) {
        Page<UserListVO> page = new Page<>(pageNum, pageSize);
        return this.userService.query(page, username, phone, email);
    }

    @Operation(summary = "查询所有用户", description = "查询所有用户数据")
    @GetMapping("/list")
    @RequiresPermissions(value = {Permissions.ADMIN, Permissions.User.VIEW}, logical = Logical.OR)
    public List<User> getList() {
        return this.userService.list();
    }

    @Operation(summary = "修改密码", description = "type=1为重置密码,type=2为修改密码")
    @PutMapping("/password")
    @RequiresAuthentication
    public int updatePassword(@RequestBody PasswordDTO passwordDTO) {
        User currUser = this.userService.getById(passwordDTO.getUserId());
        if (currUser == null) {
            throw new NotFoundUserException(ResultCode.NOT_FOUND_USER.getCode(), ResultCode.NOT_FOUND_USER.getMessage());
        }

        // 修改密码
        if (passwordDTO.getType() == 2) {
            if (!passwordDTO.getCurrentPassword().equals(currUser.getPassword())) {
                throw new PasswordErrorException(ResultCode.CURRENT_PASSWORD_ERROR.getCode(), ResultCode.CURRENT_PASSWORD_ERROR.getMessage());
            }
        }
        return this.userService.updatePassword(new PasswordBO(passwordDTO.getUserId(), passwordDTO.getNewPassword()));
    }

}
