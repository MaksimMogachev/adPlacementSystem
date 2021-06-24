package com.senlacourses.electronicHotelAdministrator.dao;

import com.senlacourses.electronicHotelAdministrator.annotations.Singleton;
import com.senlacourses.electronicHotelAdministrator.domain.model.HotelRoom;
import java.io.Serializable;

@Singleton
public class HotelRoomDao<T extends Serializable>
        extends AbstractHibernateDao<T> implements IGenericDao<T>{

  public HotelRoomDao() {
    super((Class<T>) HotelRoom.class);
  }
}
