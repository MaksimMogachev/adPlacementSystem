package com.senlacourses.electronicHotelAdministrator.domain.controller.interfaces;

import com.senlacourses.electronicHotelAdministrator.domain.dto.request.HotelRoomDto;
import org.springframework.http.ResponseEntity;

public interface IHotelRoomController {

  ResponseEntity<?> showAllRooms();

  ResponseEntity<?> addNewRoom(HotelRoomDto hotelRoomDto);

  ResponseEntity<?> changeRoomCondition(int numberOfRoom, String roomCondition);

  ResponseEntity<?> changeRoomPrice(int numberOfRoom, String newPrice);

  ResponseEntity<?> showNumberOfFreeRooms();

  ResponseEntity<?> showAllRoomsByCriterion(String criterion);

  ResponseEntity<?> showFreeRoomsByCriterion(String criterion);

  ResponseEntity<?> showRoomsByDate(int year, int month, int dayOfMonth);

  ResponseEntity<?> showLastResidentsOfRoom(int numberOfRoom);

  ResponseEntity<?> showRoomDetails(int numberOfRoom);
}
