package com.senlacourses.lecture3.task2;

import com.senlacourses.lecture3.task2.flowers.ExoticFlower;
import com.senlacourses.lecture3.task2.flowers.GardenFlower;
import com.senlacourses.lecture3.task2.flowers.Wildflower;

public class Main {

  public static void main(String[] args) {
    FlowerShop flowerShop = new FlowerShop();
    BouquetOfFlowers bouquet = new BouquetOfFlowers();

    bouquet.addFlower(new ExoticFlower("Orchid", 240));
    bouquet.addFlower(new GardenFlower("Rose", 180));
    bouquet.addFlower(new GardenFlower("Carnation", 200));
    bouquet.addFlower(new Wildflower("Lavender", 130));
    bouquet.addFlower(new Wildflower("Sage", 150));

    flowerShop.findTheTotalCost(bouquet);

    flowerShop.addNewBouquetToList(bouquet);
    flowerShop.showCurrentBouquets();
  }

}
