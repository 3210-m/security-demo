package com.example.sercuritydemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


//  private final UserDetailsService userDetailsService;
//
//  public WebSecurityConfig(
//      @Qualifier("springDetailService") UserDetailsService userDetailsService) {
//    this.userDetailsService = userDetailsService;
//  }
//
  //密码编码器
  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }



  //配置安全拦截机制
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
//        .antMatchers("/r/r1").hasAuthority("a")
//        .antMatchers("/r/r2").hasAuthority("b")
        .antMatchers("/r/**").authenticated()   //所有/r/**的请求都必须认证通过才可以访问
        .anyRequest().permitAll()    //其他的可直接访问
        .and()
        .formLogin()  //  允许表单登陆
        .successForwardUrl("/login-success");    //登陆成功返回到自定义页面地址

  }
}
