package com.senlacourses.lecture3.electronicHotelAdministrator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class CheckInRegistration {

  private HotelRoom hotelRoom;
  private List<HotelResident> residents = new ArrayList<>();
  private List<Service> services = new ArrayList<>();
  private Calendar arrivalDate;
  private Calendar departureDate;

  public CheckInRegistration(HotelRoom hotelRoom, HotelResident hotelResident, GregorianCalendar arrivalDate, int daysOfStay) {
    this.hotelRoom = hotelRoom;
    this.residents.add(hotelResident);
    this.arrivalDate = arrivalDate;
    departureDate = new GregorianCalendar(this.arrivalDate.get(Calendar.YEAR),
        this.arrivalDate.get(Calendar.MONTH),
        this.arrivalDate.get(Calendar.DAY_OF_MONTH) + daysOfStay);
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

  public Calendar getCheckInDate() {
    return arrivalDate;
  }

  public void setCheckInDate(Calendar arrivalDate) {
    this.arrivalDate = arrivalDate;
  }

  public Calendar getDepartureDate() {
    return departureDate;
  }

  public void setDepartureDate(Calendar departureDate) {
    this.departureDate = departureDate;
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
    stringBuilder.append("; Arrival date: ").append(arrivalDate.getTime())
        .append("; Departure date: ").append(departureDate.getTime());

    return stringBuilder.toString();
  }
}
