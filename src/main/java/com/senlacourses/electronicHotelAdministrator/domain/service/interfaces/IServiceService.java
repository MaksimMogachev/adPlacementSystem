package com.senlacourses.electronicHotelAdministrator.domain.service.interfaces;

import com.senlacourses.electronicHotelAdministrator.domain.model.criteriaForSorting.ServiceAndRoomSortingCriteria;

public interface IServiceService {

  void addNewService(String name, int price);

  void showCurrentServices();

  void changeServicePrice(String nameOfService, int newPrice);

  void showPriceOfServicesAndRoomsByCriterion(ServiceAndRoomSortingCriteria criterion);
}
