package com.senlacourses.electronicHotelAdministrator.domain.model;

import java.io.Serial;
import java.io.Serializable;

public class Service implements Serializable {

  @Serial
  private static final long serialVersionUID = -1000592815847945978L;

  private String name;
  private int price;

  public Service(String name, int price) {
    this.name = name;
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return name + ": " + price;
  }
}
