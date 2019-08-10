package com.bu.config;

import com.bu.realm.MyRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 定义shiro过滤器
 */
@Configuration
public class ShiroConfig {

    /*
    自定义shiro认证、授权
     */
    @Bean(name = "myRealm")
    public MyRealm myRealm() {
        return new MyRealm();
    }

    /*
    安全管理器
     */
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm());
        return securityManager;
    }

    /*
    设置过滤规则
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

         /*
        配置拦截器链
        anon  不需要认证
        authc 需要认证
         */
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
        // 配置不被拦截的资源及链接
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/templates/**", "anon");
        filterChainDefinitionMap.put("/user/login", "anon");
        filterChainDefinitionMap.put("/user/insertjob", "anon");

        // 退出过滤器
        filterChainDefinitionMap.put("/logout", "anon");
        //配置需要认证权限的
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        // 默认登录路径
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/success");
        // 没有权限的时候跳转页面
        // shiroFilterFactoryBean.setUnauthorizedUrl("unauth");

        System.out.println("Shiro拦截器工厂类注入成功");
        return shiroFilterFactoryBean;
    }

}
