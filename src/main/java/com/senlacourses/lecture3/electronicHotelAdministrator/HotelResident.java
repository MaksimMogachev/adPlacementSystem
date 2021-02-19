package com.senlacourses.lecture3.electronicHotelAdministrator;

public class HotelResident {

  final private String fullName;
  final private int passportNumber;

  public HotelResident(String fullName, int passportNumber) {
    this.fullName = fullName;
    this.passportNumber = passportNumber;
  }

  public String getFullName() {
    return fullName;
  }

  public int getPassportNumber() {
    return passportNumber;
  }
}
