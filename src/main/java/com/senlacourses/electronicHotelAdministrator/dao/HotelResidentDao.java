package com.senlacourses.electronicHotelAdministrator.dao;

import com.senlacourses.electronicHotelAdministrator.domain.model.HotelResident;
import org.springframework.stereotype.Repository;

@Repository
public class HotelResidentDao extends AbstractHibernateDao<HotelResident> implements IGenericDao<HotelResident>{

  public HotelResidentDao() {
    super(HotelResident.class);
  }
}
