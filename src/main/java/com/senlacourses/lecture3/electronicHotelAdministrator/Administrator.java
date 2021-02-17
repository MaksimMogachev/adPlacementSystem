package com.senlacourses.lecture3.electronicHotelAdministrator;

public class Administrator {

  private String fullNameOfAdministrator;
  private Services services = new Services();
  private ListOfRooms listOfRooms = new ListOfRooms();


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
