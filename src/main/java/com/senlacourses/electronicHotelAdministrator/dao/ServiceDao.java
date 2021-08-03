package com.senlacourses.electronicHotelAdministrator.dao;

import com.senlacourses.electronicHotelAdministrator.dao.interfaces.IServiceDao;
import com.senlacourses.electronicHotelAdministrator.domain.model.Service;
import org.springframework.stereotype.Repository;

@Repository
public class ServiceDao extends AbstractHibernateDao<Service> implements IServiceDao {

  public ServiceDao() {
    super(Service.class);
  }
}
