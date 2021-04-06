package com.senlacourses.electronicHotelAdministrator.dao;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    saveDataBase();
  }

  @Override
  public T read(int id) {
    return dataBase.get(id);
  }

  @Override
  public void update(T t, int id) {
    dataBase.set(id, t);
    saveDataBase();
  }

  @Override
  public void delete(T t) {
    dataBase.remove(t);
    saveDataBase();
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

  private void saveDataBase() {
    String fileName = "src/data.bin";

    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
      oos.writeObject(HotelResidentDao.getInstance().getAll());
      oos.writeObject(HotelRoomDao.getInstance().getAll());
      oos.writeObject(RegistrationCardDao.getInstance().getAll());
      oos.writeObject(ServiceDao.getInstance().getAll());

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
