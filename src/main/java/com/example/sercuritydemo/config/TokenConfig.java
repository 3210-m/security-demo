package com.example.sercuritydemo.config;

import com.example.sercuritydemo.entity.UserVoDetail;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class TokenConfig {

  /**
   * 令牌存储策略
   *
   * @return
   */
  @Bean
  public TokenStore tokenStore(){
    return new InMemoryTokenStore();
//    return new JwtTokenStore(jwtAccessTokenConverter());
  }

  private JwtAccessTokenConverter jwtAccessTokenConverter(){
    JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
    //TODO 对称类型key
    converter.setSigningKey(new BCryptPasswordEncoder().encode("123456"));
    return converter;
  }

//  @Bean
//  public TokenEnhancer tokenEnhancer() {
//    return (accessToken, authentication) -> {
//      UserVoDetail userDto = (UserVoDetail) authentication.getUserAuthentication().getPrincipal();
//      final Map<String, Object> additionalInfo = new HashMap<>();
//      additionalInfo.put("license", "pwl");
//      additionalInfo.put("userId", userDto.getUserId());
//      additionalInfo.put("username", userDto.getUsername());
//      additionalInfo.put("fullname", userDto.getFullname());
//      ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
//      //设置token的过期时间30分钟
//      Calendar nowTime = Calendar.getInstance();
//      nowTime.add(Calendar.MINUTE, 30);
//      ((DefaultOAuth2AccessToken) accessToken).setExpiration(nowTime.getTime());
//      return accessToken;
//    };
//  }
}
