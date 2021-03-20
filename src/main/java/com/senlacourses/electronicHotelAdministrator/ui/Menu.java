package com.senlacourses.electronicHotelAdministrator.ui;

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

public class Menu {

  private String name;
  private MenuItem[] menuItems;

  private Menu(String name, MenuItem[] menuItems) {
    this.name = name;
    this.menuItems = menuItems;
  }

  public MenuItem[] getMenuItems() {
    return menuItems;
  }

  public void setMenuItems(MenuItem[] menuItems) {
    this.menuItems = menuItems;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public static class Builder {

    private String name;
    private MenuItem[] menuItems;

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder menuItems(MenuItem[] menuItems) {
      this.menuItems = menuItems;
      return this;
    }

    public Menu build() {
      return new Menu(this.name, this.menuItems);
    }

    public static Menu getRootMenu() {
      String name = "Main menu";
      List<MenuItem> menuItemList = new ArrayList<>();

      menuItemList.add(new MenuItem("1. Menu to adding information", null, getAddingMenu()));
      menuItemList.add(new MenuItem("2. Menu to Updating/deleting information", null, getUpdatingMenu()));
      menuItemList.add(new MenuItem("3. Menu to display information", null, getDisplayMenu()));

      return new Builder().name(name).menuItems(menuItemList.toArray(new MenuItem[0])).build();
    }

    public static Menu getAddingMenu() {
      String name = "Add information";
      List<MenuItem> menuItemList = new ArrayList<>();

      menuItemList.add(new MenuItem("1. Put in the room", new PutInTheRoomActon(), null));
      menuItemList.add(new MenuItem("2. Add service to occupied room",
          new AddServiceToOccupiedRoomAction(), null));
      menuItemList.add(new MenuItem("3. Add new room", new AddNewRoomAction(), null));
      menuItemList.add(new MenuItem("4. Add new service", new AddNewServiceAction(), null));
      menuItemList.add(new MenuItem("5. Add new resident", new AddNewResidentAction(), null));
      menuItemList.add(new MenuItem("0. Back to main menu", null, null));

      return new Menu.Builder().name(name).menuItems(menuItemList.toArray(new MenuItem[0])).build();
    }

    public static Menu getUpdatingMenu() {
      String name = "Update/delete information";
      List<MenuItem> menuItemList = new ArrayList<>();

      menuItemList.add(new MenuItem("1. Remove resident from database", new RemoveResidentAction(), null));
      menuItemList.add(new MenuItem("2. Change room condition", new ChangeRoomConditionAction(), null));
      menuItemList.add(new MenuItem("3. Change room price", new ChangeRoomPriceAction(), null));
      menuItemList.add(new MenuItem("4. Change service price", new ChangeServicePriceAction(), null));
      menuItemList.add(new MenuItem("0. Back to main menu", null, null));

      return new Menu.Builder().name(name).menuItems(menuItemList.toArray(new MenuItem[0])).build();
    }

    public static Menu getDisplayMenu() {
      String name = "Show information";
      List<MenuItem> menuItemList = new ArrayList<>();

      menuItemList.add(new MenuItem("1. Show all residents", new ShowAllResidentsAction(), null));
      menuItemList.add(new MenuItem("2. Show all rooms", new ShowAllRoomsAction(), null));
      menuItemList.add(new MenuItem("3. Show all rooms by criterion",
          new ShowAllRoomsByCriterionAction(), null));
      menuItemList.add(new MenuItem("4. Show all free rooms by criterion",
          new ShowFreeRoomsByCriterionAction(), null));
      menuItemList.add(new MenuItem("5. Show last residents of room",
          new ShowLastResidentsOfRoomAction(), null));
      menuItemList.add(new MenuItem("6. Show number of free rooms",
          new ShowNumberOfFreeRoomsAction(), null));
      menuItemList.add(new MenuItem("7. Show room details", new ShowRoomDetailsAction(), null));
      menuItemList.add(new MenuItem("8. Show rooms by release date",
          new ShowRoomsByDateAction(), null));
      menuItemList.add(new MenuItem("9. Show amount of payment", new ShowAmountOfPayment(), null));
      menuItemList.add(new MenuItem("10. Show number of current residents",
          new ShowNumberOfCurrentResidentsAction(), null));
      menuItemList.add(new MenuItem("11. Show occupied rooms by criterion",
          new ShowOccupiedRoomByCriterionAction(), null));
      menuItemList.add(new MenuItem("12. Show occupied rooms",
          new ShowOccupiedRoomsAction(), null));
      menuItemList.add(new MenuItem("13. Show resident services by criterion",
          new ShowResidentServicesByCriterion(), null));
      menuItemList.add(new MenuItem("14. Show current services", new ShowCurrentServicesAction(), null));
      menuItemList.add(new MenuItem("15. Show Price Of Services And Rooms By Criterion",
          new ShowPriceOfServicesAndRoomsByCriterionAction(), null));
      menuItemList.add(new MenuItem("0. Back to main menu", null, null));

      return new Menu.Builder().name(name).menuItems(menuItemList.toArray(new MenuItem[0])).build();
    }
  }
}
