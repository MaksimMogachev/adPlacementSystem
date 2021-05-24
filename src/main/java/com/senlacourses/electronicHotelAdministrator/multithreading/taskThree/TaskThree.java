package com.senlacourses.electronicHotelAdministrator.multithreading.taskThree;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class TaskThree implements Runnable {

  private final int maxBufferSize;
  private final List<Integer> buffer;

  public TaskThree(int maxBufferSize) {
    this.maxBufferSize = maxBufferSize;
    this.buffer = new CopyOnWriteArrayList<>();
  }

  public void startSelection() {
    int randomNumber;

    while (true) {
      if (buffer.size() != 0) {
        randomNumber = buffer.get(ThreadLocalRandom.current().nextInt(0, buffer.size()));
        buffer.remove(Integer.valueOf(randomNumber));
        showCurrentBuffer();
        try {
          Thread.sleep((long) randomNumber * 50);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      } else {
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }


  private void showCurrentBuffer() {
    System.out.println("buffer: " + buffer);
  }

  @Override
  public void run() {
    int randomNumber = 0;

    while (true) {
      if (maxBufferSize != buffer.size()) {
        randomNumber = ThreadLocalRandom.current().nextInt(0, 10);
      } else {
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      if (randomNumber == 0) {
        continue;
      }
      buffer.add(randomNumber);
      showCurrentBuffer();
      try {
        Thread.sleep((long) randomNumber * 50);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
