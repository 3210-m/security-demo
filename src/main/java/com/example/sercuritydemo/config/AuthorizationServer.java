package com.example.sercuritydemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

  private final TokenStore tokenStore;
  private final ClientDetailsService clientDetailsService;
  private final AuthenticationManager authenticationManager;
//  private final AuthorizationCodeServices authorizationCodeServices;

  public AuthorizationServer(TokenStore tokenStore,
      ClientDetailsService clientDetailsService,
      AuthenticationManager authenticationManager) {
    this.tokenStore = tokenStore;
    this.clientDetailsService = clientDetailsService;
    this.authenticationManager = authenticationManager;
//    this.authorizationCodeServices = authorizationCodeServices;
  }


  /**
   * 配置客户端详情信息服务.
   *
   * @param clients .
   * @throws Exception
   */
  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.inMemory()    //TODO 暂时用内存，之后用数据库存
        .withClient("client_1")   //客户端名
        .secret(new BCryptPasswordEncoder().encode("secret"))   //客户端秘钥
        .resourceIds("res1")  //客户端可以访问的资源
        .authorizedGrantTypes("password", "implicit", "refresh_token")   //客户端申请令牌的方式,oauth2.0支持的五种：authorization_code, password, client_credentials, implicit, refresh_token
        .scopes("all")   //允许的授权范围. eg 只允许使用user-service,只读：read
        .autoApprove(false)   //false: 跳转到授权页面
        .redirectUris("http://www.baidu.com");    //用于验证的回调地址

  }

  /**
   * 配置令牌访问端点
   * @param endpoints
   * @throws Exception
   */
  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints.authenticationManager(authenticationManager)    //密码模式所需要的
//        .authorizationCodeServices(authorizationCodeServices)   //授权码模式需要
        .tokenServices(tokenService())  //令牌管理服务
        .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);   //允许 GET、POST 请求获取 token，即访问端点：oauth/token
  }

  /**
   * 令牌端点的安全约束
   * @param security .
   * @throws Exception .
   */
  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    security
        .tokenKeyAccess("permitAll()")      //如果使用非对称加密公钥，不拦截/oauth/token_key
        .checkTokenAccess("permitAll()")    //不拦截检查令牌请求
        .allowFormAuthenticationForClients();   //允许表单认证
  }

  /**
   * 令牌管理服务
   * @return .
   */
  @Bean
  public AuthorizationServerTokenServices tokenService(){
    DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
    defaultTokenServices.setClientDetailsService(clientDetailsService);   //客户端信息服务
    defaultTokenServices.setSupportRefreshToken(true);    //是否产生刷新令牌
    defaultTokenServices.setTokenStore(tokenStore);   //令牌存储策略
    defaultTokenServices.setAccessTokenValiditySeconds(1800);   //令牌默认有效期 30min
    defaultTokenServices.setRefreshTokenValiditySeconds(7200);  //刷新令牌默认有效期 2h
    return defaultTokenServices;
  }
}
