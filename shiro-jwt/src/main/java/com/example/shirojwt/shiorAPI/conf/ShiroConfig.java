package com.example.shirojwt.shiorAPI.conf;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    //ShiroFilterFactoryBean  3:
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(securityManager);
        /*
         * anon:无需认证
         * authc：必须认证
         * user：必须拥有记住我的功能才能访问
         * perms：必须拥有某个权限
         * role：必须拥有某个角色
         * */
        //        拦截
        Map<String, String> filterMap = new LinkedHashMap<>();
        //权限
        filterMap.put("/user/add", "perms[user:add]");
        filterMap.put("/user/update", "perms[user:update]");
        //拦截方法体
        filterMap.put("/user/*", "authc");

        bean.setFilterChainDefinitionMap(filterMap);

        //设置登录的请求
        bean.setLoginUrl("/toLogin");

        //未授权页面地址
        bean.setUnauthorizedUrl("/401");

        return bean;
    }

    //DefaultWebSecurityManager  2:
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("permissionRealm") PermissionRealm permissionRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联permissionRealm
        securityManager.setRealm(permissionRealm);
        return securityManager;
    }

    //Realm对象  :1
    @Bean
    public PermissionRealm permissionRealm() {

        return new PermissionRealm();
    }

    //整合shiro 和 thymeleaf
    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }

}
