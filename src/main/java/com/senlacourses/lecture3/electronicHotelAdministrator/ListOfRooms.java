package com.senlacourses.lecture3.electronicHotelAdministrator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListOfRooms {

  private List<HotelRoom> list = new ArrayList<>();

  public List<HotelRoom> getList() {
    return list;
  }

  public void showCurrentList() {
    System.out.println(Arrays.toString(list.toArray()));
  }

  public void changePrice(int id, int newPrice) {
    list.get(id).setPrice(newPrice);
  }

  public void changeRoomCondition(int id, RoomCondition roomCondition) {
    list.get(id).setRoomCondition(roomCondition);
  }

  public void putInTheRoom(int id, String fullName, int passportNumber) { // put for some humans
    if (list.get(id).getHotelResident() == null) {
      list.get(id).setHotelResident(new HotelResident(fullName, passportNumber, id));
    }
  }

  public void evictFromTheRoom(int id) {
    list.get(id).setHotelResident(null);
  }

  public void addRoom(int price, RoomCondition roomCondition) {
    list.add(new HotelRoom(price, roomCondition));
  }

}
