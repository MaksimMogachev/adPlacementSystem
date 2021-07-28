package com.senlacourses.electronicHotelAdministrator.dao;

import com.senlacourses.electronicHotelAdministrator.domain.model.Role;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDao extends AbstractHibernateDao<Role> implements IGenericDao<Role> {

  public RoleDao() {
    super(Role.class);
  }
}
