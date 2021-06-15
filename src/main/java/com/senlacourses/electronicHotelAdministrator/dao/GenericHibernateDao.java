package com.senlacourses.electronicHotelAdministrator.dao;

import java.io.Serializable;

public class GenericHibernateDao<T extends Serializable>
        extends AbstractHibernateDao<T> implements IGenericDao<T>{

  public GenericHibernateDao(Class<T> clazz) {
    super(clazz);
  }
}
