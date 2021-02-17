package com.senlacourses.lecture3.electronicHotelAdministrator;

import java.util.HashMap;
import java.util.Map;

public class Services {

  private Map<String, Integer> services = new HashMap<>();

  public Map<String, Integer> getServices() {
    return services;
  }

  public void addNewService(String name, int price) {
    this.services.put(name, price);
  }

  public void showCurrentServices() {
    System.out.println(services.toString());  //override toString
  }

  public void changePrice(String key, int price) {
    services.put(key, price);
  }
}
