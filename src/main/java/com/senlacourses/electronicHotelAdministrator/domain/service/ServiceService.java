package com.senlacourses.electronicHotelAdministrator.domain.service;

import com.senlacourses.electronicHotelAdministrator.dao.IGenericDao;
import com.senlacourses.electronicHotelAdministrator.domain.model.HotelRoom;
import com.senlacourses.electronicHotelAdministrator.domain.model.Service;
import com.senlacourses.electronicHotelAdministrator.domain.model.criteriaForSorting.ServiceAndRoomSortingCriteria;
import com.senlacourses.electronicHotelAdministrator.domain.service.interfaces.IServiceService;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceService implements IServiceService {

  private final static Logger logger = LoggerFactory.getLogger(ServiceService.class);
  private final IGenericDao<Service> serviceDao;
  private final IGenericDao<HotelRoom> hotelRoomDao;

  public ServiceService(IGenericDao<Service> serviceDao, IGenericDao<HotelRoom> hotelRoomDao) {
    this.serviceDao = serviceDao;
    this.hotelRoomDao = hotelRoomDao;
  }

  @Override
  public void addNewService(String name, int price) {
    Service service = new Service();
    service.setName(name);
    service.setPrice(price);
    serviceDao.create(service);
  }

  @Override
  public void showCurrentServices() {
    serviceDao.getAll().forEach(service -> System.out.println(service.toString()));
  }

  @Override
  public void changeServicePrice(String nameOfService, int newPrice) {
    Service service = serviceDao.read(nameOfService);

    if (service == null) {
      logger.error("IllegalArgumentException(\"this service does not exist\")");
      throw new IllegalArgumentException("this service does not exist");
    }

    if (newPrice < 0) {
      logger.error("IllegalArgumentException(\"incorrect newPrice\")");
      throw new IllegalArgumentException("incorrect newPrice");
    }

    service.setPrice(newPrice);

    serviceDao.update(service);
  }

  @Override
  public void showPriceOfServicesAndRoomsByCriterion(ServiceAndRoomSortingCriteria criterion) {

    switch (criterion) {

      case SECTION:
        showCurrentServices();
        hotelRoomDao.getAll().forEach(hotelRoom -> System.out.println("Hotel room: "
            + hotelRoom.getNumberOfRoom() + ": " + hotelRoom.getPrice()));
        break;

      case PRICE:
        List<Service> services = new ArrayList<>(serviceDao.getAll());
        List<HotelRoom> hotelRooms = new ArrayList<>(hotelRoomDao.getAll());

        services.sort(Comparator.comparing(Service::getPrice));
        hotelRooms.sort(Comparator.comparing(HotelRoom::getPrice));

        services.forEach(service -> System.out.println(service.toString()));

        hotelRooms.forEach(hotelRoom -> System.out.println("Hotel room: "
            + hotelRoom.getNumberOfRoom() + ": " + hotelRoom.getPrice()));
        break;
    }
  }
}
