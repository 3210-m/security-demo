package com.example.sercuritydemo.service;

import com.example.sercuritydemo.dao.UserRepository;
import java.util.List;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SpringDetailService implements UserDetailsService {

  private final UserRepository userRepository;
  private final PermissionService permissionService;

  public SpringDetailService(UserRepository userRepository,
      PermissionService permissionService) {
    this.userRepository = userRepository;
    this.permissionService = permissionService;
  }

  //根据账号查询信息
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    com.example.sercuritydemo.entity.User user = userRepository.findByUsername(username);
    if (user == null) {
      return null;
    }
    List<String> codeByUerId = permissionService.getPermissionCodeByUerId(user.getId());
    String[] code = new String[codeByUerId.size()];
    codeByUerId.toArray(code);
    return User.withUsername(user.getUsername())
        .password(user.getPassword())
        .authorities(code)
        .build();
  }
}
