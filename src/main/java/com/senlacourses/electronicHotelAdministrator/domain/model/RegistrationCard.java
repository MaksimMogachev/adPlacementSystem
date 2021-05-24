package com.senlacourses.electronicHotelAdministrator.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class RegistrationCard implements Serializable {

  private HotelRoom hotelRoom;
  private List<HotelResident> residents = new ArrayList<>();
  private Map<LocalDateTime, Service> services = new TreeMap<>();
  private LocalDate checkInDate;
  private LocalDate departureDate;

  public RegistrationCard(HotelRoom hotelRoom, HotelResident hotelResident, int daysOfStay) {
    this.hotelRoom = hotelRoom;
    this.residents.add(hotelResident);
    this.checkInDate = LocalDate.now();
    departureDate = checkInDate.plusDays(daysOfStay);
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

  public Map<LocalDateTime, Service> getServices() {
    return services;
  }

  public void setServices(Map<LocalDateTime, Service> services) {
    this.services = services;
  }

  public LocalDate getCheckInDate() {
    return checkInDate;
  }

  public void setCheckInDate(LocalDate arrivalDate) {
    this.checkInDate = arrivalDate;
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

    stringBuilder
        .append("Hotel room: ")
        .append(getHotelRoom().getNumberOfRoom())
        .append("; Room residents: ");
    for (HotelResident hotelResident : residents) {
      stringBuilder.append(hotelResident.toString()).append("; ");
    }

    if (services.size() != 0) {
      stringBuilder.append("Services: ");
      for (Map.Entry<LocalDateTime, Service> service : services.entrySet()) {
        stringBuilder.append(service.getValue().toString()).append(", ");
        stringBuilder
            .append(service.getKey().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
            .append("; ");
      }
    }
    stringBuilder
        .append("Arrival date: ")
        .append(checkInDate)
        .append("; Departure date: ")
        .append(departureDate);

    return stringBuilder.toString();
  }
}
