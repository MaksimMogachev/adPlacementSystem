package com.senlacourses.electronicHotelAdministrator;

import com.senlacourses.electronicHotelAdministrator.domain.service.HotelResidentService;
import com.senlacourses.electronicHotelAdministrator.domain.service.HotelRoomService;
import com.senlacourses.electronicHotelAdministrator.domain.service.RegistrationCardService;
import com.senlacourses.electronicHotelAdministrator.domain.service.ServiceService;
import com.senlacourses.electronicHotelAdministrator.domain.service.interfaces.IHotelResidentService;
import com.senlacourses.electronicHotelAdministrator.domain.service.interfaces.IHotelRoomService;
import com.senlacourses.electronicHotelAdministrator.domain.service.interfaces.IRegistrationCardService;
import com.senlacourses.electronicHotelAdministrator.domain.service.interfaces.IServiceService;
import com.senlacourses.electronicHotelAdministrator.ui.MenuController;
import com.senlacourses.electronicHotelAdministrator.ui.Menus;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.InvocationTargetException;

public class Main {

  public static void main(String[] args) {
    ApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationContextConfigurator.class);

    IHotelResidentService hotelResidentService = ctx.getBean(HotelResidentService.class);
    IHotelRoomService hotelRoomService = ctx.getBean(HotelRoomService.class);
    IRegistrationCardService registrationCardService = ctx.getBean(RegistrationCardService.class);
    IServiceService serviceService = ctx.getBean(ServiceService.class);

    new Menus(hotelResidentService, hotelRoomService, registrationCardService, serviceService);

    MenuController menuController = new MenuController();
    menuController.run();

//        RegistrationCardService registrationCardService = new RegistrationCardService();
//        HotelResidentService hotelResidentService = new HotelResidentService();
//        HotelRoomService hotelRoomService = new HotelRoomService();
//        ServiceService service = new ServiceService();

//        hotelRoomService.addNewRoom(1, 6, 2, 500);
//        hotelRoomService.addNewRoom(2, 8, 4, 400);
//        hotelRoomService.addNewRoom(3, 2, 6, 300);
//        hotelRoomService.addNewRoom(4, 3, 3, 800);
//        hotelRoomService.showAllRooms();
//        hotelRoomService.showAllRoomsByCriterion(RoomSortingCriteria.PRICE);
//        System.out.println();
//        hotelResidentService.addNewResident("kakoi-to zhilec", 2204014);
//        hotelResidentService.addNewResident("drugoi zhilec", 2204015);
//        hotelResidentService.addNewResident("esho odin zhilec", 2204016);
//
//        registrationCardService.putInTheRoom(1, 2204014, 5);
//        registrationCardService.putInTheRoom(2, 2204015, 8);
//        registrationCardService.putInTheRoom(3, 2204016, 3);
//
//        serviceService.addNewService("pomit' pol", 1000);
//        serviceService.addNewService("stirka", 5000);
//        serviceService.changeServicePrice("pomit' pol", 20000);
//        serviceService.showCurrentServices();
//
//        registrationCardService.addServiceToOccupiedRoom(1, "pomit' pol");
//        registrationCardService.addServiceToOccupiedRoom(1, "stirka");
//        registrationCardService.showOccupiedRooms();
//        registrationCardService
//            .showResidentServicesByCriterion(2204016, ServiceSortingCriteria.DATE);
//        registrationCardService
//            .showResidentServicesByCriterion(2204016, ServiceSortingCriteria.PRICE);
//        serviceService.showPriceOfServicesAndRoomsByCriterion(ServiceAndRoomSortingCriteria.PRICE);
//
//        hotelRoomService.changeRoomPrice(1, 800);
//        registrationCardService.evictFromTheRoom(1, 0);
//        registrationCardService.showOccupiedRooms();
//        hotelRoomService.changeRoomCondition(1, RoomCondition.REPAIRED);
//        hotelRoomService.showAllRooms();
//        hotelRoomService.showLastResidentsOfRoom(1);
//        hotelRoomService.showRoomDetails(1);
  }
}
