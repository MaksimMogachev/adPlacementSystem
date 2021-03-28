package com.senlacourses.electronicHotelAdministrator.domain.service;

import com.senlacourses.electronicHotelAdministrator.Main;
import com.senlacourses.electronicHotelAdministrator.dao.HotelResidentDao;
import com.senlacourses.electronicHotelAdministrator.domain.model.HotelResident;
import com.senlacourses.electronicHotelAdministrator.domain.service.interfaces.IHotelResidentService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HotelResidentService implements IHotelResidentService {

  private HotelResidentDao hotelResidentDao = HotelResidentDao.getInstance();
  private final static Logger logger = LoggerFactory.getLogger(Main.class);

  public List<HotelResident> getResidents() {
    return hotelResidentDao.getAll();
  }

  @Override
  public void showAllResidents() {
    hotelResidentDao.getAll().forEach(hotelResident -> System.out.println(hotelResident.toString()));
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
