package com.senlaCourses.adPlacementSystem.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * Class for data transfer new passport parameter.
 */
@Getter
@AllArgsConstructor
public class UserDtoForChangingThePassword {

  @NotNull
  private final String currentPassword;
  @NotNull
  private final String newPassword;
}
