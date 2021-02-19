package com.senlacourses.lecture3.task2;

import java.util.ArrayList;
import java.util.List;

public class FlowerShop {

  private List<BouquetOfFlowers> bouquets = new ArrayList<>();

  public void addNewBouquetToList(BouquetOfFlowers bouquetOfFlowers) {
    bouquets.add(bouquetOfFlowers);
  }

  public void showCurrentBouquets() {
    for (BouquetOfFlowers bouquetOfFlowers : bouquets) {
      for (int i = 0; i < bouquetOfFlowers.getBouquet().size(); i++) {
        System.out.println(bouquetOfFlowers.getBouquet().get(i).getName() + " - "
            + bouquetOfFlowers.getBouquet().get(i).getPrice());
      }
      System.out.println();
    }
  }
}
