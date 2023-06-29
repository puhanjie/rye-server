package com.puhj.rye.controller.api.v1;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.puhj.rye.bo.PasswordBO;
import com.puhj.rye.common.constant.Permissions;
import com.puhj.rye.common.constant.ResultCode;
import com.puhj.rye.common.exception.NotFoundUserException;
import com.puhj.rye.common.exception.PasswordErrorException;
import com.puhj.rye.common.utils.JwtUtil;
import com.puhj.rye.dto.*;
import com.puhj.rye.entity.Permission;
import com.puhj.rye.entity.Role;
import com.puhj.rye.entity.User;
import com.puhj.rye.service.PermissionService;
import com.puhj.rye.service.RoleService;
import com.puhj.rye.service.UserService;
import com.puhj.rye.vo.PageVO;
import com.puhj.rye.vo.TokenVO;
import com.puhj.rye.vo.UserInfoVO;
import com.puhj.rye.vo.UserListVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
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
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    private final RoleService roleService;

    private final PermissionService permissionService;

    public UserController(UserService userService, RoleService roleService, PermissionService permissionService) {
        this.userService = userService;
        this.roleService = roleService;
        this.permissionService = permissionService;
    }

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

    @PostMapping
    @RequiresPermissions(value = {Permissions.ADMIN, Permissions.System.User.ADD}, logical = Logical.OR)
    public boolean add(@RequestBody User user) {
        return this.userService.save(user);
    }

    @DeleteMapping
    @RequiresPermissions(value = {Permissions.ADMIN, Permissions.System.User.DELETE}, logical = Logical.OR)
    public boolean remove(@RequestBody IdsDTO idsDTO) {
        return this.userService.removeByIds(idsDTO.getIds());
    }

    @PutMapping
    @RequiresPermissions(value = {Permissions.ADMIN, Permissions.System.User.UPDATE}, logical = Logical.OR)
    public boolean edit(@RequestBody User user) {
        return this.userService.updateById(user);
    }

    @GetMapping
    @RequiresAuthentication
    public UserInfoVO getInfo() {
        Subject subject = SecurityUtils.getSubject();
        String token = subject.getPrincipal().toString();
        User user = this.userService.getByUsername(JwtUtil.getTokenInfo(token));

        List<Role> userRoles = roleService.getListByUserId(user.getId());
        List<Permission> userPermissions = permissionService.getListByRoles(userRoles);

        return new UserInfoVO(user, userRoles, userPermissions);
    }

    @GetMapping("/all")
    @RequiresPermissions(value = {Permissions.ADMIN, Permissions.System.User.VIEW}, logical = Logical.OR)
    public List<UserListVO> getAll() {
        return this.userService.getAll();
    }

    @GetMapping("/list")
    @RequiresPermissions(value = {Permissions.ADMIN, Permissions.System.User.VIEW}, logical = Logical.OR)
    public PageVO<UserListVO> getPageList(UserPageDTO userPageDTO) {
        Page<UserListVO> page = new Page<>(userPageDTO.getPageNum(), userPageDTO.getPageSize());
        return this.userService.getPageList(page, userPageDTO);
    }

    @PutMapping("/password")
    @RequiresAuthentication
    public int updatePwd(@RequestBody UpdatePwdDTO updatePwdDTO) {
        User currUser = this.userService.getById(updatePwdDTO.getUserId());
        if (!updatePwdDTO.getCurrPwd().equals(currUser.getPassword())) {
            throw new PasswordErrorException(ResultCode.CURRPWD_ERROR.getCode(), ResultCode.CURRPWD_ERROR.getMessage());
        }
        return this.userService.updatePwd(new PasswordBO(updatePwdDTO.getUserId(), updatePwdDTO.getNewPwd()));
    }

    @PutMapping("/resetpwd")
    @RequiresAuthentication
    public int resetPwd(@RequestBody ResetPwdDTO resetPwdDTO) {
        User currUser = this.userService.getById(resetPwdDTO.getUserId());
        if (currUser == null) {
            throw new NotFoundUserException(ResultCode.NOT_FOUND_USER.getCode(), ResultCode.NOT_FOUND_USER.getMessage());
        }
        return this.userService.updatePwd(new PasswordBO(resetPwdDTO.getUserId(), resetPwdDTO.getNewPwd()));
    }

}
