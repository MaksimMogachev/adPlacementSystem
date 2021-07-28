package com.senlacourses.electronicHotelAdministrator.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class RegistrationCardDto {

  private final int hotelRoom;
  private final LocalDate checkInDate;
  private final LocalDate departureDate;
}
