package com.senlacourses.electronicHotelAdministrator.ui.actions.service;

import com.senlacourses.electronicHotelAdministrator.domain.controller.interfaces.IServiceController;
import com.senlacourses.electronicHotelAdministrator.domain.service.ServiceService;
import com.senlacourses.electronicHotelAdministrator.ui.IAction;

public class ShowCurrentServicesAction implements IAction {

  private IServiceController controller;

  public ShowCurrentServicesAction(IServiceController controller) {
    this.controller = controller;
  }

  @Override
  public void execute() {
    controller.showCurrentServices();
  }
}
