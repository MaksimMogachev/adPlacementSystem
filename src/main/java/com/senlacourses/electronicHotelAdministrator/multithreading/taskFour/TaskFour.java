package com.senlacourses.electronicHotelAdministrator.multithreading.taskFour;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TaskFour implements Runnable {

  private final int quantityOfSeconds;

  public TaskFour(int quantityOfSeconds) {
    this.quantityOfSeconds = quantityOfSeconds;
  }


  @Override
  public void run() {
    while (true) {
      System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
      try {
        Thread.sleep((long) quantityOfSeconds * 1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
