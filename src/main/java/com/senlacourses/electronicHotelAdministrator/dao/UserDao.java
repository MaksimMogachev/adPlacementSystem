package com.senlacourses.electronicHotelAdministrator.dao;

import com.senlacourses.electronicHotelAdministrator.dao.interfaces.IUserDao;
import com.senlacourses.electronicHotelAdministrator.domain.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends AbstractHibernateDao<User> implements IUserDao {

  public UserDao() {
    super(User.class);
  }
}
