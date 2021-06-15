package com.senlacourses.electronicHotelAdministrator.domain.model;

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
  private int numberOfStars;
  private int roomCapacity;
  private int price;
  private RoomCondition roomCondition;
  private boolean roomIsOccupied;
  @ElementCollection
  @CollectionTable(name = "last_residents_rooms", schema = "eha", joinColumns = @JoinColumn(name = "numberofroom"))
  @Column(name = "residentinformation")
  private List<String> lastResidents = new ArrayList<>();

  public HotelRoom() {}

  public void setPrice(int price) {
    if (price < 0) {
      throw new IllegalArgumentException("Incorrect parameter");
    }
    this.price = price;
  }

  public void setNumberOfStars(int numberOfStars) {
    if (numberOfStars < 0) {
      throw new IllegalArgumentException("Incorrect parameter");
    }
    this.numberOfStars = numberOfStars;
  }

  public void setRoomCapacity(int roomCapacity) {
    if (roomCapacity < 1) {
      throw new IllegalArgumentException("Incorrect parameter");
    }
    this.roomCapacity = roomCapacity;
  }

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
