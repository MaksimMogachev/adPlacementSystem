package com.senlacourses.lecture3.task2.flowers;

public class ExoticFlower extends Flower {

  public ExoticFlower(String name, int price) {
    super(name, price);
    System.out.println("you take " + this.getName() + ", price - " + this.getPrice());
  }
}
