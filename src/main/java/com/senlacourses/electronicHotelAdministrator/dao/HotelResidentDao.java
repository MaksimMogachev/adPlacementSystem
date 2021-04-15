package com.senlacourses.electronicHotelAdministrator.dao;

import com.senlacourses.electronicHotelAdministrator.domain.model.HotelResident;

public class HotelResidentDao extends Dao<HotelResident> {

  private static HotelResidentDao hotelResidentDataBase = new HotelResidentDao();

  private HotelResidentDao() {}

  public static HotelResidentDao getInstance() {
    return hotelResidentDataBase;
  }

}
