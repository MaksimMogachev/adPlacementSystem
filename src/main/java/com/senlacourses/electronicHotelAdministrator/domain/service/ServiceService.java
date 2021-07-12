package com.senlacourses.electronicHotelAdministrator.domain.service;

import com.senlacourses.electronicHotelAdministrator.dao.IGenericDao;
import com.senlacourses.electronicHotelAdministrator.domain.model.HotelRoom;
import com.senlacourses.electronicHotelAdministrator.domain.model.Service;
import com.senlacourses.electronicHotelAdministrator.domain.service.criteriaForSorting.ServiceAndRoomSortingCriteria;
import com.senlacourses.electronicHotelAdministrator.domain.service.interfaces.IServiceService;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;

@org.springframework.stereotype.Service
public class ServiceService implements IServiceService {

  private final static Logger logger = LoggerFactory.getLogger(ServiceService.class);
  private final IGenericDao<Service> serviceDao;
  private final IGenericDao<HotelRoom> hotelRoomDao;

  public ServiceService(IGenericDao<Service> serviceDao, IGenericDao<HotelRoom> hotelRoomDao) {
    this.serviceDao = serviceDao;
    this.hotelRoomDao = hotelRoomDao;
  }

  @Transactional
  @Override
  public void addNewService(String name, int price) {
    Service service = new Service();
    service.setName(name);
    service.setPrice(price);
    serviceDao.create(service);
  }

  @Override
  public List<Service> showCurrentServices() {
    return serviceDao.getAll();
  }

  @Transactional
  @Override
  public Service changeServicePrice(String nameOfService, int newPrice) {
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
    return service;
  }

  @Override
  public Map<String, List> showPriceOfServicesAndRoomsByCriterion(ServiceAndRoomSortingCriteria criterion) {
    Map<String, List> listMap = new HashMap<>();

    switch (criterion) {

      case SECTION:
        listMap.put("services", showCurrentServices());
        listMap.put("hotel rooms", hotelRoomDao.getAll());
        return listMap;

      case PRICE:
        List<Service> services = showCurrentServices();
        List<HotelRoom> hotelRooms = new ArrayList<>(hotelRoomDao.getAll());

        services.sort(Comparator.comparing(Service::getPrice));
        hotelRooms.sort(Comparator.comparing(HotelRoom::getPrice));

        listMap.put("services", services);
        listMap.put("hotel rooms", hotelRooms);
        return listMap;
    }
    return null;
  }
}
