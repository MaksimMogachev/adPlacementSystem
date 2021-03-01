package com.senlacourses.electronicHotelAdministrator.dao;

import com.senlacourses.electronicHotelAdministrator.domain.CheckInRegistration;
import java.util.ArrayList;
import java.util.List;

public class CheckInRegistrationDao implements Dao<CheckInRegistration>{

  private List<CheckInRegistration> bookedRooms = new ArrayList<>();

  @Override
  public List<CheckInRegistration> getAll() {
    return bookedRooms;
  }

  @Override
  public void create(CheckInRegistration checkInRegistration) {
    bookedRooms.add(checkInRegistration);
  }

  @Override
  public CheckInRegistration read(long id) {
    return bookedRooms.get((int) id);
  }

  @Override
  public void update(CheckInRegistration checkInRegistration, int id) {
    bookedRooms.add(id, checkInRegistration);
  }

  @Override
  public void delete(CheckInRegistration checkInRegistration) {
    bookedRooms.remove(checkInRegistration);
  }
}
