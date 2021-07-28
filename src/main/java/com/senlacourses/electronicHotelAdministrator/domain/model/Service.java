package com.senlacourses.electronicHotelAdministrator.domain.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
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

  @Override
  public String toString() {
    return name + ": " + price;
  }
}
