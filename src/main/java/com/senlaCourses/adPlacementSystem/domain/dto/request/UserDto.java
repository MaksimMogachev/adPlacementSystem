package com.senlaCourses.adPlacementSystem.domain.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Class for creation User.class object.
 */
@Getter
@NoArgsConstructor
public class UserDto {

  private String username;
  private String email;
  private String password;
}
