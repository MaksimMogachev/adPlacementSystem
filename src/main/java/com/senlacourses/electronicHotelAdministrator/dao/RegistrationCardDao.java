package com.senlacourses.electronicHotelAdministrator.dao;

import com.senlacourses.electronicHotelAdministrator.domain.model.RegistrationCard;

public class RegistrationCardDao extends Dao<RegistrationCard> {

  private static RegistrationCardDao registrationCardDataBase = new RegistrationCardDao();

  private RegistrationCardDao() {}

  public static RegistrationCardDao getInstance() {
    return registrationCardDataBase;
  }
}
