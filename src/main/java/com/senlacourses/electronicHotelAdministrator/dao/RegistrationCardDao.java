package com.senlacourses.electronicHotelAdministrator.dao;

import com.senlacourses.electronicHotelAdministrator.dao.interfaces.IRegistrationCardDao;
import com.senlacourses.electronicHotelAdministrator.domain.model.RegistrationCard;
import org.springframework.stereotype.Repository;

@Repository
public class RegistrationCardDao extends AbstractHibernateDao<RegistrationCard> implements IRegistrationCardDao {

  public RegistrationCardDao() {
    super(RegistrationCard.class);
  }
}
