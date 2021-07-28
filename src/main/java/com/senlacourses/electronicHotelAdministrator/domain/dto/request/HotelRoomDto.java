package com.senlacourses.electronicHotelAdministrator.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HotelRoomDto {

  private final int numberOfRoom;
  private final int numberOfStars;
  private final int roomCapacity;
  private final int price;
}
