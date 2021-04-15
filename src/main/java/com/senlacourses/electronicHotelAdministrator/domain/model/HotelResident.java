package com.senlacourses.electronicHotelAdministrator.domain.model;

import java.io.Serializable;

public record HotelResident(String fullName, int passportNumber) implements Serializable {

  @Override
  public String toString() {
    return "full name - " + fullName + ", passport number - " + passportNumber;
  }
}
