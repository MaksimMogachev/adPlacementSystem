package com.senlacourses.electronicHotelAdministrator.domain.service.interfaces;

import com.senlacourses.electronicHotelAdministrator.domain.model.RoomCondition;
import com.senlacourses.electronicHotelAdministrator.domain.model.criteriaForSorting.RoomSortingCriteria;

public interface IHotelRoomService {

  void showAllRooms();

  void addNewRoom(int numberOfRoom, int numberOfStars, int roomCapacity, int price);

  void changeRoomCondition(int numberOfRoom, RoomCondition roomCondition);

  void changeRoomPrice(int numberOfRoom, int newPrice);

  void showNumberOfFreeRooms();

  void showAllRoomsByCriterion(RoomSortingCriteria criterion);

  void showFreeRoomsByCriterion(RoomSortingCriteria criterion);

  void showRoomsByDate(int year, int month, int dayOfMonth);

  void showLastResidentsOfRoom(int numberOfRoom);

  void showRoomDetails(int numberOfRoom);
}
