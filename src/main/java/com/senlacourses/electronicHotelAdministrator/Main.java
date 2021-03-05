package com.senlacourses.electronicHotelAdministrator;

import com.senlacourses.electronicHotelAdministrator.domain.model.RoomCondition;
import com.senlacourses.electronicHotelAdministrator.domain.model.ServiceAndRoomSortingCriteria;
import com.senlacourses.electronicHotelAdministrator.domain.model.ServiceSortingCriteria;
import com.senlacourses.electronicHotelAdministrator.domain.service.CheckInRegistrationService;
import com.senlacourses.electronicHotelAdministrator.domain.service.HotelResidentService;
import com.senlacourses.electronicHotelAdministrator.domain.service.HotelRoomService;
import com.senlacourses.electronicHotelAdministrator.domain.service.ServiceService;

public class Main {

  public static void main(String[] args) {
    CheckInRegistrationService checkInRegistrationService = new CheckInRegistrationService();
    HotelResidentService hotelResidentService = new HotelResidentService();
    HotelRoomService hotelRoomService = new HotelRoomService();
    ServiceService service = new ServiceService();

    hotelRoomService.addNewRoom(1, 6, 2, 500);
    hotelRoomService.addNewRoom(2, 8, 4, 400);
    hotelRoomService.addNewRoom(3, 2, 6, 300);
    hotelRoomService.addNewRoom(4, 3, 3, 800);
    hotelRoomService.showAllRooms();
    System.out.println();
    hotelResidentService.addNewResident("kakoi-to zhilec", 2204014);
    hotelResidentService.addNewResident("drugoi zhilec", 2204015);
    hotelResidentService.addNewResident("esho odin zhilec", 2204016);

    checkInRegistrationService.putInTheRoom(1, "kakoi-to zhilec", 5);
    checkInRegistrationService.putInTheRoom(2, "drugoi zhilec", 8);
    checkInRegistrationService.putInTheRoom(3, "esho odin zhilec", 3);

    service.addNewService("pomit' pol", 1000);
    service.addNewService("stirka", 5000);
    service.changeServicePrice("pomit' pol", 20000);
    service.showCurrentServices();

    checkInRegistrationService.addServiceToOccupiedRoom(1, "pomit' pol");
    checkInRegistrationService.addServiceToOccupiedRoom(1, "stirka");
    checkInRegistrationService.showOccupiedRooms();
    checkInRegistrationService.showResidentServicesByCriterion("kakoi-to zhilec", ServiceSortingCriteria.DATE);
    checkInRegistrationService.showResidentServicesByCriterion("kakoi-to zhilec", ServiceSortingCriteria.PRICE);
    service.showPricesOfServicesAndRoomsByCriterion(ServiceAndRoomSortingCriteria.PRICE);

    hotelRoomService.changeRoomPrice(1, 800);
    checkInRegistrationService.evictFromTheRoom(1, 0);
    checkInRegistrationService.showOccupiedRooms();
    hotelRoomService.changeRoomCondition(1, RoomCondition.REPAIRED);
    hotelRoomService.showAllRooms();
    hotelRoomService.showLastResidentsOfRoom(1);
    hotelRoomService.showRoomDetails(1);
  }
}