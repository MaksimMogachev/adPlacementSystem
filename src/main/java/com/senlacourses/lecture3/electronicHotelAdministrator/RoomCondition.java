package com.senlacourses.lecture3.electronicHotelAdministrator;

public enum RoomCondition {
  REPAIRED("the room is being repaired"),
  MAINTAINED("the room is being maintained");

  private final String name;

  RoomCondition(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
