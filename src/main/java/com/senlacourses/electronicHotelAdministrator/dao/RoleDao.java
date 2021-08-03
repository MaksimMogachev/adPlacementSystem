package com.senlacourses.electronicHotelAdministrator.dao;

import com.senlacourses.electronicHotelAdministrator.dao.interfaces.IRoleDao;
import com.senlacourses.electronicHotelAdministrator.domain.model.Role;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDao extends AbstractHibernateDao<Role> implements IRoleDao {

  public RoleDao() {
    super(Role.class);
  }
}
