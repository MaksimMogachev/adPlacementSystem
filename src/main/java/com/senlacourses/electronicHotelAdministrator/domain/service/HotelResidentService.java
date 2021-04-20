package com.senlacourses.electronicHotelAdministrator.domain.service;

import com.senlacourses.electronicHotelAdministrator.dao.HotelResidentDao;
import com.senlacourses.electronicHotelAdministrator.domain.model.HotelResident;
import com.senlacourses.electronicHotelAdministrator.domain.service.interfaces.IHotelResidentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HotelResidentService implements IHotelResidentService {

  private static final Logger logger = LoggerFactory.getLogger(HotelResidentService.class);
  private final HotelResidentDao hotelResidentDao = HotelResidentDao.getInstance();

  @Override
  public void showAllResidents() {
    hotelResidentDao
        .getAll()
        .forEach(hotelResident -> System.out.println(hotelResident.toString()));
  }

  @Override
  public void addNewResident(String fullName, int passportNumber) {
    hotelResidentDao.create(new HotelResident(fullName, passportNumber));
  }

  @Override
  public void removeResident(String fullName) {
    int indexOfResident = findIndexOfResident(fullName);

    if (indexOfResident == -1) {
      logger.error("IllegalArgumentException(\"Incorrect argument\")");
      throw new IllegalArgumentException("Incorrect argument");
    }

    hotelResidentDao.delete(hotelResidentDao.read(indexOfResident));
  }

  private int findIndexOfResident(String name) {
    int indexOfResident = -1;

    for (int i = 0; i < hotelResidentDao.getAll().size(); i++) {
      if (hotelResidentDao.read(i).fullName().equals(name)) {
        indexOfResident = i;
        break;
      }
    }
    return indexOfResident;
  }
}
