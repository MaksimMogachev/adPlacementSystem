package com.senlacourses.electronicHotelAdministrator.multithreading.taskFour;

public class Main {

  public static void main(String[] args) {
    int quantityOfSeconds = 3;
    Thread thread = new Thread(new TaskFour(quantityOfSeconds));
    thread.start();
  }
}
