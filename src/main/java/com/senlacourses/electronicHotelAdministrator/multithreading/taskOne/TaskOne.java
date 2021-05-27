package com.senlacourses.electronicHotelAdministrator.multithreading.taskOne;

public class TaskOne {

  static Thread main = Thread.currentThread();

  public static void main(String[] args) throws InterruptedException {
    TaskOneHelper taskOneHelper = new TaskOneHelper();
    Thread thread1 = new Thread(taskOneHelper);

    printState(thread1);
    thread1.start();
    printState(thread1);

    Thread.sleep(1000);
    printState(thread1);
    taskOneHelper.notifier();
    printState(thread1);
    Thread.sleep(1000);
    printState(thread1);
  }

  protected static void printState(Thread thread) {
    System.out.println(thread.getState());
  }
}
