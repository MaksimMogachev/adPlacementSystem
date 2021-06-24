package com.senlacourses.electronicHotelAdministrator.dao;

import com.senlacourses.electronicHotelAdministrator.annotations.Singleton;
import com.senlacourses.electronicHotelAdministrator.domain.model.Service;
import java.io.Serializable;

@Singleton
public class ServiceDao<T extends Serializable>
        extends AbstractHibernateDao<T> implements IGenericDao<T>{

  public ServiceDao() {
    super((Class<T>) Service.class);
  }
}
