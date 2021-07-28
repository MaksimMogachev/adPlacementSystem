package com.senlacourses.electronicHotelAdministrator.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {

  private final String username;
  private final String password;
}
