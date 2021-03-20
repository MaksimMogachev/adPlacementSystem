package com.senlacourses.electronicHotelAdministrator.ui;

public record MenuItem(String title, IAction action, Menu nextMenu) {

  public void doAction() {
    this.action.execute();
  }
}
