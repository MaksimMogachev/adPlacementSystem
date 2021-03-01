package com.senlacourses.electronicHotelAdministrator.dao;

import com.senlacourses.electronicHotelAdministrator.domain.HotelResident;
import java.util.ArrayList;
import java.util.List;

public class HotelResidentDao implements Dao<HotelResident>{

  private List<HotelResident> residents = new ArrayList<>();

  @Override
  public List<HotelResident> getAll() {
    return residents;
  }

  @Override
  public void create(HotelResident hotelResident) {
    residents.add(hotelResident);
  }

  @Override
  public HotelResident read(long id) {
    return residents.get((int) id);
  }

  @Override
  public void update(HotelResident hotelResident, int id) {
    residents.add(id, hotelResident);
  }

  @Override
  public void delete(HotelResident hotelResident) {
    residents.remove(hotelResident);
  }
}
