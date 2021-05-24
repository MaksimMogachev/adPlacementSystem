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
    assert dataBase != null;
    dataBase.add(t);
    saveDataBase();
  }

  @Override
  public T read(int id) {
    assert dataBase != null;
    return dataBase.get(id);
  }

  @Override
  public void update(T t, int id) {
    assert dataBase != null;
    dataBase.set(id, t);
    saveDataBase();
  }

  @Override
  public void delete(T t) {
    assert dataBase != null;
    dataBase.remove(t);
    saveDataBase();
  }
  
  private List<T> readDataBase() {
    String className = this.getClass().getSimpleName();
    String hotelResidentData = "src/hotelResidentData.bin";
    String hotelRoomData = "src/hotelRoomData.bin";
    String registrationCardData = "src/registrationCardData.bin";
    String serviceData = "src/serviceData.bin";

    switch (className) {

      case "HotelResidentDao": {
        return getDataBase(className, hotelResidentData);
      }

      case "HotelRoomDao": {
        return getDataBase(className, hotelRoomData);
      }

      case "RegistrationCardDao": {
        return getDataBase(className, registrationCardData);
      }

      case "ServiceDao": {
        return getDataBase(className, serviceData);
      }
    }
  return null;
  }

  private List<T> getDataBase(String className, String fileName) {
    List<T> dataBaseForRead = null;
    
    try (ObjectInputStream ois = new ObjectInputStream(
        new FileInputStream(fileName))) {
      dataBaseForRead = (ArrayList<T>) ois.readObject();
    } catch (IOException | ClassNotFoundException e) {
      if (e instanceof EOFException) {
        System.out
            .println("No data was found for the \"" + className + "\" list. New list created");
        return new ArrayList<>();
      }
      e.printStackTrace();
    }
    return dataBaseForRead;
  }

  private void saveDataBase() {
    String className = this.getClass().getSimpleName();
    String hotelResidentData = "src/hotelResidentData.bin";
    String hotelRoomData = "src/hotelRoomData.bin";
    String registrationCardData = "src/registrationCardData.bin";
    String serviceData = "src/serviceData.bin";

    switch (className) {

      case "HotelResidentDao": {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(hotelResidentData))) {
          oos.writeObject(this.getAll());
        } catch (IOException e) {
          e.printStackTrace();
        }
        break;
      }

      case "HotelRoomDao": {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(hotelRoomData))) {
          oos.writeObject(this.getAll());
        } catch (IOException e) {
          e.printStackTrace();
        }
        break;
      }

      case "RegistrationCardDao": {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(registrationCardData))) {
          oos.writeObject(this.getAll());
        } catch (IOException e) {
          e.printStackTrace();
        }
        break;
      }

      case "ServiceDao": {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(serviceData))) {
          oos.writeObject(this.getAll());
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      break;
    }
  }
}
