package com.senlacourses.electronicHotelAdministrator.dao;

import com.senlacourses.electronicHotelAdministrator.domain.model.RegistrationCard;
import org.springframework.stereotype.Repository;

@Repository
public class RegistrationCardDao extends AbstractHibernateDao<RegistrationCard> implements IGenericDao<RegistrationCard>{

  public RegistrationCardDao() {
    super(RegistrationCard.class);
  }
}
