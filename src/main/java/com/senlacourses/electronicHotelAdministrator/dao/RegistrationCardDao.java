package com.senlacourses.electronicHotelAdministrator.dao;

import com.senlacourses.electronicHotelAdministrator.domain.model.RegistrationCard;

public class RegistrationCardDao extends Dao<RegistrationCard>{

  private static RegistrationCardDao registrationDataBase = new RegistrationCardDao();

  private RegistrationCardDao() {}

  public static RegistrationCardDao getInstance() {
    if(registrationDataBase == null){
      registrationDataBase = new RegistrationCardDao();
    }
    return registrationDataBase;
  }
}
