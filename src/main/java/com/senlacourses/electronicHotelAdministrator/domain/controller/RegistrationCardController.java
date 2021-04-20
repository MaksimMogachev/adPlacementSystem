package com.senlacourses.electronicHotelAdministrator.domain.controller;

import com.senlacourses.electronicHotelAdministrator.domain.controller.interfaces.IRegistrationCardController;
import com.senlacourses.electronicHotelAdministrator.domain.model.criteriaForSorting.OccupiedRoomSortingCriteria;
import com.senlacourses.electronicHotelAdministrator.domain.model.criteriaForSorting.ServiceSortingCriteria;
import com.senlacourses.electronicHotelAdministrator.domain.service.interfaces.IRegistrationCardService;

public class RegistrationCardController implements IRegistrationCardController {

  private final IRegistrationCardService registrationCardService;

  public RegistrationCardController(IRegistrationCardService registrationCardService) {
    this.registrationCardService = registrationCardService;
  }

  @Override
  public void showOccupiedRooms() {
    registrationCardService.showOccupiedRooms();
  }

  @Override
  public void putInTheRoom(int numberOfRoom, String fullNameOfResident, int daysOfStay) {
    registrationCardService.putInTheRoom(numberOfRoom, fullNameOfResident, daysOfStay);
  }

  @Override
  public void putInTheRoom(int numberOfRoom, String fullNameOfResident) {
    registrationCardService.putInTheRoom(numberOfRoom, fullNameOfResident);
  }

  @Override
  public void evictFromTheRoom(int numberOfRoom, int indexOfResidentInRoom) {
    registrationCardService.evictFromTheRoom(numberOfRoom, indexOfResidentInRoom);
  }

  @Override
  public void evictFromTheRoom(int numberOfRoom) {
    registrationCardService.evictFromTheRoom(numberOfRoom);
  }

  @Override
  public void addServiceToOccupiedRoom(int numberOfRoom, String nameOfService) {
    registrationCardService.addServiceToOccupiedRoom(numberOfRoom, nameOfService);
  }

  @Override
  public void showOccupiedRoomsByCriterion(OccupiedRoomSortingCriteria criterion) {
    registrationCardService.showOccupiedRoomsByCriterion(criterion);
  }

  @Override
  public void showNumberOfCurrentResidents() {
    registrationCardService.showNumberOfCurrentResidents();
  }

  @Override
  public void showAmountOfPayment(int numberOfRoom, int daysOfStay) {
    registrationCardService.showAmountOfPayment(numberOfRoom, daysOfStay);
  }

  @Override
  public void showResidentServicesByCriterion(
      String fullName, ServiceSortingCriteria sortingCriteria) {
    registrationCardService.showResidentServicesByCriterion(fullName, sortingCriteria);
  }
}
