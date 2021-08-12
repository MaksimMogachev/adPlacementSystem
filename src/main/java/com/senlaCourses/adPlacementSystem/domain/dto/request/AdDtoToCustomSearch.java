package com.senlaCourses.adPlacementSystem.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * Class for custom search User.class objects is DB.
 */
@Getter
@AllArgsConstructor
public class AdDtoToCustomSearch {

  @NotNull
  private final String nameOfAd;
  private final int minPrice;
  private final int maxPrice;
  private final String city;
  private final String category;
}
