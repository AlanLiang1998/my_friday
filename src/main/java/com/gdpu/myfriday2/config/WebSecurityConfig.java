package com.gdpu.myfriday2.config;

import com.gdpu.myfriday2.security.MyAccessDeniedHandler;
import com.gdpu.myfriday2.security.MyAuthenticationFailureHandler;
import com.gdpu.myfriday2.security.MyAuthenticationSuccessHandler;
import com.gdpu.myfriday2.security.ValidateCodeFilter;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;
    @Autowired
    private ValidateCodeFilter validateCodeFilter;

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
        //开启登录配置
        http.authorizeRequests()
                //放行访问登录页面、验证码和静态资源的请求
                .antMatchers("/login.html", "/captcha", "/xadmin/**", "/tretable-lay/**", "/ztree/**", "/js/**", "/static/**").permitAll()
                //其余请求在认证后可访问
                .anyRequest().authenticated();

        //添加图形验证码
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                //允许表单登录
                .formLogin()
                //自定义登录页面
                .loginPage("/login.html")
                //自定义登录表单提交路径
                .loginProcessingUrl("/login")
                //认证成功处理
                .successHandler(myAuthenticationSuccessHandler)
                //认证失败处理
                .failureHandler(myAuthenticationFailureHandler)
                .and()
                //记住我
                .rememberMe();

        //自定义退出登录
        http.logout().permitAll()
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/login.html");

        //处理权限异常
        http.exceptionHandling().accessDeniedHandler(myAccessDeniedHandler);

        //禁用拦截除GET方式以外的请求
        http.csrf().disable();
        //解决X-Frame-Options DENY问题
        http.headers().frameOptions().sameOrigin();
    }
}
