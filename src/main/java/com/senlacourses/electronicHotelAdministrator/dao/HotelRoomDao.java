package com.senlacourses.electronicHotelAdministrator.dao;

import com.senlacourses.electronicHotelAdministrator.dao.interfaces.IHotelRoomDao;
import com.senlacourses.electronicHotelAdministrator.domain.model.HotelRoom;
import org.springframework.stereotype.Repository;

@Repository
public class HotelRoomDao extends AbstractHibernateDao<HotelRoom> implements IHotelRoomDao {

  public HotelRoomDao() {
    super(HotelRoom.class);
  }
}
