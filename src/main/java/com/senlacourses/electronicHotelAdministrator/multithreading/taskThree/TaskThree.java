package com.senlacourses.electronicHotelAdministrator.multithreading.taskThree;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TaskThree implements Runnable {

  private final int maxBufferSize;
  private final List<Integer> buffer = new ArrayList<>();

  public TaskThree(int maxBufferSize) {
    this.maxBufferSize = maxBufferSize;
  }

  public void startSelection() {
    int randomNumber;

    while (true) {
      if (buffer.size() != 0) {
        randomNumber = buffer.get(ThreadLocalRandom.current().nextInt(0, buffer.size()));
        System.out.println("Removing number - " + randomNumber
                + "\nCurrent buffer size: " + (getCurrentBufferSize() - randomNumber));
        buffer.remove(Integer.valueOf(randomNumber));
        try {
          Thread.sleep((long) randomNumber * 200);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

  private int getCurrentBufferSize() {
    int currentBufferSize = 0;

    for (int i : buffer) {
      currentBufferSize = currentBufferSize + i;
    }
    return currentBufferSize;
  }

  @Override
  public void run() {
    int randomNumber = 0;

    while (true) {
      if (maxBufferSize != getCurrentBufferSize())
      randomNumber = ThreadLocalRandom.current().nextInt(0, maxBufferSize - getCurrentBufferSize());
      if (randomNumber == 0) {
        continue;
      }
      buffer.add(randomNumber);
      System.out.println("Adding number - " + randomNumber + "\nCurrent buffer size: " + getCurrentBufferSize());
      try {
        Thread.sleep((long) randomNumber * 100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
