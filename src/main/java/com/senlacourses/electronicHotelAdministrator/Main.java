package com.senlacourses.electronicHotelAdministrator;

import com.senlacourses.electronicHotelAdministrator.config.ApplicationContextConfigurator;
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
  }
}
