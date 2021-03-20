package com.senlacourses.electronicHotelAdministrator.ui.actions.service;

import com.senlacourses.electronicHotelAdministrator.domain.service.ServiceService;
import com.senlacourses.electronicHotelAdministrator.ui.IAction;

public class ShowCurrentServicesAction implements IAction {

  @Override
  public void execute() {
    new ServiceService().showCurrentServices();
  }
}
