package com.senlaCourses.adPlacementSystem.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * Class for creation User.class object.
 */
@Getter
@AllArgsConstructor
public class UserDto {

  @NotNull
  private final String username;
  @NotNull
  private final String password;
}
