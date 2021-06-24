package com.senlacourses.electronicHotelAdministrator.dao;

import com.senlacourses.electronicHotelAdministrator.annotations.Singleton;
import com.senlacourses.electronicHotelAdministrator.domain.model.RegistrationCard;
import java.io.Serializable;

@Singleton
public class RegistrationCardDao<T extends Serializable>
        extends AbstractHibernateDao<T> implements IGenericDao<T>{

  public RegistrationCardDao() {
    super((Class<T>) RegistrationCard.class);
  }
}
