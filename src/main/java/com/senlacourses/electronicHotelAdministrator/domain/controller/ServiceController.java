package com.senlacourses.electronicHotelAdministrator.domain.controller;

import com.senlacourses.electronicHotelAdministrator.domain.controller.interfaces.IServiceController;
import com.senlacourses.electronicHotelAdministrator.domain.model.Service;
import com.senlacourses.electronicHotelAdministrator.domain.service.criteriaForSorting.ServiceAndRoomSortingCriteria;
import com.senlacourses.electronicHotelAdministrator.domain.service.interfaces.IServiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ServiceController implements IServiceController {

  private final IServiceService serviceService;

  public ServiceController(IServiceService serviceService) {
    this.serviceService = serviceService;
  }

  @Override
  @PostMapping(value = "/services")
  public ResponseEntity<?> addNewService(@RequestBody Service service) {
    serviceService.addNewService(service);

    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @Override
  @GetMapping(value = "/services")
  public ResponseEntity<List<Service>> showCurrentServices() {
    List<Service> services = serviceService.showCurrentServices();

    return services != null
            ? new ResponseEntity<>(services, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @Override
  @PutMapping(value = "/services/{newPrice}")
  public ResponseEntity<Service> changeServicePrice(@RequestBody String nameOfService,
                                                    @PathVariable(value = "newPrice") int newPrice) {
    Service service = serviceService.changeServicePrice(nameOfService, newPrice);

    return service != null
            ? new ResponseEntity<>(service, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @Override
  @GetMapping(value = "/services/criterion")
  public ResponseEntity<?> showPriceOfServicesAndRoomsByCriterion(@RequestBody String criterionForSorting) {

    ServiceAndRoomSortingCriteria criterion = ServiceAndRoomSortingCriteria.valueOf(criterionForSorting.toUpperCase());
    Map<String, List> listMap = serviceService.showPriceOfServicesAndRoomsByCriterion(criterion);

    return listMap != null
            ? new ResponseEntity<>(listMap, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
