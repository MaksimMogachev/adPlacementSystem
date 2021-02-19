package com.senlacourses.lecture3.task2;

import java.util.ArrayList;
import java.util.List;

public class BouquetOfFlowers {

  private List<Flower> bouquet = new ArrayList<>();

  public List<Flower> getBouquet() {
    return this.bouquet;
  }

  public void setBouquet(List<Flower> list) {
    this.bouquet = list;
  }

  public void addFlower(Flower flower) {
    this.bouquet.add(flower);
  }

  public void findTheTotalCost() {
    int totalCost = 0;

    for (Flower flower : bouquet) {
      totalCost += flower.getPrice();
    }

    System.out.println("\n" + "total cost is " + totalCost + "\n");
  }
}
