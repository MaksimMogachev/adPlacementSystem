package com.senlacourses.electronicHotelAdministrator.dao;

import com.senlacourses.electronicHotelAdministrator.domain.model.HotelRoom;
import org.springframework.stereotype.Repository;

@Repository
public class HotelRoomDao extends AbstractHibernateDao<HotelRoom> implements IGenericDao<HotelRoom>{

  public HotelRoomDao() {
    super(HotelRoom.class);
  }
}
