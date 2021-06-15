package com.senlacourses.electronicHotelAdministrator.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "hotelresident", schema = "eha")
@Embeddable
public class HotelResident implements Serializable {

  @Id
  private int passportNumber;
  private String fullName;

  public HotelResident() {}

  @Override
  public String toString() {
    return "full name - " + fullName + ", passport number - " + passportNumber;
  }
}
