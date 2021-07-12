package com.senlacourses.electronicHotelAdministrator.ui;

import com.senlacourses.electronicHotelAdministrator.domain.controller.HotelResidentController;
import com.senlacourses.electronicHotelAdministrator.domain.controller.HotelRoomController;
import com.senlacourses.electronicHotelAdministrator.domain.controller.RegistrationCardController;
import com.senlacourses.electronicHotelAdministrator.domain.controller.ServiceController;
import com.senlacourses.electronicHotelAdministrator.domain.controller.interfaces.IHotelResidentController;
import com.senlacourses.electronicHotelAdministrator.domain.controller.interfaces.IHotelRoomController;
import com.senlacourses.electronicHotelAdministrator.domain.controller.interfaces.IRegistrationCardController;
import com.senlacourses.electronicHotelAdministrator.domain.controller.interfaces.IServiceController;
import com.senlacourses.electronicHotelAdministrator.domain.service.interfaces.IHotelResidentService;
import com.senlacourses.electronicHotelAdministrator.domain.service.interfaces.IHotelRoomService;
import com.senlacourses.electronicHotelAdministrator.domain.service.interfaces.IRegistrationCardService;
import com.senlacourses.electronicHotelAdministrator.domain.service.interfaces.IServiceService;
import com.senlacourses.electronicHotelAdministrator.ui.Menu.Builder;
import com.senlacourses.electronicHotelAdministrator.ui.actions.hotelResident.AddNewResidentAction;
import com.senlacourses.electronicHotelAdministrator.ui.actions.hotelResident.RemoveResidentAction;
import com.senlacourses.electronicHotelAdministrator.ui.actions.hotelResident.ShowAllResidentsAction;
import com.senlacourses.electronicHotelAdministrator.ui.actions.hotelRoom.AddNewRoomAction;
import com.senlacourses.electronicHotelAdministrator.ui.actions.hotelRoom.ChangeRoomConditionAction;
import com.senlacourses.electronicHotelAdministrator.ui.actions.hotelRoom.ChangeRoomPriceAction;
import com.senlacourses.electronicHotelAdministrator.ui.actions.hotelRoom.ShowAllRoomsAction;
import com.senlacourses.electronicHotelAdministrator.ui.actions.hotelRoom.ShowAllRoomsByCriterionAction;
import com.senlacourses.electronicHotelAdministrator.ui.actions.hotelRoom.ShowFreeRoomsByCriterionAction;
import com.senlacourses.electronicHotelAdministrator.ui.actions.hotelRoom.ShowLastResidentsOfRoomAction;
import com.senlacourses.electronicHotelAdministrator.ui.actions.hotelRoom.ShowNumberOfFreeRoomsAction;
import com.senlacourses.electronicHotelAdministrator.ui.actions.hotelRoom.ShowRoomDetailsAction;
import com.senlacourses.electronicHotelAdministrator.ui.actions.hotelRoom.ShowRoomsByDateAction;
import com.senlacourses.electronicHotelAdministrator.ui.actions.residentCard.AddServiceToOccupiedRoomAction;
import com.senlacourses.electronicHotelAdministrator.ui.actions.residentCard.EvictFromTheRoomAction;
import com.senlacourses.electronicHotelAdministrator.ui.actions.residentCard.PutInTheRoomActon;
import com.senlacourses.electronicHotelAdministrator.ui.actions.residentCard.ShowAmountOfPayment;
import com.senlacourses.electronicHotelAdministrator.ui.actions.residentCard.ShowNumberOfCurrentResidentsAction;
import com.senlacourses.electronicHotelAdministrator.ui.actions.residentCard.ShowOccupiedRoomByCriterionAction;
import com.senlacourses.electronicHotelAdministrator.ui.actions.residentCard.ShowOccupiedRoomsAction;
import com.senlacourses.electronicHotelAdministrator.ui.actions.residentCard.ShowResidentServicesByCriterion;
import com.senlacourses.electronicHotelAdministrator.ui.actions.service.AddNewServiceAction;
import com.senlacourses.electronicHotelAdministrator.ui.actions.service.ChangeServicePriceAction;
import com.senlacourses.electronicHotelAdministrator.ui.actions.service.ShowCurrentServicesAction;
import com.senlacourses.electronicHotelAdministrator.ui.actions.service.ShowPriceOfServicesAndRoomsByCriterionAction;

import java.util.ArrayList;
import java.util.List;

public class Menus {

  private static IHotelResidentController residentController;
  private static IHotelRoomController roomController;
  private static IRegistrationCardController registrationCardController;
  private static IServiceController serviceController;

  public Menus(
          IHotelResidentService residentService,
          IHotelRoomService roomService,
          IRegistrationCardService registrationCardService,
          IServiceService serviceService) {

    residentController = new HotelResidentController(residentService);
    roomController = new HotelRoomController(roomService);
    registrationCardController = new RegistrationCardController(registrationCardService);
    serviceController = new ServiceController(serviceService);
  }

