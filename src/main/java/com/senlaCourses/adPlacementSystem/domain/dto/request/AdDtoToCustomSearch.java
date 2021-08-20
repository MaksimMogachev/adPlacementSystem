package com.senlaCourses.adPlacementSystem.domain.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Class for custom search User.class objects is DB.
 */
@Getter
@NoArgsConstructor
public class AdDtoToCustomSearch {

  private String nameOfAd;
  private int minPrice;
  private int maxPrice;
  private String city;
  private String category;
}
