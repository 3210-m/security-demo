package com.example.sercuritydemo.entity;


import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "t_role")
public class Role {
  @Id
  private String id;
  @Column(name = "role_name", unique = true)
  private String roleName;
  @Column
  private String description;
  @Column(name = "create_time")
  private Date createTime;
  @Column(name = "update_time")
  private Date updateTime;
  @Column(nullable = false)
  private String status;

  @ManyToMany
  private List<Permission> permissions;

  @ManyToMany(mappedBy = "roles")
  private List<User> users;
}
