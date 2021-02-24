package com.senlacourses.lecture3.electronicHotelAdministrator;

import java.util.ArrayList;
import java.util.List;

public class HotelRoom {

  private final int numberOfRoom;
  private int numberOfStars;
  private int roomCapacity;
  private int price;
  private RoomCondition roomCondition;
  private boolean roomIsOccupied;

  public HotelRoom(int numberOfRoom, int numberOfStars, int roomCapacity, int price) {
    if (numberOfRoom < 1 || numberOfStars < 0 || roomCapacity < 1 || price < 0) {
      throw new UnsupportedOperationException("Incorrect values");
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
    this.numberOfStars = numberOfStars;
  }

  public int getRoomCapacity() {
    return roomCapacity;
  }

  public void setRoomCapacity(int roomCapacity) {
    this.roomCapacity = roomCapacity;
  }

  @Override
  public String toString() {
    return ("Number of room: " + getNumberOfRoom() + "; Number of stars: " + getNumberOfStars()
        + "; Room capacity: " + getRoomCapacity() + "; Price: " + getPrice()
        + "; Room condition: " + getRoomCondition()
        + (roomIsOccupied ? "; Room is occupied now" : "; Room is not occupied now"));
  }

  public boolean isRoomIsOccupied() {
    return roomIsOccupied;
  }

  public void setRoomIsOccupied(boolean roomIsOccupied) {
    this.roomIsOccupied = roomIsOccupied;
  }
}
