package com.senlaCourses.adPlacementSystem.domain.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

/**
 * POJO class for users wishing to place an announcement.
 */
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "profile", schema = "aps")
public class Profile implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @OneToOne(mappedBy = "profile", cascade = CascadeType.ALL)
  private User user;
  @NaturalId
  private String username;
  @Size(min = 6)
  private String phoneNumber;
  @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
  private final Set<Ad> ads = new HashSet<>();
  private int rating;
}
