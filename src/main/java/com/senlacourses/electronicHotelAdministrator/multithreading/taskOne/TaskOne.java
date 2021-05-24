package com.senlacourses.electronicHotelAdministrator.multithreading.taskOne;

public class TaskOne implements Runnable {

  Thread main = Thread.currentThread();

  public static void main(String[] args) throws InterruptedException {
    TaskOne taskOne = new TaskOne();
    TaskOneHelper taskOneHelper = new TaskOneHelper();
    Thread thread1 = new Thread(taskOneHelper);
    Thread thread2 = new Thread(taskOne);

    printState(thread1);
    thread1.start();
    printState(thread1);
    thread2.start();

    Thread.sleep(1000);
    printState(thread1);
    taskOneHelper.notifier();
    printState(thread1);
    printState(thread2);
  }

  private static void printState(Thread thread) {
    System.out.println(thread.getState());
  }

  @Override
  public void run() {
    try {
      Thread.sleep(100);
      printState(main);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
