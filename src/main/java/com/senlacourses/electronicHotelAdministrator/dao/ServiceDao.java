package com.senlacourses.electronicHotelAdministrator.dao;

import com.senlacourses.electronicHotelAdministrator.domain.model.Service;

public class ServiceDao extends Dao<Service>{

  private static ServiceDao serviceDataBase = new ServiceDao();

  public static ServiceDao getInstance() {
    if(serviceDataBase == null){
      serviceDataBase = new ServiceDao();
    }
    return serviceDataBase;
  }
}
