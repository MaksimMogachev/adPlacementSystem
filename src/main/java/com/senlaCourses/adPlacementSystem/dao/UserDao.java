package com.senlaCourses.adPlacementSystem.dao;

import com.senlaCourses.adPlacementSystem.dao.interfaces.IUserDao;
import com.senlaCourses.adPlacementSystem.domain.model.User;
import org.springframework.stereotype.Repository;

/**
 * Class for data access User.class object.
 */
@Repository
public class UserDao extends AbstractHibernateDao<User> implements IUserDao {

  public UserDao() {
    super(User.class);
  }
}
