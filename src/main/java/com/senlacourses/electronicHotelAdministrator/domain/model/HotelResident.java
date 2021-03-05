package com.senlacourses.electronicHotelAdministrator.domain.model;

public record HotelResident(String fullName, int passportNumber) {

  @Override
  public String toString() {
    return "full name - " + fullName + ", passport number - " + passportNumber;
  }
}
