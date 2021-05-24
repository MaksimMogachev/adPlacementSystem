package com.senlacourses.electronicHotelAdministrator.multithreading.taskOne;

public class TaskOneHelper implements Runnable {
  @Override
  public synchronized void run() {
    try {
      wait();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public synchronized void notifier() {
    notifyAll();
  }
}
