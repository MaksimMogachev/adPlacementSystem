package com.senlacourses.electronicHotelAdministrator.domain.model;

import java.io.Serial;
import java.io.Serializable;

public record HotelResident(String fullName, int passportNumber) implements Serializable {

  @Serial
  private static final long serialVersionUID = -3712898207284411440L;

  @Override
  public String toString() {
    return "full name - " + fullName + ", passport number - " + passportNumber;
  }
}
