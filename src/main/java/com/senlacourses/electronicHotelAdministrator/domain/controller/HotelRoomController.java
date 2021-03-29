package com.senlacourses.electronicHotelAdministrator.domain.controller;

import com.senlacourses.electronicHotelAdministrator.domain.controller.interfaces.IHotelRoomController;
import com.senlacourses.electronicHotelAdministrator.domain.model.RoomCondition;
import com.senlacourses.electronicHotelAdministrator.domain.model.criteriaForSorting.RoomSortingCriteria;
import com.senlacourses.electronicHotelAdministrator.domain.service.HotelRoomService;
import com.senlacourses.electronicHotelAdministrator.domain.service.interfaces.IHotelRoomService;

public class HotelRoomController implements IHotelRoomController {

  private final IHotelRoomService hotelRoomService;

  public HotelRoomController(
      IHotelRoomService hotelRoomService) {
    this.hotelRoomService = hotelRoomService;
  }

  @Override
  public void showAllRooms() {
    hotelRoomService.showAllRooms();
  }

  @Override
  public void addNewRoom(int numberOfRoom, int numberOfStars, int roomCapacity, int price) {
    hotelRoomService.addNewRoom(numberOfRoom, numberOfStars, roomCapacity, price);
  }

  @Override
  public void changeRoomCondition(int numberOfRoom, RoomCondition roomCondition) {
    hotelRoomService.changeRoomCondition(numberOfRoom, roomCondition);
  }

  @Override
  public void changeRoomPrice(int numberOfRoom, int newPrice) {
    hotelRoomService.changeRoomPrice(numberOfRoom, newPrice);
  }

  @Override
  public void showNumberOfFreeRooms() {
    hotelRoomService.showNumberOfFreeRooms();
  }

  @Override
  public void showAllRoomsByCriterion(RoomSortingCriteria criterion) {
    hotelRoomService.showAllRoomsByCriterion(criterion);
  }

  @Override
  public void showFreeRoomsByCriterion(RoomSortingCriteria criterion) {
    hotelRoomService.showFreeRoomsByCriterion(criterion);
  }

  @Override
  public void showRoomsByDate(int year, int month, int dayOfMonth) {
    hotelRoomService.showRoomsByDate(year, month, dayOfMonth);
  }

  @Override
  public void showLastResidentsOfRoom(int numberOfRoom) {
    hotelRoomService.showLastResidentsOfRoom(numberOfRoom);
  }

  @Override
  public void showRoomDetails(int numberOfRoom) {
    hotelRoomService.showRoomDetails(numberOfRoom);
  }
}
