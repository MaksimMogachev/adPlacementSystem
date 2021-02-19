package com.senlacourses.lecture3.electronicHotelAdministrator;

import java.util.ArrayList;
import java.util.List;

public class ListOfRooms {

  private List<HotelRoom> list = new ArrayList<>();

  public List<HotelRoom> getList() {
    return list;
  }

  public void showCurrentList() {
    for (HotelRoom room : list) {
      System.out.print("Number of room: " + room + "; Residents: ");

      for (int j = 0; j < room.getHotelResidents().size(); j++) {
        System.out.print(room.getHotelResidents().get(j).getFullName() + ", "
            + room.getHotelResidents().get(j).getPassportNumber() + "; ");
      }
      System.out.println("Price: " + room.getPrice() +
          "; Room condition: " + room.getRoomCondition());
    }
  }

  public void changePrice(int id, int newPrice) {
    list.get(id).setPrice(newPrice);
  }

  public void changeRoomCondition(int id, RoomCondition roomCondition) {
    list.get(id).setRoomCondition(roomCondition);
  }

  public void putInTheRoom(int id, String fullName, int passportNumber) {
      list.get(id).setHotelResidents(new HotelResident(fullName, passportNumber, id));
  }

  public void evictFromTheRoom(int id) {
    list.get(id).setHotelResidents(null);
  }

  public void addNewRoom(int price, RoomCondition roomCondition) {
    list.add(new HotelRoom(price, roomCondition));
  }

}
