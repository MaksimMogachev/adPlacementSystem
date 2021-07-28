package com.senlacourses.electronicHotelAdministrator.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ServiceDto {

  private final String name;
  private final int price;
}
