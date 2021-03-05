package com.senlacourses.electronicHotelAdministrator.dao;

import com.senlacourses.electronicHotelAdministrator.domain.model.HotelRoom;

public class HotelRoomDao extends Dao<HotelRoom>{

  private static HotelRoomDao dataBase = new HotelRoomDao();

  private HotelRoomDao() {}

  public static HotelRoomDao getInstance() {
    if(dataBase == null){
      dataBase = new HotelRoomDao();
    }
    return dataBase;
  }
}
