package com.senlaCourses.adPlacementSystem.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * Class for creation Ad.class object.
 */
@Getter
@AllArgsConstructor
public class AdDto {
  @NotNull
  private final String nameOfAd;
  @NotNull
  private final String description;
  private final int price;
  @NotNull
  private final String city;
  @NotNull
  private final String category;
}
