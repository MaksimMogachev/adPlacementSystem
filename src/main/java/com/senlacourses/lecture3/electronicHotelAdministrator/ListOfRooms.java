package com.senlacourses.lecture3.electronicHotelAdministrator;

import java.util.ArrayList;
import java.util.List;

public class ListOfRooms {

  private List<HotelRoom> list = new ArrayList<>();

  public List<HotelRoom> getList() {
    return list;
  }

  public void showCurrentRooms() {
    for (HotelRoom hotelRoom : list) {
      System.out.print("Number of room: " + hotelRoom.getNumberOfRoom() + "; Residents: ");

      if (hotelRoom.getHotelResidents().size() > 0) {
        for (int j = 0; j < hotelRoom.getHotelResidents().size(); j++) {
          System.out.print(hotelRoom.getHotelResidents().get(j).getFullName() + ", "
              + hotelRoom.getHotelResidents().get(j).getPassportNumber() + "; ");
        }
      } else {
        System.out.print("no residents; ");
      }

      System.out.println("Price: " + hotelRoom.getPrice() +
          "; Room condition: " + hotelRoom.getRoomCondition());
    }
  }

  public void changePrice(int numberOfRoom, int newPrice) {
    int index = findIndexOfRoom(numberOfRoom);
    list.get(index).setPrice(newPrice);
  }

  public void changeRoomCondition(int numberOfRoom, RoomCondition roomCondition) {
    int index = findIndexOfRoom(numberOfRoom);
    list.get(index).setRoomCondition(roomCondition);
  }

  public void putInTheRoom(int numberOfRoom, String fullName, int passportNumber) {
    int index = findIndexOfRoom(numberOfRoom);
    list.get(index).addHotelResident(new HotelResident(fullName, passportNumber));
  }

  public void evictFromTheRoom(int numberOfRoom, int indexOfResident) {
    int index = findIndexOfRoom(numberOfRoom);
    list.get(index).removeHotelResident(indexOfResident);
  }

  public void evictFromTheRoom(int numberOfRoom) {
    int index = findIndexOfRoom(numberOfRoom);
    for (int i = 0; i < list.get(index).getHotelResidents().size(); i++) {
      list.get(index).removeHotelResident(i);
    }
  }

  public void addNewRoom(int numberOfRoom, int price, RoomCondition roomCondition) {
    if (findIndexOfRoom(numberOfRoom) == -1 && numberOfRoom > 0) {
      list.add(new HotelRoom(numberOfRoom, price, roomCondition));
    }
  }

  private int findIndexOfRoom(int numberOfRoom) {
    int index = -1;
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNumberOfRoom() == numberOfRoom) {
        index = i;
        break;
      }
    }
    return index;
  }

}
