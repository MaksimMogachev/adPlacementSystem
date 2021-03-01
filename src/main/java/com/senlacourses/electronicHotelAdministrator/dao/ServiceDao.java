package com.senlacourses.electronicHotelAdministrator.dao;

import com.senlacourses.electronicHotelAdministrator.domain.Service;
import java.util.ArrayList;
import java.util.List;

public class ServiceDao implements Dao<Service>{

  private List<Service> services = new ArrayList<>();

  @Override
  public List<Service> getAll() {
    return services;
  }

  @Override
  public void create(Service service) {
    services.add(service);
  }

  @Override
  public Service read(long id) {
    return services.get((int) id);
  }

  @Override
  public void update(Service service, int id) {
    services.add(id, service);
  }

  @Override
  public void delete(Service service) {
    services.remove(service);
  }
}
