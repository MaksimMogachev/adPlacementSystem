package com.senlaCourses.adPlacementSystem.domain.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

/**
 * POJO class for users wishing to place an announcement.
 */
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "profile", schema = "aps")
public class Profile implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @OneToOne(mappedBy = "profile")
  private User user;
  @NaturalId
  private String username;
  private String phoneNumber;
  @OneToMany(mappedBy = "profile")
  private final Set<Ad> ads = new HashSet<>();
  private int rating;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Profile profile = (Profile) o;
    return id == profile.id && rating == profile.rating && username.equals(profile.username)
        && phoneNumber.equals(profile.phoneNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, phoneNumber, rating);
  }
}
