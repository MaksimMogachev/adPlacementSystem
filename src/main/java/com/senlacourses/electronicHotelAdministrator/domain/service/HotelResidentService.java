package com.senlacourses.electronicHotelAdministrator.domain.service;

import com.senlacourses.electronicHotelAdministrator.dao.interfaces.IHotelResidentDao;
import com.senlacourses.electronicHotelAdministrator.domain.dto.request.HotelResidentDto;
import com.senlacourses.electronicHotelAdministrator.domain.model.HotelResident;
import com.senlacourses.electronicHotelAdministrator.domain.service.interfaces.IHotelResidentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class HotelResidentService implements IHotelResidentService {

  private static final Logger logger = LoggerFactory.getLogger(HotelResidentService.class);
  private final IHotelResidentDao hotelResidentDao;

  public HotelResidentService(IHotelResidentDao hotelResidentDao) {
    this.hotelResidentDao = hotelResidentDao;
  }

  @Override
  public List<HotelResident> getAllResidents() {
    return hotelResidentDao
        .getAll();
  }

  @Transactional
  @Override
  public void addNewResident(HotelResidentDto hotelResidentDto) {
    if (hotelResidentDao.read(hotelResidentDto.getPassportNumber()) != null) {
      logger.error("IllegalArgumentException(\"this resident already exists\")");
      throw new IllegalArgumentException("this resident already exists");
    }

    HotelResident hotelResident = new HotelResident();
    hotelResident.setFullName(hotelResidentDto.getFullName());
    hotelResident.setPassportNumber(hotelResidentDto.getPassportNumber());

    hotelResidentDao.create(hotelResident);
  }

  @Transactional
  @Override
  public boolean removeResident(int passportNumber) {
    HotelResident hotelResident = hotelResidentDao.read(passportNumber);

    if (hotelResident == null) {
      logger.error("IllegalArgumentException(\"Incorrect argument\")");
      throw new IllegalArgumentException("Incorrect argument");
    }

    hotelResidentDao.delete(hotelResident);

    return hotelResidentDao.read(passportNumber) == null;
  }
}
