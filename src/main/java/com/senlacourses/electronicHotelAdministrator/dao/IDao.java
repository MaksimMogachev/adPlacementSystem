package com.senlacourses.electronicHotelAdministrator.dao;

import java.util.List;

public interface IDao<T> {

  List<T> getAll();

  void create(T t);

  T read(int id);

  void update(T t, int id);

  void delete(T t);
}
