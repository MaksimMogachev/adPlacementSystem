package com.senlacourses.electronicHotelAdministrator.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "t_role", schema = "eha")
public class Role implements GrantedAuthority {

  @Id
  private long id;
  private String name;
  @Transient
  @ManyToMany(mappedBy = "roles")
  private Set<User> users;

  public Role(long id) {
    this.id = id;
  }

  public Role(long id, String name) {
    this.id = id;
    this.name = name;
  }

  @Override
  public String getAuthority() {
    return getName();
  }
}
