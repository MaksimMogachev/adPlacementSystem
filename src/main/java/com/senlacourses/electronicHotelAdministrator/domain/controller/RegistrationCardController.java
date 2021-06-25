package com.senlacourses.electronicHotelAdministrator.domain.controller;

import com.senlacourses.electronicHotelAdministrator.domain.controller.interfaces.IRegistrationCardController;
import com.senlacourses.electronicHotelAdministrator.domain.model.criteriaForSorting.OccupiedRoomSortingCriteria;
import com.senlacourses.electronicHotelAdministrator.domain.model.criteriaForSorting.ServiceSortingCriteria;
import com.senlacourses.electronicHotelAdministrator.domain.service.interfaces.IRegistrationCardService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
@Component
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
  public void putInTheRoom(int numberOfRoom, int passportNumber, int daysOfStay) {
    registrationCardService.putInTheRoom(numberOfRoom, passportNumber, daysOfStay);
  }

  @Override
  public void putInTheRoom(int numberOfRoom, int passportNumber) {
    registrationCardService.putInTheRoom(numberOfRoom, passportNumber);
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
          int passportNumber, ServiceSortingCriteria sortingCriteria) {
    registrationCardService.showResidentServicesByCriterion(passportNumber, sortingCriteria);
  }
}
