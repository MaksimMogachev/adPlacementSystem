package com.senlacourses.electronicHotelAdministrator.dao;

import com.senlacourses.electronicHotelAdministrator.domain.model.Service;
import org.springframework.stereotype.Repository;

@Repository
public class ServiceDao extends AbstractHibernateDao<Service> implements IGenericDao<Service>{

  public ServiceDao() {
    super(Service.class);
  }
}
