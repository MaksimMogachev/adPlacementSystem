package com.senlacourses.electronicHotelAdministrator.domain.controller.interfaces;

import com.senlacourses.electronicHotelAdministrator.domain.model.criteriaForSorting.ServiceAndRoomSortingCriteria;

public interface IServiceController {

  void addNewService(String name, int price);

  void showCurrentServices();

  void changeServicePrice(String nameOfService, int newPrice);

  void showPriceOfServicesAndRoomsByCriterion(ServiceAndRoomSortingCriteria criterion);
}
