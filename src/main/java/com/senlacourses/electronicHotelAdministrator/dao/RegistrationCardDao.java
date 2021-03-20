package com.senlacourses.electronicHotelAdministrator.dao;

import com.senlacourses.electronicHotelAdministrator.domain.model.RegistrationCard;

public class RegistrationCardDao extends Dao<RegistrationCard>{

  private static RegistrationCardDao registrationCardDataBase = new RegistrationCardDao();

  public static RegistrationCardDao getInstance() {
    if(registrationCardDataBase == null){
      registrationCardDataBase = new RegistrationCardDao();
    }
    return registrationCardDataBase;
  }
}
