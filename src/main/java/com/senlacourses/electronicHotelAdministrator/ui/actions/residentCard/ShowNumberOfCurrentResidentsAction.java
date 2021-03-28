package com.senlacourses.electronicHotelAdministrator.ui.actions.residentCard;

import com.senlacourses.electronicHotelAdministrator.domain.controller.interfaces.IRegistrationCardController;
import com.senlacourses.electronicHotelAdministrator.domain.service.RegistrationCardService;
import com.senlacourses.electronicHotelAdministrator.ui.IAction;

public class ShowNumberOfCurrentResidentsAction implements IAction {

  private IRegistrationCardController controller;

  public ShowNumberOfCurrentResidentsAction(IRegistrationCardController controller) {
    this.controller = controller;
  }

  @Override
  public void execute() {
    controller.showNumberOfCurrentResidents();
  }
}
