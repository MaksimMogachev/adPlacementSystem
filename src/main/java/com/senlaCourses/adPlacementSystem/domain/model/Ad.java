package com.senlaCourses.adPlacementSystem.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
@Table(name = "ad", schema = "aps")
public class Ad implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @ManyToOne(cascade = CascadeType.ALL)
  private Profile profile;
  private String nameOfAd;
  private String description;
  private int price;
  //todo check by date
  private final LocalDate dateOfCreation = LocalDate.now();
  private boolean isActive = true;
  private boolean isPaid;
  private String city;
  //todo check
  @ElementCollection
  @CollectionTable(name = "ad_comments", schema = "aps")
  private final List<String> comments = new LinkedList<>();
  @Enumerated(EnumType.STRING)
  private CategoryOfAd category;
}
