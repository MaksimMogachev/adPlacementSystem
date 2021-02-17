package com.senlacourses.lecture3.electronicHotelAdministrator;

public class HotelResident {

  final private String fullName;
  final private int passportNumber;
  private int hotelRoom;

  public HotelResident(String fullName, int passportNumber, int hotelRoom) {
    this.fullName = fullName;
    this.passportNumber = passportNumber;
    this.hotelRoom = hotelRoom;
  }

  public String getFullName() {
    return fullName;
  }

  public int getPassportNumber() {
    return passportNumber;
  }

  public int getHotelRoom() {
    return hotelRoom;
  }

  public void setHotelRoom(int hotelRoom) {
    this.hotelRoom = hotelRoom;
  }
}
