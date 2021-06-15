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
@Table(name = "service", schema = "eha")
@Embeddable
public class Service implements Serializable {

  @Id
  private String name;
  private int price;

  public Service() {}

  @Override
  public String toString() {
    return name + ": " + price;
  }
}
