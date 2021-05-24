package com.senlacourses.electronicHotelAdministrator.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HotelRoom implements Serializable {

  private final int numberOfRoom;
  private int numberOfStars;
  private int roomCapacity;
  private int price;
  private RoomCondition roomCondition;
  private boolean roomIsOccupied;
  private List<String> lastResidents = new ArrayList<>();

  public HotelRoom(int numberOfRoom) {
    this.numberOfRoom = numberOfRoom;
  }

  public HotelRoom(int numberOfRoom, int numberOfStars, int roomCapacity, int price) {
    if (numberOfRoom < 1 || numberOfStars < 0 || roomCapacity < 1 || price < 0) {
      throw new IllegalArgumentException("Incorrect parameters");
    }
    this.numberOfRoom = numberOfRoom;
    this.numberOfStars = numberOfStars;
    this.roomCapacity = roomCapacity;
    this.price = price;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    if (price < 0) {
      throw new IllegalArgumentException("Incorrect parameter");
    }
    this.price = price;
  }

  public RoomCondition getRoomCondition() {
    return roomCondition;
  }

  public void setRoomCondition(RoomCondition roomCondition) {
    this.roomCondition = roomCondition;
  }

  public int getNumberOfRoom() {
    return numberOfRoom;
  }

  public int getNumberOfStars() {
    return numberOfStars;
  }

  public void setNumberOfStars(int numberOfStars) {
    if (numberOfStars < 0) {
      throw new IllegalArgumentException("Incorrect parameter");
    }
    this.numberOfStars = numberOfStars;
  }

  public int getRoomCapacity() {
    return roomCapacity;
  }

  public void setRoomCapacity(int roomCapacity) {
    if (roomCapacity < 1) {
      throw new IllegalArgumentException("Incorrect parameter");
    }
    this.roomCapacity = roomCapacity;
  }

  public boolean isRoomIsOccupied() {
    return roomIsOccupied;
  }

  public void setRoomIsOccupied(boolean roomIsOccupied) {
    this.roomIsOccupied = roomIsOccupied;
  }

  public List<String> getLastResidents() {
    return lastResidents;
  }

  public void setLastResidents(List<String> lastResidents) {
    this.lastResidents = lastResidents;
  }

  @Override
  public String toString() {
    return (" Number of room: "
        + getNumberOfRoom()
        + "; Number of stars: "
        + getNumberOfStars()
        + "; Room capacity: "
        + getRoomCapacity()
        + "; Price: "
        + getPrice()
        + (roomCondition != null ? "; Room condition: " + getRoomCondition() : "")
        + (roomIsOccupied ? "; Room is occupied now" : "; Room is not occupied now"));
  }
}
