package com.senlacourses.electronicHotelAdministrator.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HotelResidentDto {

  private final int passportNumber;
  private final String fullName;
}
