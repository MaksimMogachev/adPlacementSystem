package com.senlacourses.electronicHotelAdministrator.ui;

import java.util.Scanner;

public class MenuController {

  private final Navigator navigator = new Navigator();

  public void run() {
    Scanner scanner = new Scanner(System.in);
    int index;

    while (true) {
      navigator.printMenu();

      System.out.print("Select the menu item: ");

      index = scanner.nextInt();
      System.out.println();

      navigator.navigate(index);
    }
  }
}
