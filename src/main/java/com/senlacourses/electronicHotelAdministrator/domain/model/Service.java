package com.senlacourses.electronicHotelAdministrator.domain.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@Entity
@Table(name = "service", schema = "eha")
@Embeddable
public class Service implements Serializable {

  @Id
  @NotNull
  private String name;
  @NotNull
  @Min(0)
  private int price;

  public Service() {}

  @Override
  public String toString() {
    return name + ": " + price;
  }
}
