package com.senlacourses.electronicHotelAdministrator.dao;

import com.senlacourses.electronicHotelAdministrator.domain.model.HotelRoom;

public class HotelRoomDao extends Dao<HotelRoom> {

  private static HotelRoomDao hotelRoomDataBase = new HotelRoomDao();

  private HotelRoomDao() {}

  public static HotelRoomDao getInstance() {
    if(hotelRoomDataBase == null){
      hotelRoomDataBase = new HotelRoomDao();
    }
    return hotelRoomDataBase;
  }
}
