package com.senlacourses.electronicHotelAdministrator.dao;

import com.senlacourses.electronicHotelAdministrator.domain.model.HotelResident;

public class HotelResidentDao extends Dao<HotelResident> {

  private static HotelResidentDao hotelResidentDataBase = new HotelResidentDao();

  public static HotelResidentDao getInstance() {
    if(hotelResidentDataBase == null){
      hotelResidentDataBase = new HotelResidentDao();
    }
    return hotelResidentDataBase;
  }
}
