package com.senlaCourses.adPlacementSystem.dao;

import com.senlaCourses.adPlacementSystem.dao.interfaces.IProfileDao;
import com.senlaCourses.adPlacementSystem.domain.model.Profile;
import org.springframework.stereotype.Repository;

/**
 * Class for data access Profile.class object.
 */
@Repository
public class ProfileDao extends AbstractHibernateDao<Profile> implements IProfileDao {

  public ProfileDao() {
    super(Profile.class);
  }
}
