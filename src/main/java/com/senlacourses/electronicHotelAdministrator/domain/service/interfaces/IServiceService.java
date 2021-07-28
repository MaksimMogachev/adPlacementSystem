package com.senlacourses.electronicHotelAdministrator.domain.service.interfaces;

import com.senlacourses.electronicHotelAdministrator.domain.dto.request.ServiceDto;
import com.senlacourses.electronicHotelAdministrator.domain.model.Service;
import com.senlacourses.electronicHotelAdministrator.domain.service.criteriaForSorting.ServiceAndRoomSortingCriteria;

import java.util.List;
import java.util.Map;

public interface IServiceService {

  void addNewService(ServiceDto serviceDto);

  List<Service> getCurrentServices();

  Service changeServicePrice(String nameOfService, int newPrice);

  Map<String, List> showPriceOfServicesAndRoomsByCriterion(ServiceAndRoomSortingCriteria criterion);
}
