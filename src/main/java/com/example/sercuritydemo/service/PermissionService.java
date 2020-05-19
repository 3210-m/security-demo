package com.example.sercuritydemo.service;

import com.example.sercuritydemo.dao.PermissionRepository;
import com.example.sercuritydemo.entity.Permission;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {

  private final PermissionRepository permissionRepository;

  public PermissionService(PermissionRepository permissionRepository) {
    this.permissionRepository = permissionRepository;
  }

  public List<String> getPermissionCodeByUerId(Long userId){
    List<String> codes = permissionRepository.findPermissionCodeByUserId(userId);
    return codes;
  }
}
