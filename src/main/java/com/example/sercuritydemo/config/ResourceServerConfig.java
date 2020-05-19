package com.example.sercuritydemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;


@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

  private static final String RESOURCE_ID = "res1";



  @Override
  public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
    resources.resourceId(RESOURCE_ID)   //资源ID
//        .tokenServices()    //验证token服务
        .stateless(true);
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/oauth/**").permitAll()
        .antMatchers("/**").access("#oauth2.hasScope('all')")
        .and().authorizeRequests().anyRequest().authenticated()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);    //用token所以关闭session
  }
//
//  /**
//   * 资源令牌解析服务.
//   * @return resourceServerTokenServices
//   */
////  @Bean
////  public ResourceServerTokenServices tokenService(){
////    RemoteTokenServices services = new RemoteTokenServices();
////    services.setCheckTokenEndpointUrl("");
////    services.setClientId("");
////    services.setClientSecret("");
////    return services;
////  }
}