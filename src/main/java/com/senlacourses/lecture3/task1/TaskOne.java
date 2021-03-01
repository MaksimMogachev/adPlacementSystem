package com.senlacourses.lecture3.task1;

import java.util.Random;

public class TaskOne {

  private final int randomInt;

  public TaskOne(int maximumInt, int minimumInt) {
    final Random random = new Random();
    this.randomInt = random.nextInt(maximumInt - minimumInt) + minimumInt;
  }

  public void findTheLargest() {
    char[] arrayFromInt = ("" + this.randomInt).toCharArray();
    char largestInt = arrayFromInt[0];

    System.out.println("The original number - " + this.randomInt);

    for (int i = 1; i < arrayFromInt.length; i++) {
      if ((int) arrayFromInt[i] > (int) largestInt) {
        largestInt = arrayFromInt[i];
      }
    }

    System.out.println("The highest figure - " + largestInt);
  }
}
