package com.senlacourses.electronicHotelAdministrator.domain.service;

import com.senlacourses.electronicHotelAdministrator.dao.IGenericDao;
import com.senlacourses.electronicHotelAdministrator.domain.model.HotelResident;
import com.senlacourses.electronicHotelAdministrator.domain.service.interfaces.IHotelResidentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HotelResidentService implements IHotelResidentService {

  private static final Logger logger = LoggerFactory.getLogger(HotelResidentService.class);
  private final IGenericDao<HotelResident> hotelResidentDao;

  public HotelResidentService(IGenericDao<HotelResident> hotelResidentDao) {
    this.hotelResidentDao = hotelResidentDao;
  }

  @Override
  public void showAllResidents() {
    hotelResidentDao
        .getAll()
        .forEach(hotelResident -> System.out.println(hotelResident.toString()));
  }

  @Override
  public void addNewResident(String fullName, int passportNumber) {
    HotelResident hotelResident = new HotelResident();
    hotelResident.setFullName(fullName);
    hotelResident.setPassportNumber(passportNumber);
    hotelResidentDao.create(hotelResident);
  }

  @Override
  public void removeResident(int passportNumber) {
    HotelResident hotelResident = hotelResidentDao.read(passportNumber);

    if (hotelResident == null) {
      logger.error("IllegalArgumentException(\"Incorrect argument\")");
      throw new IllegalArgumentException("Incorrect argument");
    }

    hotelResidentDao.delete(hotelResident);
  }
}
