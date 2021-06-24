package com.senlacourses.electronicHotelAdministrator.dao;

import com.senlacourses.electronicHotelAdministrator.annotations.Singleton;
import com.senlacourses.electronicHotelAdministrator.domain.model.HotelResident;

import java.io.Serializable;

@Singleton
public class HotelResidentDao<T extends Serializable>
        extends AbstractHibernateDao<T> implements IGenericDao<T>{

  public HotelResidentDao() {
    super((Class<T>) HotelResident.class);
  }
}
