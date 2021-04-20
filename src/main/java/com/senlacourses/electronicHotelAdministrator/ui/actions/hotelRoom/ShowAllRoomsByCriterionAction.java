package com.senlacourses.electronicHotelAdministrator.ui.actions.hotelRoom;

import com.senlacourses.electronicHotelAdministrator.domain.controller.interfaces.IHotelRoomController;
import com.senlacourses.electronicHotelAdministrator.domain.model.criteriaForSorting.RoomSortingCriteria;
import com.senlacourses.electronicHotelAdministrator.ui.IAction;
import java.util.Scanner;

public class ShowAllRoomsByCriterionAction implements IAction {

  private final IHotelRoomController controller;

  public ShowAllRoomsByCriterionAction(IHotelRoomController controller) {
    this.controller = controller;
  }

  @Override
  public void execute() {
    Scanner scanner = new Scanner(System.in);
    RoomSortingCriteria criterion;

    System.out.print("Enter room sorting criteria: ");
    criterion = RoomSortingCriteria.valueOf(scanner.next().toUpperCase());
    controller.showAllRoomsByCriterion(criterion);
  }
}
