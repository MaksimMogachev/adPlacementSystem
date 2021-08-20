package com.senlaCourses.adPlacementSystem.domain.model;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

/**
 * POJO class for users roles.
 */
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "t_role", schema = "aps")
public class Role implements GrantedAuthority {

  @Id
  private long id;
  private String name;
  @Transient
  @ManyToMany(mappedBy = "roles")
  private Set<User> users;

  public Role(long id, String name) {
    this.id = id;
    this.name = name;
  }

  @Override
  public String getAuthority() {
    return name;
  }
}
