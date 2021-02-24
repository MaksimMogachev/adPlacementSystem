package com.senlacourses.lecture3.electronicHotelAdministrator;

import java.util.ArrayList;
import java.util.List;

public class CheckInRegistration {

  private HotelRoom hotelRoom;
  private List<HotelResident> residents = new ArrayList<>();
  private List<Service> services = new ArrayList<>();

  public CheckInRegistration(HotelRoom hotelRoom, HotelResident hotelResident) {
    this.hotelRoom = hotelRoom;
    this.residents.add(hotelResident);
  }

  public HotelRoom getHotelRoom() {
    return hotelRoom;
  }

  public void setHotelRoom(HotelRoom hotelRoom) {
    this.hotelRoom = hotelRoom;
  }

  public List<HotelResident> getResidents() {
    return residents;
  }

  public void setResidents(List<HotelResident> residents) {
    this.residents = residents;
  }

  public List<Service> getServices() {
    return services;
  }

  public void setServices(List<Service> services) {
    this.services = services;
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder.append("Hotel room: ").append(getHotelRoom().getNumberOfRoom())
        .append("; Room residents: ");
    for (HotelResident hotelResident : residents) {
      stringBuilder.append(hotelResident.toString());
    }

    if (services.size() != 0) {
      stringBuilder.append("; Services: ");
      for (Service service : services) {
        stringBuilder.append(service.toString());
      }
    }

    return stringBuilder.toString();
  }
}
