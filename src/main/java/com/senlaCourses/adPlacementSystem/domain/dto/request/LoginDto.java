package com.senlaCourses.adPlacementSystem.domain.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Class for login.
 */
@Getter
@NoArgsConstructor
public class LoginDto {

  private String username;
  private String password;
}
