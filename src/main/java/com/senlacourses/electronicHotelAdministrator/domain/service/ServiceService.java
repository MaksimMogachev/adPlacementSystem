package com.senlacourses.electronicHotelAdministrator.domain.service;

import com.senlacourses.electronicHotelAdministrator.dao.HotelRoomDao;
import com.senlacourses.electronicHotelAdministrator.dao.ServiceDao;
import com.senlacourses.electronicHotelAdministrator.domain.model.HotelRoom;
import com.senlacourses.electronicHotelAdministrator.domain.model.Service;
import com.senlacourses.electronicHotelAdministrator.domain.model.criteriaForSorting.ServiceAndRoomSortingCriteria;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ServiceService {

  private ServiceDao serviceDao = ServiceDao.getInstance();
  private HotelRoomDao hotelRoomDao = HotelRoomDao.getInstance();

  public List<Service> getServices() {
    return serviceDao.getAll();
  }

  public void addNewService(String name, int price) {
    serviceDao.create(new Service(name, price));
  }

  public void showCurrentServices() {
    for (Service service : serviceDao.getAll()) {
      System.out.println(service.toString());
    }
  }

  public void changeServicePrice(String nameOfService, int newPrice) {
    int indexOfService = findIndexOfService(nameOfService);

    if (indexOfService == -1) {
      throw new IllegalArgumentException("this service does not exist");
    }

    if (newPrice < 0) {
      throw new IllegalArgumentException("incorrect newPrice");
    }

    serviceDao.update(new Service(nameOfService, newPrice), indexOfService);
  }

  public void showPriceOfServicesAndRoomsByCriterion(ServiceAndRoomSortingCriteria criterion) {

    switch (criterion) {

      case SECTION -> {
        showCurrentServices();
        for (HotelRoom hotelRoom : hotelRoomDao.getAll()) {
          System.out.println("Hotel room: " + hotelRoom.getNumberOfRoom() + ": " + hotelRoom.getPrice());
        }
      }
      case PRICE -> {
        List<Service> services = new ArrayList<>(serviceDao.getAll());
        List<HotelRoom> hotelRooms = new ArrayList<>(hotelRoomDao.getAll());

        services.sort(Comparator.comparing(Service::getPrice));
        hotelRooms.sort(Comparator.comparing(HotelRoom::getPrice));

        for (Service service : services) {
          System.out.println(service.toString());
        }

        for (HotelRoom hotelRoom : hotelRooms) {
          System.out.println("Hotel room: " + hotelRoom.getNumberOfRoom() + ": " + hotelRoom.getPrice());
        }
      }
    }
  }

  private int findIndexOfService(String name) {
    int indexOfService = -1;

    for (int i = 0; i < serviceDao.getAll().size(); i++) {
      if (serviceDao.read(i).getName().equals(name)) {
        indexOfService = i;
        break;
      }
    }
    return indexOfService;
  }
}
