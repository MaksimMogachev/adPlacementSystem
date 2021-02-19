package com.senlacourses.lecture3.electronicHotelAdministrator;

import java.util.ArrayList;
import java.util.List;

public class HotelRoom {

  private final int numberOfRoom;
  private int price;
  private RoomCondition roomCondition;
  private List<HotelResident> hotelResidents = new ArrayList<>();

  public HotelRoom(int numberOfRoom, int price, RoomCondition roomCondition) {
    this.numberOfRoom = numberOfRoom;
    this.price = price;
    this.roomCondition = roomCondition;
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

  public List<HotelResident> getHotelResidents() {
    return hotelResidents;
  }

  public void addHotelResident(HotelResident hotelResident) {
    this.hotelResidents.add(hotelResident);
  }

  public void removeHotelResident(int index) {
    this.hotelResidents.remove(this.hotelResidents.get(index));
  }

  public int getNumberOfRoom() {
    return numberOfRoom;
  }
}
