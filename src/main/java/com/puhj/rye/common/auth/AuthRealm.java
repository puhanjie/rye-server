package com.puhj.rye.common.auth;

import com.puhj.rye.common.utils.JwtUtil;
import com.puhj.rye.service.UserService;
import com.puhj.rye.bo.PermissionBO;
import com.puhj.rye.bo.RoleBO;
import com.puhj.rye.vo.UserBasicInfoVO;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

/**
 * @author puhanjie
 * @description 认证Realm
 * @create 2022-3-19
 */
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

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
        UserBasicInfoVO userBasicInfoVO = this.userService.getBasicInfo();

        List<String> rolesStr = userBasicInfoVO.getRoles().stream().map(RoleBO::getCode).toList();
        List<String> permissionsStr = userBasicInfoVO.getPermissions().stream().map(PermissionBO::getCode).toList();

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 获取到的用户角色提交给Shiro
        simpleAuthorizationInfo.addRoles(rolesStr);
        // 获取到的用户权限提交给Shiro
        simpleAuthorizationInfo.addStringPermissions(permissionsStr);
        return simpleAuthorizationInfo;
    }

    /**
     * 登陆认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        try {
            String username = Objects.requireNonNull(JwtUtil.getTokenInfo(token, "username")).asString();
            // 若查询结果为空，则会抛出空指针异常
            this.userService.getByUsername(username);
        } catch (NullPointerException e) {
            // 故意传入Invalid Token让Shiro报登录异常
            return new SimpleAuthenticationInfo(token, "Invalid Token", this.getName());
        }
        return new SimpleAuthenticationInfo(token, token, this.getName());
    }

}
