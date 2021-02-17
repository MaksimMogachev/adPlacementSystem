package com.senlacourses.lecture3.task2;

import java.util.ArrayList;
import java.util.List;

public class BouquetOfFlowers {

  private List<Flower> bouquet = new ArrayList<>();
  private int totalCost = 0;

  public List<Flower> getBouquet() {
    return this.bouquet;
  }

  public void setBouquet(List<Flower> list) {
    this.bouquet = list;
  }

  public void addFlower(Flower flower) {
    this.bouquet.add(flower);
  }


  public int getTotalCost() {
    for (Flower flower : bouquet) {
      totalCost += flower.getPrice();
    }
    return totalCost;
  }

  public void findTheTotalCost() {

    System.out.println("\n" + "total cost is " + this.getTotalCost());
  }
}
