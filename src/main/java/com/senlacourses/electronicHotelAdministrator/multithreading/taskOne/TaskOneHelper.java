package com.senlacourses.electronicHotelAdministrator.multithreading.taskOne;

import static com.senlacourses.electronicHotelAdministrator.multithreading.taskOne.TaskOne.printState;

public class TaskOneHelper implements Runnable {
  @Override
  public synchronized void run() {
    try {
      Thread.sleep(100);
      printState(TaskOne.main);
      wait();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public synchronized void notifier() {
    notifyAll();
  }
}
