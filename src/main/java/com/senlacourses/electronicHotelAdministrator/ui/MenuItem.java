package com.senlacourses.electronicHotelAdministrator.ui;

import java.util.Objects;

public final class MenuItem {
  private final String title;
  private final IAction action;
  private final Menu nextMenu;

  public MenuItem(String title, IAction action, Menu nextMenu) {
    this.title = title;
    this.action = action;
    this.nextMenu = nextMenu;
  }

  public String title() {
    return title;
  }

  public IAction action() {
    return action;
  }

  public Menu nextMenu() {
    return nextMenu;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) return true;
    if (obj == null || obj.getClass() != this.getClass()) return false;
    var that = (MenuItem) obj;
    return Objects.equals(this.title, that.title) &&
            Objects.equals(this.action, that.action) &&
            Objects.equals(this.nextMenu, that.nextMenu);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, action, nextMenu);
  }

  @Override
  public String toString() {
    return "MenuItem[" +
            "title=" + title + ", " +
            "action=" + action + ", " +
            "nextMenu=" + nextMenu + ']';
  }

  public void doAction() {
    this.action.execute();
  }
}
