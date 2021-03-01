package com.senlacourses.lecture3.task2.flowers;

public class Wildflower extends Flower {

  public Wildflower(String name, int price) {
    super(name, price);
    System.out.println("you take " + this.getName() + ", price - " + this.getPrice());
  }
}
