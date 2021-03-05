package com.senlacourses.electronicHotelAdministrator.dao;

import com.senlacourses.electronicHotelAdministrator.domain.model.HotelResident;

public class HotelResidentDao extends Dao<HotelResident> {

  private static HotelResidentDao dataBase = new HotelResidentDao();

  private HotelResidentDao() {}

  public static HotelResidentDao getInstance() {
    if(dataBase == null){
      dataBase = new HotelResidentDao();
    }
    return dataBase;
  }
}
