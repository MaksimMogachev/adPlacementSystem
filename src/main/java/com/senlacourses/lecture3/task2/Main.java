package com.senlacourses.lecture3.task2;

public class Main {

  public static void main(String[] args) {
    BouquetOfFlowers bouquetOfFlowers = new BouquetOfFlowers();

    bouquetOfFlowers.addFlower(new ExoticFlower("Orchid", 240));
    bouquetOfFlowers.addFlower(new GardenFlower("Rose", 180));
    bouquetOfFlowers.addFlower(new GardenFlower("Carnation", 200));
    bouquetOfFlowers.addFlower(new Wildflower("Lavender", 130));
    bouquetOfFlowers.addFlower(new Wildflower("Sage", 150));

    bouquetOfFlowers.findTheTotalCost();
  }

}
