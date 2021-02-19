package com.senlacourses.lecture3.electronicHotelAdministrator;

public class Administrator {

  private final String fullNameOfAdministrator;
  private Services services = new Services();
  private ListOfRooms listOfRooms = new ListOfRooms();

  public Administrator(String fullNameOfAdministrator) {
    this.fullNameOfAdministrator = fullNameOfAdministrator;
  }

  public String getFullNameOfAdministrator() {
    return fullNameOfAdministrator;
  }

  public Services getServices() {
    return services;
  }

  public ListOfRooms getListOfRooms() {
    return listOfRooms;
  }
}
