package com.senlacourses.electronicHotelAdministrator.domain.controller.interfaces;

import com.senlacourses.electronicHotelAdministrator.domain.model.RoomCondition;
import com.senlacourses.electronicHotelAdministrator.domain.service.criteriaForSorting.RoomSortingCriteria;
import org.springframework.http.ResponseEntity;

public interface IHotelRoomController {

  ResponseEntity<?> showAllRooms();

  ResponseEntity<?> addNewRoom(int numberOfRoom, int numberOfStars, int roomCapacity, int price);

  ResponseEntity<?> changeRoomCondition(int numberOfRoom, String roomCondition);

  ResponseEntity<?> changeRoomPrice(int numberOfRoom, int newPrice);

  ResponseEntity<?> showNumberOfFreeRooms();

  ResponseEntity<?> showAllRoomsByCriterion(String criterion);

  ResponseEntity<?> showFreeRoomsByCriterion(String criterion);

  ResponseEntity<?> showRoomsByDate(int year, int month, int dayOfMonth);

  ResponseEntity<?> showLastResidentsOfRoom(int numberOfRoom);

  ResponseEntity<?> showRoomDetails(int numberOfRoom);
}