  public static Menu getRootMenu() {
    String name = "Main menu";
    List<MenuItem> menuItemList = new ArrayList<>();

    menuItemList.add(new MenuItem("1. Menu to adding information", null, getAddingMenu()));
    menuItemList.add(
            new MenuItem("2. Menu to Updating/deleting information", null, getUpdatingMenu()));
    menuItemList.add(new MenuItem("3. Menu to display information", null, getDisplayMenu()));

    return new Builder().name(name).menuItems(menuItemList.toArray(new MenuItem[0])).build();
  }

  public static Menu getAddingMenu() {
    String name = "Add information";
    List<MenuItem> menuItemList = new ArrayList<>();

    menuItemList.add(
            new MenuItem(
                    "1. Put in the room", new PutInTheRoomActon(registrationCardController), null));
    menuItemList.add(
            new MenuItem(
                    "2. Add service to occupied room",
                    new AddServiceToOccupiedRoomAction(registrationCardController),
                    null));
    menuItemList.add(new MenuItem("3. Add new room", new AddNewRoomAction(roomController), null));
    menuItemList.add(
            new MenuItem("4. Add new service", new AddNewServiceAction(serviceController), null));
    menuItemList.add(
            new MenuItem("5. Add new resident", new AddNewResidentAction(residentController), null));
    menuItemList.add(new MenuItem("0. Back to main menu", null, null));

    return new Menu.Builder().name(name).menuItems(menuItemList.toArray(new MenuItem[0])).build();
  }

  public static Menu getUpdatingMenu() {
    String name = "Update/delete information";
    List<MenuItem> menuItemList = new ArrayList<>();

    menuItemList.add(
            new MenuItem(
                    "1. Remove resident from database",
                    new RemoveResidentAction(residentController),
                    null));
    menuItemList.add(
            new MenuItem(
                    "2. Change room condition", new ChangeRoomConditionAction(roomController), null));
    menuItemList.add(
            new MenuItem("3. Change room price", new ChangeRoomPriceAction(roomController), null));
    menuItemList.add(
            new MenuItem(
                    "4. Change service price", new ChangeServicePriceAction(serviceController), null));
    menuItemList.add(
            new MenuItem(
                    "5. Evict resident from the room",
                    new EvictFromTheRoomAction(registrationCardController),
                    null));
    menuItemList.add(new MenuItem("0. Back to main menu", null, null));

    return new Menu.Builder().name(name).menuItems(menuItemList.toArray(new MenuItem[0])).build();
  }

  public static Menu getDisplayMenu() {
    String name = "Show information";
    List<MenuItem> menuItemList = new ArrayList<>();

    menuItemList.add(
            new MenuItem(
                    "1. Show all residents", new ShowAllResidentsAction(residentController), null));
    menuItemList.add(
            new MenuItem("2. Show all rooms", new ShowAllRoomsAction(roomController), null));
    menuItemList.add(
            new MenuItem(
                    "3. Show all rooms by criterion",
                    new ShowAllRoomsByCriterionAction(roomController),
                    null));
    menuItemList.add(
            new MenuItem(
                    "4. Show all free rooms by criterion",
                    new ShowFreeRoomsByCriterionAction(roomController),
                    null));
    menuItemList.add(
            new MenuItem(
                    "5. Show last residents of room",
                    new ShowLastResidentsOfRoomAction(roomController),
                    null));
    menuItemList.add(
            new MenuItem(
                    "6. Show number of free rooms", new ShowNumberOfFreeRoomsAction(roomController), null));
    menuItemList.add(
            new MenuItem("7. Show room details", new ShowRoomDetailsAction(roomController), null));
    menuItemList.add(
            new MenuItem(
                    "8. Show rooms by release date", new ShowRoomsByDateAction(roomController), null));
    menuItemList.add(
            new MenuItem(
                    "9. Show amount of payment",
                    new ShowAmountOfPayment(registrationCardController),
                    null));
    menuItemList.add(
            new MenuItem(
                    "10. Show number of current residents",
                    new ShowNumberOfCurrentResidentsAction(registrationCardController),
                    null));
    menuItemList.add(
            new MenuItem(
                    "11. Show occupied rooms by criterion",
                    new ShowOccupiedRoomByCriterionAction(registrationCardController),
                    null));
    menuItemList.add(
            new MenuItem(
                    "12. Show occupied rooms",
                    new ShowOccupiedRoomsAction(registrationCardController),
                    null));
    menuItemList.add(
            new MenuItem(
                    "13. Show resident services by criterion",
                    new ShowResidentServicesByCriterion(registrationCardController),
                    null));
    menuItemList.add(
            new MenuItem(
                    "14. Show current services", new ShowCurrentServicesAction(serviceController), null));
    menuItemList.add(
            new MenuItem(
                    "15. Show price of services and rooms by criterion",
                    new ShowPriceOfServicesAndRoomsByCriterionAction(serviceController),
                    null));
    menuItemList.add(new MenuItem("0. Back to main menu", null, null));

    return new Menu.Builder().name(name).menuItems(menuItemList.toArray(new MenuItem[0])).build();
  }
}
