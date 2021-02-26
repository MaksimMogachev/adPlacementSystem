package com.senlacourses.lecture3.task2;

import com.senlacourses.lecture3.task2.flowers.Flower;
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
}
