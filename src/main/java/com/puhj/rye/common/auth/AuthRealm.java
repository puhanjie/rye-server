package com.puhj.rye.common.auth;

import com.puhj.rye.common.utils.JwtUtil;
import com.puhj.rye.entity.Permission;
import com.puhj.rye.entity.Role;
import com.puhj.rye.entity.User;
import com.puhj.rye.service.PermissionService;
import com.puhj.rye.service.RoleService;
import com.puhj.rye.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author puhanjie
 * @description 认证Realm
 * @create 2022-3-19
 */
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    /**
     * 添加对自定义的AutoToken类型的支持,否则会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof AuthToken;
    }

    /**
     * 访问授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String token = (String) principalCollection.getPrimaryPrincipal();
        User user = this.userService.getByUsername(JwtUtil.getTokenInfo(token));

        List<Role> roles = this.roleService.getListByUserId(user.getId());
        List<Permission> permissions = this.permissionService.getListByRoles(roles);
        // 无角色或权限,则返回一个未初始化过的SimpleAuthorizationInfo对象
        if (roles == null || permissions == null) {
            return new SimpleAuthorizationInfo();
        }

        List<String> roleList = new ArrayList<>();
        List<String> permissionList = new ArrayList<>();

        for (Role role : roles) {
            roleList.add(role.getName());
        }
        for (Permission permission : permissions) {
            permissionList.add(permission.getName());
        }

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 获取到的用户角色提交给Shiro
        simpleAuthorizationInfo.addRoles(roleList);
        // 获取到的用户权限提交给Shiro
        simpleAuthorizationInfo.addStringPermissions(permissionList);
        return simpleAuthorizationInfo;
    }

    /**
     * 登陆认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        try {
            // 若查询结果为空，则会抛出空指针异常
            this.userService.getByUsername(JwtUtil.getTokenInfo(token));
        } catch (NullPointerException e) {
            // 故意传入Invalid Token让Shiro报登录异常
            return new SimpleAuthenticationInfo(token, "Invalid Token", this.getName());
        }
        return new SimpleAuthenticationInfo(token, token, this.getName());
    }

}
