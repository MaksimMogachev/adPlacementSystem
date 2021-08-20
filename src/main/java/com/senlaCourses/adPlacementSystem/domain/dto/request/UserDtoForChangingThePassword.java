package com.senlaCourses.adPlacementSystem.domain.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Class for data transfer new passport parameter.
 */
@Getter
@NoArgsConstructor
public class UserDtoForChangingThePassword {

  private String currentPassword;
  private String newPassword;
}
