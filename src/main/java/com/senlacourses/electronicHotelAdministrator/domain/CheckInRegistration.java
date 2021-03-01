package com.senlacourses.electronicHotelAdministrator.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CheckInRegistration {

  private HotelRoom hotelRoom;
  private List<HotelResident> residents = new ArrayList<>();
  private List<Service> services = new ArrayList<>();
  private LocalDate arrivalDate;
  private LocalDate departureDate;

  public CheckInRegistration(HotelRoom hotelRoom, HotelResident hotelResident, LocalDate arrivalDate, int daysOfStay) {
    this.hotelRoom = hotelRoom;
    this.residents.add(hotelResident);
    this.arrivalDate = arrivalDate;
    departureDate = arrivalDate.plusDays(daysOfStay);
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

  public LocalDate getCheckInDate() {
    return arrivalDate;
  }

  public void setCheckInDate(LocalDate arrivalDate) {
    this.arrivalDate = arrivalDate;
  }

  public LocalDate getDepartureDate() {
    return departureDate;
  }

  public void setDepartureDate(LocalDate departureDate) {
    this.departureDate = departureDate;
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder.append("Hotel room: ").append(getHotelRoom().getNumberOfRoom())
        .append("; Room residents: ");
    for (HotelResident hotelResident : residents) {
      stringBuilder.append(hotelResident.toString());
      if (hotelResident != residents.get(residents.size() - 1)) {
        stringBuilder.append("; ");
      }
    }

    if (services.size() != 0) {
      stringBuilder.append("; Services: ");
      for (Service service : services) {
        stringBuilder.append(service.toString());
      }
    }
    stringBuilder.append("; Arrival date: ").append(arrivalDate)
        .append("; Departure date: ").append(departureDate);

    return stringBuilder.toString();
  }
}
