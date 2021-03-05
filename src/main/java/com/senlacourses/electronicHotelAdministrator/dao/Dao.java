package com.senlacourses.electronicHotelAdministrator.dao;

import java.util.ArrayList;
import java.util.List;

public abstract class Dao<T> {

  private List<T> dataBase = new ArrayList<>();

  public List<T> getAll() {
    return getClone();
  }

  public void create(T t) {
    dataBase.add(t);
  }

  public T read(int id) {
    return getClone().get(id);
  }

  public void update(T t, int id) {
    dataBase.set(id, t);
  }

  public void delete(T t) {
    dataBase.remove(t);
  }

  private List<T> getClone() {
    return new ArrayList<T>(dataBase);
  }
}
