package com.senlacourses.electronicHotelAdministrator.ui.actions.service;

import com.senlacourses.electronicHotelAdministrator.domain.controller.interfaces.IServiceController;
import com.senlacourses.electronicHotelAdministrator.domain.service.ServiceService;
import com.senlacourses.electronicHotelAdministrator.ui.IAction;
import java.util.Scanner;

public class ChangeServicePriceAction implements IAction {

  private IServiceController controller;

  public ChangeServicePriceAction(IServiceController controller) {
    this.controller = controller;
  }

  @Override
  public void execute() {
    Scanner scanner = new Scanner(System.in);
    String name;
    int price;

    System.out.print("Enter name of service: ");
    name = scanner.nextLine();
    System.out.print("\nEnter new price of service: ");
    price = scanner.nextInt();
    controller.changeServicePrice(name,price);
  }
}
