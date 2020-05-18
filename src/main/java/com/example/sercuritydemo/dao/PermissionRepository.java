package com.example.sercuritydemo.dao;

import com.example.sercuritydemo.entity.Permission;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface PermissionRepository extends JpaRepository<Permission,String> {

  @Query(value = "select code from t_permission  where id in(SELECT permissions_id from t_role_permissions where roles_id in (SELECT roles_id from t_user_roles WHERE user_id=?1))", nativeQuery = true)
  List<String> findPermissionCodeByUserId(Long userId);

}
