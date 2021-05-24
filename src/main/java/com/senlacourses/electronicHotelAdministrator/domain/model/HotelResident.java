package com.senlacourses.electronicHotelAdministrator.domain.model;

import java.io.Serializable;
import java.util.Objects;

public final class HotelResident implements Serializable {
  private final String fullName;
  private final int passportNumber;

  public HotelResident(String fullName, int passportNumber) {
    this.fullName = fullName;
    this.passportNumber = passportNumber;
  }

  public String fullName() {
    return fullName;
  }

  public int passportNumber() {
    return passportNumber;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) return true;
    if (obj == null || obj.getClass() != this.getClass()) return false;
    var that = (HotelResident) obj;
    return Objects.equals(this.fullName, that.fullName) &&
            this.passportNumber == that.passportNumber;
  }

  @Override
  public int hashCode() {
    return Objects.hash(fullName, passportNumber);
  }

  @Override
  public String toString() {
    return "full name - " + fullName + ", passport number - " + passportNumber;
  }
}
