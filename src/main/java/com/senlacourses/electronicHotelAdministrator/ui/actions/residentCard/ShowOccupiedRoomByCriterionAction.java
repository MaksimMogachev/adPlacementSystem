package com.senlacourses.electronicHotelAdministrator.ui.actions.residentCard;

import com.senlacourses.electronicHotelAdministrator.domain.controller.interfaces.IRegistrationCardController;
import com.senlacourses.electronicHotelAdministrator.domain.service.criteriaForSorting.OccupiedRoomSortingCriteria;
import com.senlacourses.electronicHotelAdministrator.ui.IAction;
import java.util.Scanner;

public class ShowOccupiedRoomByCriterionAction implements IAction {

  private final IRegistrationCardController controller;

  public ShowOccupiedRoomByCriterionAction(IRegistrationCardController controller) {
    this.controller = controller;
  }

  @Override
  public void execute() {
    Scanner scanner = new Scanner(System.in);
    OccupiedRoomSortingCriteria criterion;

    System.out.print("Enter occupied room sorting criteria: ");
    criterion = OccupiedRoomSortingCriteria.valueOf(scanner.next().toUpperCase());
//    controller.showOccupiedRoomsByCriterion(criterion);
  }
}
