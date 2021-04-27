package com.senlacourses.electronicHotelAdministrator.multithreading.taskOne;

public class TaskOne implements Runnable {

  private static Thread threadOne;
  private static Thread threadTwo;
  public static Thread t1;
  public static Thread t3;

  public static void main(String[] args) throws InterruptedException {
    TaskOne objectOne = new TaskOne();
    TaskOne objectTwo = new TaskOne();
    threadOne = new Thread(objectOne);
    threadTwo = new Thread(objectTwo);
    t1 = new Thread(new DemoThreadB());
    Thread t2 = new Thread(new DemoThreadB());
    t3 = new Thread(new WaitingState());

    System.out.println(threadOne.getState());

    threadOne.start();
    System.out.println(threadOne.getState());
    threadTwo.start();

    objectOne.someMethod();
    objectTwo.someMethod();

    t1.start();
    t2.start();

    Thread.sleep(1000);

    System.out.println(t2.getState());

    t3.start();
  }

  @Override
  public void run() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    try {
      Thread.sleep(200);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private void someMethod() throws InterruptedException {
    Thread.sleep(1000);
    System.out.println(threadTwo.getState());
  }
}

class DemoThreadB implements Runnable {
  @Override
  public void run() {
    commonResource();
  }

  public static synchronized void commonResource() {
    while (true) {
    }
  }
}

class WaitingState implements Runnable {

  public void run() {
    Thread t2 = new Thread(new DemoThreadWS());
    t2.start();

    try {
      t2.join();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}

class DemoThreadWS implements Runnable {
  public void run() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    System.out.println(TaskOne.t3.getState());
  }
}
