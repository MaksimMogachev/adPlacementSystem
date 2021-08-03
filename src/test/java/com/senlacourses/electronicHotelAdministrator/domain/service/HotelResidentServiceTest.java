package com.senlacourses.electronicHotelAdministrator.domain.service;

import com.senlacourses.electronicHotelAdministrator.dao.interfaces.IHotelResidentDao;
import com.senlacourses.electronicHotelAdministrator.domain.dto.request.HotelResidentDto;
import com.senlacourses.electronicHotelAdministrator.domain.model.HotelResident;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class HotelResidentServiceTest {

  @Mock
  private IHotelResidentDao hotelResidentDao;
  @InjectMocks
  private HotelResidentService hotelResidentService;

  @Test
  public void getAllResidentsShouldReturnEmptyList() {
    assertThat(hotelResidentService.getAllResidents(),
            is(new ArrayList<HotelResident>(){}));
  }

  @Test
  public void addNewResident() {
    String fullName = "asd";
    int passportNumber = 123;
    HotelResidentDto hotelResidentDto = new HotelResidentDto(passportNumber, fullName);

    HotelResident hotelResident = new HotelResident();
    hotelResident.setFullName(hotelResidentDto.getFullName());
    hotelResident.setPassportNumber(hotelResidentDto.getPassportNumber());

    hotelResidentService.addNewResident(hotelResidentDto);

    ArgumentCaptor<HotelResident> captor = ArgumentCaptor.forClass(HotelResident.class);
    Mockito.verify(hotelResidentDao).create(captor.capture());

    assertThat(captor.getValue(), is(hotelResident));
  }

  @Test
  public void addNewResidentShouldThrowIllegalArgExc() {
    String fullName = "asd";
    int passportNumber = 123;
    HotelResidentDto hotelResidentDto = new HotelResidentDto(passportNumber, fullName);

    when(hotelResidentDao.read(hotelResidentDto.getPassportNumber()))
            .thenReturn(new HotelResident());

    assertThrows(IllegalArgumentException.class, () ->
            hotelResidentService.addNewResident(hotelResidentDto));
  }

  @Test
  public void removeResidentShouldReturnTrue() {
    int passportNumber = 123;

    when(hotelResidentDao.read(passportNumber))
            .thenReturn(new HotelResident())
            .thenReturn(null);

    assertTrue(hotelResidentService.removeResident(passportNumber));
  }

  @Test
  public void removeResidentShouldThrowIllegalArgExc() {
    int passportNumber = 123;

    assertThrows(IllegalArgumentException.class, () ->
            hotelResidentService.removeResident(passportNumber));
  }
}
