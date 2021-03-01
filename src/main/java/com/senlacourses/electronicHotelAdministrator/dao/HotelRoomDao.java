package com.senlacourses.electronicHotelAdministrator.dao;

import com.senlacourses.electronicHotelAdministrator.domain.HotelRoom;
import java.util.ArrayList;
import java.util.List;

public class HotelRoomDao implements Dao<HotelRoom>{

  private List<HotelRoom> rooms = new ArrayList<>();

  @Override
  public List<HotelRoom> getAll() {
    return rooms;
  }

  @Override
  public void create(HotelRoom hotelRoom) {
    rooms.add(hotelRoom);
  }

  @Override
  public HotelRoom read(long id) {
    return rooms.get((int) id);
  }

  @Override
  public void update(HotelRoom hotelRoom, int id) {
    rooms.add(id, hotelRoom);
  }

  @Override
  public void delete(HotelRoom hotelRoom) {
    rooms.remove(hotelRoom);
  }
}
