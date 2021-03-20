package com.senlacourses.electronicHotelAdministrator.ui.actions.residentCard;

import com.senlacourses.electronicHotelAdministrator.domain.model.criteriaForSorting.OccupiedRoomSortingCriteria;
import com.senlacourses.electronicHotelAdministrator.domain.model.criteriaForSorting.ServiceSortingCriteria;
import com.senlacourses.electronicHotelAdministrator.domain.service.RegistrationCardService;
import com.senlacourses.electronicHotelAdministrator.ui.IAction;
import java.util.Scanner;

public class ShowResidentServicesByCriterion implements IAction {

  @Override
  public void execute() {
    Scanner scanner = new Scanner(System.in);
    String fullName;
    ServiceSortingCriteria criterion;

    System.out.print("Enter occupied room sorting criteria: ");
    fullName = scanner.nextLine();
    System.out.print("\nEnter occupied room sorting criteria: ");
    criterion = ServiceSortingCriteria.valueOf(scanner.next().toUpperCase());
    new RegistrationCardService().showResidentServicesByCriterion(fullName, criterion);
  }
}