package com.senlacourses.lecture3.task1;

public class Main {

  public static void main(String[] args) {
    int maximumInt = 1000;
    int minimumInt = 100;
    TaskOne taskOne = new TaskOne(maximumInt, minimumInt);
    taskOne.findTheLargest();
  }

}
