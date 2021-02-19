package com.senlacourses.lecture3.electronicHotelAdministrator;

import java.util.ArrayList;
import java.util.List;

public class ListOfRooms {

  private List<HotelRoom> list = new ArrayList<>();

  public List<HotelRoom> getList() {
    return list;
  }

  public void showCurrentList() {
    for (int i = 0; i < list.size(); i++) {
      System.out.print("Number of room: " + i + "; Residents: ");

      if (list.get(i).getHotelResidents().size() > 0) {
        for (int j = 0; j < list.get(i).getHotelResidents().size(); j++) {
          System.out.print(list.get(i).getHotelResidents().get(j).getFullName() + ", "
              + list.get(i).getHotelResidents().get(j).getPassportNumber() + "; ");
        }
      } else {
        System.out.print("no residents; ");
      }

      System.out.println("Price: " + list.get(i).getPrice() +
          "; Room condition: " + list.get(i).getRoomCondition());
    }
  }

  public void changePrice(int id, int newPrice) {
    list.get(id).setPrice(newPrice);
  }

  public void changeRoomCondition(int id, RoomCondition roomCondition) {
    list.get(id).setRoomCondition(roomCondition);
  }

  public void putInTheRoom(int id, String fullName, int passportNumber) {
      list.get(id).setHotelResident(new HotelResident(fullName, passportNumber));
  }

  public void evictFromTheRoom(int id) {
    list.get(id).setHotelResident(null);
  }

  public void addNewRoom(int price, RoomCondition roomCondition) {
    list.add(new HotelRoom(price, roomCondition));
  }

}
