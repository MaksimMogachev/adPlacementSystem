package com.senlacourses.electronicHotelAdministrator.dao;

import com.senlacourses.electronicHotelAdministrator.domain.model.Service;

public class ServiceDao extends Dao<Service>{

  private static ServiceDao dataBase = new ServiceDao();

  private ServiceDao() {}

  public static ServiceDao getInstance() {
    if(dataBase == null){
      dataBase = new ServiceDao();
    }
    return dataBase;
  }
}
