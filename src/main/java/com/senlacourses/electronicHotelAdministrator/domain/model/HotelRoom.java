package com.senlacourses.electronicHotelAdministrator.domain.model;

import jakarta.validation.constraints.Min;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "hotelroom", schema = "eha")
public class HotelRoom implements Serializable {

  @Id
  private int numberOfRoom;
  @Min(0)
  private int numberOfStars;
  @Min(1)
  private int roomCapacity;
  @Min(0)
  private int price;
  private RoomCondition roomCondition;
  private boolean roomIsOccupied;
  @ElementCollection
  @CollectionTable(name = "last_residents_rooms", schema = "eha", joinColumns = @JoinColumn(name = "numberofroom"))
  @Column(name = "residentinformation")
  private List<String> lastResidents = new ArrayList<>();

  public HotelRoom() {}

  @Override
  public String toString() {
    return (" Number of room: "
        + getNumberOfRoom()
        + "; Number of stars: "
        + getNumberOfStars()
        + "; Room capacity: "
        + getRoomCapacity()
        + "; Price: "
        + getPrice()
        + (roomCondition != null ? "; Room condition: " + getRoomCondition() : "")
        + (roomIsOccupied ? "; Room is occupied now" : "; Room is not occupied now"));
  }
}
