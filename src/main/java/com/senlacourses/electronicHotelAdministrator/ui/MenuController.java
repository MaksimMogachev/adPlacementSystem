package com.senlacourses.electronicHotelAdministrator.ui;

import java.util.Scanner;

public class MenuController {

  private Navigator navigator = new Navigator();

  public void run() {
    Scanner scanner = new Scanner(System.in);
    int index;

    while (true) {
      clearScreen();
      navigator.printMenu();

      System.out.print("Select the menu item: ");
      index = scanner.nextInt();
      System.out.println();

      navigator.navigate(index);
    }
  }

  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}
