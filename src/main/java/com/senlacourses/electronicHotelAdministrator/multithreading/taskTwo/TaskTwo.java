package com.senlacourses.electronicHotelAdministrator.multithreading.taskTwo;

public class TaskTwo {

  public static void main(String[] args) throws InterruptedException {
    Thread threadOne = new Thread();
    Thread threadTwo = new Thread();

    while (true) {
      printName(threadOne);
      printName(threadTwo);
      Thread.sleep(1000);
    }

  }

  private static synchronized void printName(Thread thread) {
    System.out.println(thread.getName());

  }
}
