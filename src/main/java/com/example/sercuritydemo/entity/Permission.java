package com.example.sercuritydemo.entity;


import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "t_permission")
public class Permission {
  @Id
  private String id;
  @Column(unique = true)
  private String code;
  @Column
  private String description;
  @Column
  private String url;

  @ManyToMany(mappedBy = "permissions")
  private List<Role> roles;
}
