package com.senlacourses.electronicHotelAdministrator.dao;

import com.senlacourses.electronicHotelAdministrator.domain.model.CheckInRegistration;

public class CheckInRegistrationDao extends Dao<CheckInRegistration>{

  private static CheckInRegistrationDao dataBase = new CheckInRegistrationDao();

  private CheckInRegistrationDao() {}

  public static CheckInRegistrationDao getInstance() {
    if(dataBase == null){
      dataBase = new CheckInRegistrationDao();
    }
    return dataBase;
  }
}
