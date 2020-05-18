package com.example.sercuritydemo.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

  @RequestMapping(value = "/login-success", produces = {"text/plain;charset=UTF-8"})
  public String loginSuccess(){
    return "登陆成功";
  }

  @PreAuthorize("hasAuthority('a')")  //有a权限才可访问
  @RequestMapping(value = "/r/r1", produces = {"text/plain;charset=UTF-8"})
  public String r1(){
    return "访问资源1";
  }

//  @Secured("ROLE_TELLER")   //需要TELLER角色才能访问
//  @Secured("IS_AUTHENTICATED_ANONYMOUSLY")  //可匿名访问（不登陆）
  @PreAuthorize("hasAuthority('b')")
  @RequestMapping(value = "/r/r2", produces = {"text/plain;charset=UTF-8"})
  public String r2(){
    return "访问资源2";
  }
}
