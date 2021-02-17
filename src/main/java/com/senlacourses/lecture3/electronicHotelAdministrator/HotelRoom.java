package com.senlacourses.lecture3.electronicHotelAdministrator;

public class HotelRoom {

  private int price;
  private RoomCondition roomCondition;
  private HotelResident hotelResident;

  public HotelRoom(int price, RoomCondition roomCondition) {
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

  public HotelResident getHotelResident() {
    return hotelResident;
  }

  public void setHotelResident(HotelResident hotelResident) {
    this.hotelResident = hotelResident;
  }
}
