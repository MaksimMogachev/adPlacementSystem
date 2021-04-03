package com.senlacourses.electronicHotelAdministrator.dao;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public abstract class Dao<T> implements IDao<T> {

  private final List<T> dataBase = readDataBase();

  @Override
  public List<T> getAll() {
    return dataBase;
  }

  @Override
  public void create(T t) {
    dataBase.add(t);
  }

  @Override
  public T read(int id) {
    return dataBase.get(id);
  }

  @Override
  public void update(T t, int id) {
    dataBase.set(id, t);
  }

  @Override
  public void delete(T t) {
    dataBase.remove(t);
  }
  
  private List<T> readDataBase() {
    List<T> dataBaseForRead = null;
    String fileName = "src/data.bin";

    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
      dataBaseForRead = (ArrayList<T>) ois.readObject();
    } catch (IOException | ClassNotFoundException e) {

      if (e.getClass() == EOFException.class) {
        System.out.println("No data was found for the \"" + this.getClass().getSimpleName() + "\" list. New list created");
        return new ArrayList<>();
      }
      e.printStackTrace();
    }
    return dataBaseForRead;
  }
}
