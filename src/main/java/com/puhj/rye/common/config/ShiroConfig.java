package com.puhj.rye.common.config;

import com.puhj.rye.common.auth.AuthFilter;
import com.puhj.rye.common.auth.AuthRealm;
import com.puhj.rye.common.shiro.StatelessDefaultSubjectFactory;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author puhanjie
 * @description Shiro配置类
 * @create 2022/3/19 00:40
 * @modify 2022/3/19 00:40
 */
@Configuration
public class ShiroConfig {

    /*
     * 使用权限注解需要开启AOP拦截
     */
    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        /*
          setUsePrefix(true)用于解决一个奇怪的bug。在引入spring aop的情况下。
          在@Controller注解的类的方法中加入@RequiresRole等shiro注解，会导致该方法无法映射请求，导致返回404。 加入这项配置能解决这个bug
         */
        defaultAdvisorAutoProxyCreator.setUsePrefix(true);
        return defaultAdvisorAutoProxyCreator;
    }

    /*
     * 注入自己的realm
     */
    @Bean
    public AuthRealm userRealm() {
        return new AuthRealm();
    }

    /*
     * 自定义subject工厂
     */
    @Bean
    public DefaultWebSubjectFactory subjectFactory() {
        return new StatelessDefaultSubjectFactory();
    }

    /*
     * 自定义session管理器
     */
    @Bean
    public SessionManager sessionManager() {
        DefaultSessionManager shiroSessionManager = new DefaultSessionManager();
        // 关闭session校验轮询
        shiroSessionManager.setSessionValidationSchedulerEnabled(false);
        return shiroSessionManager;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        // 关闭shiro自带的session
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        // 设置自定义subject工厂
        securityManager.setSubjectFactory(this.subjectFactory());
        // 设置自定义session管理器
        securityManager.setSessionManager(this.sessionManager());
        // 设置自定义realm
        securityManager.setRealm(this.userRealm());
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shirFilter() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(this.securityManager());

        // 注册自定义的过滤器
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("auth", new AuthFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        shiroFilterFactoryBean.setLoginUrl("/api/v1/user/login");
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");
        shiroFilterFactoryBean.setSuccessUrl("/");

        // 设置url拦截所使用的过滤器
        Map<String, String> filterChainDefinitionMap = new HashMap<>();
        filterChainDefinitionMap.put("/api/v1/user/login", "anon");
        filterChainDefinitionMap.put("/swagger-ui/**", "anon"); // Swagger资源放行
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");  // Swagger资源放行
        filterChainDefinitionMap.put("/v3/**", "anon"); // Swagger资源放行
        filterChainDefinitionMap.put("/res/**", "anon");  // 静态资源放行
        filterChainDefinitionMap.put("/**", "auth");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /*
     * 开启权限注解使用
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(this.securityManager());
        return authorizationAttributeSourceAdvisor;
    }

}
