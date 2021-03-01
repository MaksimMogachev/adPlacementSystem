package com.senlacourses.electronicHotelAdministrator.dao;

import java.util.List;

public interface Dao<T> {

  List<T> getAll();

  void create(T t);

  T read(long id);

  void update(T t, int id);

  void delete(T t);
}
