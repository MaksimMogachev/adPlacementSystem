package com.senlaCourses.adPlacementSystem.domain.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Class for creation Ad.class object.
 */
@Getter
@NoArgsConstructor
public class AdDto {

  private String nameOfAd;
  private String description;
  private int price;
  private String city;
  private String category;
}
