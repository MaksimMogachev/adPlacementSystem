package com.senlacourses.electronicHotelAdministrator.ui.actions.residentCard;

import com.senlacourses.electronicHotelAdministrator.domain.model.criteriaForSorting.OccupiedRoomSortingCriteria;
import com.senlacourses.electronicHotelAdministrator.domain.service.RegistrationCardService;
import com.senlacourses.electronicHotelAdministrator.ui.IAction;
import java.util.Scanner;

public class ShowOccupiedRoomByCriterionAction implements IAction {

  @Override
  public void execute() {
    Scanner scanner = new Scanner(System.in);
    OccupiedRoomSortingCriteria criterion;

    System.out.print("Enter occupied room sorting criteria: ");
    criterion = OccupiedRoomSortingCriteria.valueOf(scanner.next().toUpperCase());
    new RegistrationCardService().showOccupiedRoomsByCriterion(criterion);
  }
}
