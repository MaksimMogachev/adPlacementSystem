package com.senlacourses.electronicHotelAdministrator.dao.interfaces;

import java.io.Serializable;
import java.util.List;

public interface IGenericDao<T extends Serializable> {

  List<T> getAll();

  void create(T t);

  <R> T read(R id);

  void update(T t);

  void delete(T t);
}
