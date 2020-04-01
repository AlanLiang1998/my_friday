package com.gdpu.myfriday2.config;

import com.gdpu.myfriday2.security.MyAuthenticationFailureHandler;
import com.gdpu.myfriday2.security.MyAuthenticationSuccessHandler;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Descriptin TODO
 * @Author AlanLiang
 * Date 2020/3/31 21:05
 * Version 1.0
 **/
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)//启用spring security注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("userDetailServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //禁用拦截除GET方式以外的请求
        http.csrf().disable();
        //开启登录配置
        http.authorizeRequests()
                //放行访问登录页面和静态资源的请求
                .antMatchers("/login.html", "/xadmin/**", "/tretable-lay/**", "/ztree/**", "/js/**", "/static/**").permitAll()
                //其余请求在认证后可访问
                .anyRequest().authenticated();

        //允许表单登录
        http.formLogin()
                //自定义登录页面
                .loginPage("/login.html")
                //自定义登录表单提交路径
                .loginProcessingUrl("/login")
                //认证成功处理
                .successHandler(myAuthenticationSuccessHandler)
                //认证失败处理
                .failureHandler(myAuthenticationFailureHandler);
    }
}
