package com.senlaCourses.adPlacementSystem.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * Class for creation Profile.class object.
 */
@Getter
@AllArgsConstructor
public class ProfileDto {

  @NotNull
  private final String phoneNumber;
}
