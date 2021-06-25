package com.senlacourses.electronicHotelAdministrator.domain.controller;

import com.senlacourses.electronicHotelAdministrator.domain.controller.interfaces.IServiceController;
import com.senlacourses.electronicHotelAdministrator.domain.model.criteriaForSorting.ServiceAndRoomSortingCriteria;
import com.senlacourses.electronicHotelAdministrator.domain.service.interfaces.IServiceService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
@Component
public class ServiceController implements IServiceController {

  private final IServiceService serviceService;

  public ServiceController(IServiceService serviceService) {
    this.serviceService = serviceService;
  }

  @Override
  public void addNewService(String name, int price) {
    serviceService.addNewService(name, price);
  }

  @Override
  public void showCurrentServices() {
    serviceService.showCurrentServices();
  }

  @Override
  public void changeServicePrice(String nameOfService, int newPrice) {
    serviceService.changeServicePrice(nameOfService, newPrice);
  }

  @Override
  public void showPriceOfServicesAndRoomsByCriterion(ServiceAndRoomSortingCriteria criterion) {
    serviceService.showPriceOfServicesAndRoomsByCriterion(criterion);
  }
}
