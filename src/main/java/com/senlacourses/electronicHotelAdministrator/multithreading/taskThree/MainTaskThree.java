package com.senlacourses.electronicHotelAdministrator.multithreading.taskThree;

public class MainTaskThree {

  public static void main(String[] args) {
    int maxBufferSize = 100;
    TaskThree taskThree = new TaskThree(maxBufferSize);
    Thread thread = new Thread(taskThree);

    thread.start();
    taskThree.startSelection();
  }
}
