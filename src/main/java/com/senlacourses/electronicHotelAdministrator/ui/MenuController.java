package com.senlacourses.electronicHotelAdministrator.ui;

import com.senlacourses.electronicHotelAdministrator.dao.HotelResidentDao;
import com.senlacourses.electronicHotelAdministrator.dao.HotelRoomDao;
import com.senlacourses.electronicHotelAdministrator.dao.RegistrationCardDao;
import com.senlacourses.electronicHotelAdministrator.dao.ServiceDao;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class MenuController {

  private final Navigator navigator = new Navigator();

  public void run() {
    Scanner scanner = new Scanner(System.in);
    int index;

    while (true) {
      navigator.printMenu();

      System.out.print("Select the menu item: ");

      if (scanner.nextLine().equalsIgnoreCase("exit")) {
        saveData();
        break;
      }
      index = scanner.nextInt();
      System.out.println();

      navigator.navigate(index);
    }
  }

  private void saveData() {
    String fileName = "src/data.bin";

    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
      oos.writeObject(HotelResidentDao.getInstance().getAll());
      oos.writeObject(HotelRoomDao.getInstance().getAll());
      oos.writeObject(RegistrationCardDao.getInstance().getAll());
      oos.writeObject(ServiceDao.getInstance().getAll());

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
