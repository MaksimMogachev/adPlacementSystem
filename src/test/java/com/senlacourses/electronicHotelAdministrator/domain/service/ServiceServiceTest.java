package com.senlacourses.electronicHotelAdministrator.domain.service;

import com.senlacourses.electronicHotelAdministrator.dao.interfaces.IHotelRoomDao;
import com.senlacourses.electronicHotelAdministrator.dao.interfaces.IServiceDao;
import com.senlacourses.electronicHotelAdministrator.domain.dto.request.ServiceDto;
import com.senlacourses.electronicHotelAdministrator.domain.model.HotelRoom;
import com.senlacourses.electronicHotelAdministrator.domain.model.Service;
import com.senlacourses.electronicHotelAdministrator.domain.service.criteriaForSorting.ServiceAndRoomSortingCriteria;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class ServiceServiceTest {

  @Mock
  private IServiceDao serviceDao;
  @Mock
  private IHotelRoomDao hotelRoomDao;
  @InjectMocks
  ServiceService serviceService;

  @Test
  void addNewService() {
    ServiceDto serviceDto = new ServiceDto("service", 123);
    Service service = new Service();
    service.setName(serviceDto.getName());
    service.setPrice(serviceDto.getPrice());

    serviceService.addNewService(serviceDto);

    ArgumentCaptor<Service> captor = ArgumentCaptor.forClass(Service.class);
    verify(serviceDao).create(captor.capture());

    assertThat(captor.getValue(), is(service));
  }

  @Test
  void addNewServiceShouldThrowIllegalArgExc() {
    when(serviceDao.read(any(String.class))).thenReturn(new Service());

    assertThrows(IllegalArgumentException.class, () ->
            serviceService.addNewService(new ServiceDto("service", 123)));
  }

  @Test
  void getCurrentServices() {
    assertThat(serviceService.getCurrentServices(), is(new ArrayList<Service>()));
  }

  @Test
  void changeServicePrice() {
    String nameOfService = "service";
    int newPrice = 123;

    Service serviceForInput = new Service();
    serviceForInput.setName(nameOfService);
    serviceForInput.setPrice(1234);

    Service serviceForResult = new Service();
    serviceForResult.setName(nameOfService);
    serviceForResult.setPrice(newPrice);

    when(serviceDao.read(any(String.class))).thenReturn(serviceForInput);

    assertThat(serviceService.changeServicePrice(nameOfService, newPrice),
            is(serviceForResult));

    assertThat(serviceService.changeServicePrice(null, newPrice),
            nullValue());
  }

  @Test
  void changeServicePriceShouldThrowIllegalArgExc() {
    when(serviceDao.read(any(String.class)))
            .thenReturn(null)
            .thenReturn(new Service());

    assertThrows(IllegalArgumentException.class, () ->
            serviceService.changeServicePrice("service", 123));
    assertThrows(IllegalArgumentException.class, () ->
            serviceService.changeServicePrice("service", -123));
  }

  @Test
  void showPriceOfServicesAndRoomsByCriterion() {
    ServiceAndRoomSortingCriteria criteria = ServiceAndRoomSortingCriteria.PRICE;

    Service service1 = new Service();
    service1.setPrice(5);
    Service service2 = new Service();
    service2.setPrice(1);
    Service service3 = new Service();
    service3.setPrice(3);

    HotelRoom hotelRoom1 = new HotelRoom();
    hotelRoom1.setPrice(4);
    HotelRoom hotelRoom2 = new HotelRoom();
    hotelRoom2.setPrice(2);

    Map<String, List> listMapForResult = new HashMap<>();
    listMapForResult.put("services", Arrays.asList(service2, service3, service1));
    listMapForResult.put("hotel rooms", Arrays.asList(hotelRoom2, hotelRoom1));

    when(serviceDao.getAll()).thenReturn(Arrays.asList(service1, service2, service3));
    when(hotelRoomDao.getAll()).thenReturn(Arrays.asList(hotelRoom1, hotelRoom2));

    assertThat(serviceService.showPriceOfServicesAndRoomsByCriterion(criteria), is(listMapForResult));
  }
}