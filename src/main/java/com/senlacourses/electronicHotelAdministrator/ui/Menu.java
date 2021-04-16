package com.senlacourses.electronicHotelAdministrator.ui;

public class Menu {

  private String name;
  private MenuItem[] menuItems;

  private Menu(String name, MenuItem[] menuItems) {
    this.name = name;
    this.menuItems = menuItems;
  }

  public MenuItem[] getMenuItems() {
    return menuItems;
  }

  public void setMenuItems(MenuItem[] menuItems) {
    this.menuItems = menuItems;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public static class Builder {

    private String name;
    private MenuItem[] menuItems;


    public Builder() {}

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder menuItems(MenuItem[] menuItems) {
      this.menuItems = menuItems;
      return this;
    }

    public Menu build() {
      return new Menu(this.name, this.menuItems);
    }
  }
}
