package com.senlacourses.electronicHotelAdministrator.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "registrationcard", schema = "eha")
public class RegistrationCard implements Serializable {

  @Id
  private int hotelRoom;
  @ElementCollection
  @CollectionTable(name = "residents_cards", schema = "eha")
  private List<HotelResident> residents = new ArrayList<>();
  @ElementCollection
  @CollectionTable(name = "cards_services", schema = "eha")
  private Map<LocalDateTime, Service> services = new TreeMap<>();
  @NotNull
  private LocalDate checkInDate;
  @NotNull
  private LocalDate departureDate;

  public RegistrationCard(int numberOfRoom) {
    this.hotelRoom = numberOfRoom;
  }

  public RegistrationCard() {}

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder
        .append("Hotel room: ")
        .append(getHotelRoom())
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
