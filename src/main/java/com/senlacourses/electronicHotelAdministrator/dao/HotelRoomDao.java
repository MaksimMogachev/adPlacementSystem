package com.senlacourses.electronicHotelAdministrator.dao;

import com.senlacourses.electronicHotelAdministrator.domain.model.HotelRoom;

public class HotelRoomDao extends Dao<HotelRoom>{

  private static HotelRoomDao roomDataBase = new HotelRoomDao();

  private HotelRoomDao() {}

  public static HotelRoomDao getInstance() {
    if(roomDataBase == null){
      roomDataBase = new HotelRoomDao();
    }
    return roomDataBase;
  }
}
