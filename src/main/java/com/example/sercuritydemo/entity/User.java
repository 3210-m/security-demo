package com.example.sercuritydemo.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
@Table(name = "t_user")
public class User  {
  @Id
  private Long id;
  @Column(nullable = false)
  private String username;
  @Column(nullable = false)
  private String password;
  @Column(nullable = false)
  private String fullname;
  @Column(columnDefinition = "varchar(11) DEFAULT NULL comment '手机号'")
  private String mobile;

  @ManyToMany(cascade = CascadeType.REMOVE)
  @JoinTable(
      name = "t_user_roles",
      joinColumns = {
          @JoinColumn(name = "role_id", referencedColumnName = "id")
      },
      inverseJoinColumns = {
          @JoinColumn(name = "user_id", referencedColumnName = "id")
      }
  )
  private List<Role> roles;

}
