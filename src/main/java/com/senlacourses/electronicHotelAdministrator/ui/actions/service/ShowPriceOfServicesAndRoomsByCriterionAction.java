package com.senlacourses.electronicHotelAdministrator.ui.actions.service;

import com.senlacourses.electronicHotelAdministrator.domain.model.criteriaForSorting.ServiceAndRoomSortingCriteria;
import com.senlacourses.electronicHotelAdministrator.domain.model.criteriaForSorting.ServiceSortingCriteria;
import com.senlacourses.electronicHotelAdministrator.domain.service.RegistrationCardService;
import com.senlacourses.electronicHotelAdministrator.domain.service.ServiceService;
import com.senlacourses.electronicHotelAdministrator.ui.IAction;
import java.util.Scanner;

public class ShowPriceOfServicesAndRoomsByCriterionAction implements IAction {

  @Override
  public void execute() {
    Scanner scanner = new Scanner(System.in);
    ServiceAndRoomSortingCriteria criterion;

    System.out.print("Enter services and rooms sorting criteria: ");
    criterion = ServiceAndRoomSortingCriteria.valueOf(scanner.next().toUpperCase());
    new ServiceService().showPriceOfServicesAndRoomsByCriterion(criterion);
  }
}
