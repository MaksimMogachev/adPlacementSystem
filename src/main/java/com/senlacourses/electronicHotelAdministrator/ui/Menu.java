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


    public Builder() {}


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


  }
}
