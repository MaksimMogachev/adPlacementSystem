package com.senlaCourses.adPlacementSystem.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Class for data transfer changes of Ad.class object in DB.
 */
@Getter
@AllArgsConstructor
public class AdDtoToChange {

  private final String nameOfAd;
  private final String description;
  private final int price;
  private final String city;
  private final String category;
}
