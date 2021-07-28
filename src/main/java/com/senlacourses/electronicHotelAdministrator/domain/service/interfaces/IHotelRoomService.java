package com.senlacourses.electronicHotelAdministrator.domain.service.interfaces;

import com.senlacourses.electronicHotelAdministrator.domain.dto.request.HotelRoomDto;
import com.senlacourses.electronicHotelAdministrator.domain.model.HotelRoom;
import com.senlacourses.electronicHotelAdministrator.domain.model.RoomCondition;
import com.senlacourses.electronicHotelAdministrator.domain.service.criteriaForSorting.RoomSortingCriteria;

import java.util.List;

public interface IHotelRoomService {

  List<HotelRoom> getAllRooms();

  void addNewRoom(HotelRoomDto hotelRoomDto);

  HotelRoom changeRoomCondition(int numberOfRoom, RoomCondition roomCondition);

  HotelRoom changeRoomPrice(int numberOfRoom, int newPrice);

  String showNumberOfFreeRooms();

  List<HotelRoom> showAllRoomsByCriterion(RoomSortingCriteria criterion);

  List<HotelRoom> showFreeRoomsByCriterion(RoomSortingCriteria criterion);

  List<HotelRoom> showRoomsByDate(int year, int month, int dayOfMonth);

  List<String> showLastResidentsOfRoom(int numberOfRoom);

  String showRoomDetails(int numberOfRoom);
}
