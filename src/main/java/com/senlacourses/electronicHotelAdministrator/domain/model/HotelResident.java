package com.senlacourses.electronicHotelAdministrator.domain.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "hotelresident", schema = "eha")
public class HotelResident implements Serializable {

  @Id
  @NotNull
  private int passportNumber;
  @NotNull
  private String fullName;

  @Override
  public String toString() {
    return "full name - " + fullName + ", passport number - " + passportNumber;
  }
}
