package com.senlaCourses.adPlacementSystem.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * POJO class for announcements.
 */
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Ad implements Serializable {

  @Id
  @GeneratedValue
  private long id;
  @ManyToOne
  private Profile profile;
  @Size(min = 3)
  private String nameOfAd;
  @Size(min = 20)
  private String description;
  @Min(0)
  private int price;
  private LocalDate dateOfCreation = LocalDate.now();
  private boolean isActive = true;
  private boolean isPaid;
  private String city;
  //todo check
  @ElementCollection
  private List<String> comments = new LinkedList<>();
  @NotNull
  private CategoryOfAd category;
}
