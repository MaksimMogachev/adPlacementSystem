package com.senlacourses.electronicHotelAdministrator.hibernate;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {

  private static final EntityManagerFactory managerFactory =
          Persistence.createEntityManagerFactory("eha");

  public static EntityManagerFactory getManagerFactory() {
    return managerFactory;
  }
}
