package com.senlacourses.electronicHotelAdministrator.domain.service;

import com.senlacourses.electronicHotelAdministrator.dao.interfaces.IHotelResidentDao;
import com.senlacourses.electronicHotelAdministrator.dao.interfaces.IHotelRoomDao;
import com.senlacourses.electronicHotelAdministrator.dao.interfaces.IRegistrationCardDao;
import com.senlacourses.electronicHotelAdministrator.dao.interfaces.IServiceDao;
import com.senlacourses.electronicHotelAdministrator.domain.dto.request.RegistrationCardDto;
import com.senlacourses.electronicHotelAdministrator.domain.model.HotelResident;
import com.senlacourses.electronicHotelAdministrator.domain.model.HotelRoom;
import com.senlacourses.electronicHotelAdministrator.domain.model.RegistrationCard;
import com.senlacourses.electronicHotelAdministrator.domain.model.Service;
import com.senlacourses.electronicHotelAdministrator.domain.service.criteriaForSorting.OccupiedRoomSortingCriteria;
import com.senlacourses.electronicHotelAdministrator.domain.service.criteriaForSorting.ServiceSortingCriteria;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class RegistrationCardServiceTest {

  @Mock
  private IRegistrationCardDao registrationCardDao;
  @Mock
  private IHotelResidentDao hotelResidentDao;
  @Mock
  private IHotelRoomDao hotelRoomDao;
  @Mock
  private IServiceDao serviceDao;
  @InjectMocks
  private RegistrationCardService registrationCardService;

  @Test
  void getOccupiedRooms() {
    assertThat(registrationCardService.getOccupiedRooms(),
            is(new ArrayList<RegistrationCard>()));
  }

  @Test
  void createNewCard() {
    RegistrationCardDto registrationCardDto =
            new RegistrationCardDto(1, LocalDate.now(), LocalDate.now().plusDays(10));
    RegistrationCard registrationCard = new RegistrationCard();
    registrationCard.setHotelRoom(registrationCardDto.getHotelRoom());
    registrationCard.setCheckInDate(registrationCardDto.getCheckInDate());
    registrationCard.setDepartureDate(registrationCardDto.getDepartureDate());

    when(hotelRoomDao.read(registrationCardDto.getHotelRoom())).thenReturn(new HotelRoom());

    registrationCardService.createNewCard(registrationCardDto);

    ArgumentCaptor<RegistrationCard> captor = ArgumentCaptor.forClass(RegistrationCard.class);
    verify(registrationCardDao).create(captor.capture());

    assertThat(captor.getValue(), is(registrationCard));
  }

  @Test
  void createNewCardShouldThrow2IllegalArgExc() {
    RegistrationCardDto registrationCardDto =
            new RegistrationCardDto(1, LocalDate.now(), LocalDate.now().plusDays(10));

    when(hotelRoomDao.read(any(Integer.class)))
            .thenReturn(null)
            .thenReturn(new HotelRoom());

    when(registrationCardDao.read(any(Integer.class)))
            .thenReturn(new RegistrationCard());


    assertThrows(IllegalArgumentException.class, () ->
            registrationCardService.createNewCard(registrationCardDto));
    assertThrows(IllegalArgumentException.class, () ->
            registrationCardService.createNewCard(registrationCardDto));
  }

  @Test
  void putInTheRoom() {
    int numberOfRoom = 1;
    int passportNumber = 123;
    HotelRoom hotelRoomForInput = new HotelRoom();
    hotelRoomForInput.setNumberOfRoom(numberOfRoom);
    hotelRoomForInput.setRoomCapacity(1);

    HotelRoom hotelRoomForResult = new HotelRoom();
    hotelRoomForResult.setNumberOfRoom(numberOfRoom);
    hotelRoomForResult.setRoomCapacity(1);
    hotelRoomForResult.setRoomIsOccupied(true);

    HotelResident hotelResident = new HotelResident();
    hotelResident.setPassportNumber(passportNumber);
    hotelResident.setFullName("abc");

    RegistrationCard registrationCardForInput = new RegistrationCard();
    registrationCardForInput.setHotelRoom(numberOfRoom);

    RegistrationCard registrationCardForResult = new RegistrationCard();
    registrationCardForResult.setHotelRoom(numberOfRoom);
    registrationCardForResult.setResidents(Collections.singletonList(hotelResident));

    when(hotelRoomDao.read(numberOfRoom)).thenReturn(hotelRoomForInput);
    when(registrationCardDao.read(numberOfRoom)).thenReturn(registrationCardForInput);
    when(hotelResidentDao.read(passportNumber)).thenReturn(hotelResident);

    assertThat(registrationCardService.putInTheRoom(numberOfRoom, passportNumber),
            is(registrationCardForResult));

    ArgumentCaptor<HotelRoom> captor = ArgumentCaptor.forClass(HotelRoom.class);
    verify(hotelRoomDao).update(captor.capture());

    assertThat(captor.getValue(), is(hotelRoomForResult));
  }

  @Test
  void putInTheRoomShouldThrowUnsupportedOperationExcAnd2IllegalArgExc() {
    int numberOfRoom = 1;
    int passportNumber = 123;

    when(registrationCardDao.read(any(Integer.class)))
            .thenReturn(null)
            .thenReturn(new RegistrationCard());

    when(hotelResidentDao.read(any(Integer.class)))
            .thenReturn(null)
            .thenReturn(null)
            .thenReturn(new HotelResident());

    assertThrows(UnsupportedOperationException.class, () ->
            registrationCardService.putInTheRoom(numberOfRoom, passportNumber));

    assertThrows(IllegalArgumentException.class, () ->
            registrationCardService.putInTheRoom(numberOfRoom, passportNumber));

    assertThrows(IllegalArgumentException.class, () ->
            registrationCardService.putInTheRoom(numberOfRoom, passportNumber));
  }

  @Test
  void evictFromTheRoom() {
    int numberOfRoom = 1;
    int indexOfResidentInRoom = 0;
    HotelRoom hotelRoomForInput = new HotelRoom();
    hotelRoomForInput.setNumberOfRoom(numberOfRoom);

    HotelResident hotelResident1 = new HotelResident();
    hotelResident1.setPassportNumber(123);
    hotelResident1.setFullName("abc");
    HotelResident hotelResident2 = new HotelResident();
    hotelResident2.setPassportNumber(1234);
    hotelResident2.setFullName("abcd");

    HotelRoom hotelRoomForResult = new HotelRoom();
    hotelRoomForResult.setNumberOfRoom(numberOfRoom);
    hotelRoomForResult.setLastResidents(
            Collections.singletonList("full name - abc, passport number - 123; 2021-08-03 - 2021-08-18"));

    RegistrationCard registrationCardForInput = new RegistrationCard();
    registrationCardForInput.setHotelRoom(numberOfRoom);
    registrationCardForInput.setCheckInDate(LocalDate.now());
    registrationCardForInput.setDepartureDate(LocalDate.now().plusDays(15));
    List<HotelResident> list = new ArrayList<>();
    list.add(hotelResident1);
    list.add(hotelResident2);
    registrationCardForInput.setResidents(list);

    RegistrationCard registrationCardForResult = new RegistrationCard();
    registrationCardForResult.setHotelRoom(numberOfRoom);
    registrationCardForResult.setCheckInDate(LocalDate.now());
    registrationCardForResult.setDepartureDate(LocalDate.now().plusDays(15));
    registrationCardForResult.setResidents(Collections.singletonList(hotelResident2));

    when(hotelRoomDao.read(any(Integer.class))).thenReturn(hotelRoomForInput);
    when(registrationCardDao.read(any(Integer.class))).thenReturn(registrationCardForInput);

    assertThat(registrationCardService.evictFromTheRoom(numberOfRoom, indexOfResidentInRoom),
            is(registrationCardForResult));

    ArgumentCaptor<HotelRoom> captor = ArgumentCaptor.forClass(HotelRoom.class);
    verify(hotelRoomDao).update(captor.capture());

    assertThat(captor.getValue(), is(hotelRoomForResult));
  }

  @Test
  void evictFromTheRoomShouldThrow3IllegalArgExc() {
    int numberOfRoom = 1;
    int passportNumber = 0;
    RegistrationCard registrationCard = new RegistrationCard();
    registrationCard.setResidents(Collections.singletonList(new HotelResident()));

    when(hotelRoomDao.read(any(Integer.class)))
            .thenReturn(null)
            .thenReturn(new HotelRoom());
    when(registrationCardDao.read(any(Integer.class)))
            .thenReturn(null)
            .thenReturn(null)
            .thenReturn(registrationCard);

    assertThrows(IllegalArgumentException.class, () ->
            registrationCardService.evictFromTheRoom(numberOfRoom, passportNumber));
    assertThrows(IllegalArgumentException.class, () ->
            registrationCardService.evictFromTheRoom(numberOfRoom, passportNumber));
    assertThrows(IllegalArgumentException.class, () ->
            registrationCardService.evictFromTheRoom(numberOfRoom, passportNumber));
  }

  @Test
  void testEvictFromTheRoom() {
    int numberOfRoom = 1;
    HotelRoom hotelRoomForInput = new HotelRoom();
    hotelRoomForInput.setNumberOfRoom(numberOfRoom);

    HotelResident hotelResident1 = new HotelResident();
    hotelResident1.setPassportNumber(123);
    hotelResident1.setFullName("abc");
    HotelResident hotelResident2 = new HotelResident();
    hotelResident2.setPassportNumber(1234);
    hotelResident2.setFullName("abcd");

    HotelRoom hotelRoomForResult = new HotelRoom();
    hotelRoomForResult.setNumberOfRoom(numberOfRoom);
    hotelRoomForResult.setLastResidents(
            Arrays.asList("full name - abc, passport number - 123; 2021-08-03 - 2021-08-18",
                          "full name - abcd, passport number - 1234; 2021-08-03 - 2021-08-18"));

    RegistrationCard registrationCard = new RegistrationCard();
    registrationCard.setHotelRoom(numberOfRoom);
    registrationCard.setCheckInDate(LocalDate.now());
    registrationCard.setDepartureDate(LocalDate.now().plusDays(15));
    registrationCard.setResidents(Arrays.asList(hotelResident1, hotelResident2));

    when(hotelRoomDao.read(any(Integer.class))).thenReturn(hotelRoomForInput);
    when(registrationCardDao.read(any(Integer.class))).thenReturn(registrationCard);

    registrationCardService.evictFromTheRoom(numberOfRoom);

    ArgumentCaptor<HotelRoom> captor = ArgumentCaptor.forClass(HotelRoom.class);
    verify(hotelRoomDao).update(captor.capture());

    assertThat(captor.getValue(), is(hotelRoomForResult));

    ArgumentCaptor<RegistrationCard> captor2 = ArgumentCaptor.forClass(RegistrationCard.class);
    verify(registrationCardDao).delete(captor2.capture());

    assertThat(captor2.getValue(), is(registrationCard));
  }

  @Test
  void testEvictFromTheRoomShouldThrow3IllegalArgExc() {
    int numberOfRoom = 1;

    when(hotelRoomDao.read(any(Integer.class)))
            .thenReturn(null)
            .thenReturn(new HotelRoom());

    assertThrows(IllegalArgumentException.class, () ->
            registrationCardService.evictFromTheRoom(numberOfRoom));
    assertThrows(IllegalArgumentException.class, () ->
            registrationCardService.evictFromTheRoom(numberOfRoom));
  }

  @Test
  void addServiceToOccupiedRoom() {
    int numberOfRoom = 1;
    String nameOfService = "someName";

    Service serviceForInput = new Service();
    serviceForInput.setName(nameOfService);

    RegistrationCard registrationCardForResult = new RegistrationCard();
    registrationCardForResult.setServices(new TreeMap<>(){{
      put(LocalDateTime.now(), serviceForInput);
    }});

    when(registrationCardDao.read(any(Integer.class))).thenReturn(new RegistrationCard());
    when(serviceDao.read(any(String.class))).thenReturn(serviceForInput);

    for (LocalDateTime localDateTime : registrationCardService
            .addServiceToOccupiedRoom(numberOfRoom, nameOfService)
            .getServices().keySet()) {
      assertThat(localDateTime.toLocalDate(), is(LocalDate.now()));
    }
    for (Service service : registrationCardService
            .addServiceToOccupiedRoom(numberOfRoom, nameOfService)
            .getServices().values()) {
      assertThat(service, is(serviceForInput));
    }
  }

  @Test
  void addServiceToOccupiedRoomShouldThrow3IllegalArgExc() {
    int numberOfRoom = 1;
    String nameOfService = "someName";

    when(registrationCardDao.read(any(Integer.class)))
            .thenReturn(null)
            .thenReturn(new RegistrationCard());

    assertThrows(IllegalArgumentException.class, () ->
            registrationCardService.addServiceToOccupiedRoom(numberOfRoom, nameOfService));
    assertThrows(IllegalArgumentException.class, () ->
            registrationCardService.addServiceToOccupiedRoom(numberOfRoom, nameOfService));
  }

  @Test
  void showOccupiedRoomsByCriterion() {
    OccupiedRoomSortingCriteria sortingCriteria = OccupiedRoomSortingCriteria.ROOM_RELEASE_DATE;

    RegistrationCard registrationCard1 = new RegistrationCard();
    registrationCard1.setDepartureDate(LocalDate.now().plusDays(5));
    RegistrationCard registrationCard2 = new RegistrationCard();
    registrationCard2.setDepartureDate(LocalDate.now().plusDays(6));
    RegistrationCard registrationCard3 = new RegistrationCard();
    registrationCard3.setDepartureDate(LocalDate.now().plusDays(4));

    List<RegistrationCard> listResult = new ArrayList<>(Arrays.asList(
            registrationCard3,
            registrationCard1,
            registrationCard2));

    when(registrationCardDao.getAll()).thenReturn(Arrays.asList(
            registrationCard1,
            registrationCard2,
            registrationCard3));

    assertThat(registrationCardService.showOccupiedRoomsByCriterion(sortingCriteria), is(listResult));
  }

  @Test
  void showNumberOfCurrentResidents() {
    RegistrationCard registrationCard1 = new RegistrationCard();
    registrationCard1.setResidents(Arrays.asList(new HotelResident(), new HotelResident()));
    RegistrationCard registrationCard2 = new RegistrationCard();
    registrationCard2.setResidents(Arrays.asList(new HotelResident(), new HotelResident()));
    RegistrationCard registrationCard3 = new RegistrationCard();
    registrationCard3.setResidents(Collections.singletonList(new HotelResident()));

    when(registrationCardDao.getAll()).thenReturn(Arrays.asList(
            registrationCard1,
            registrationCard2,
            registrationCard3));

    assertThat(registrationCardService.showNumberOfCurrentResidents(),
            is("Total number of current residents: 5"));
  }

  @Test
  void showAmountOfPayment() {
    int daysOfStay = 5;

    HotelRoom hotelRoom = new HotelRoom();
    hotelRoom.setPrice(100);

    when(hotelRoomDao.read(any(Integer.class))).thenReturn(hotelRoom);

    assertThat(registrationCardService.showAmountOfPayment(1, daysOfStay),
            is("Amount of payment for the room: 500"));
  }

  @Test
  void showResidentServicesByCriterion() {
    int passportNumber = 123;
    ServiceSortingCriteria criteria = ServiceSortingCriteria.DATE;
    LocalDateTime time = LocalDateTime.now();

    HotelResident hotelResident1 = new HotelResident();
    hotelResident1.setPassportNumber(passportNumber);
    HotelResident hotelResident2 = new HotelResident();
    hotelResident2.setPassportNumber(1234);

    RegistrationCard registrationCard1 = new RegistrationCard();
    registrationCard1.setResidents(Arrays.asList(new HotelResident(), hotelResident2));
    registrationCard1.setServices(new TreeMap<>(){{
      put(time.plusDays(2), new Service());
      put(time.plusDays(4), new Service());
    }});
    RegistrationCard registrationCard2 = new RegistrationCard();
    registrationCard2.setResidents(Arrays.asList(hotelResident1, new HotelResident()));
    registrationCard2.setServices(new TreeMap<>(){{
      put(time.plusDays(5), new Service());
      put(time, new Service());
    }});
    RegistrationCard registrationCard3 = new RegistrationCard();
    registrationCard3.setResidents(Collections.singletonList(new HotelResident()));
    registrationCard3.setServices(new TreeMap<>(){{
      put(time.plusDays(2), new Service());
      put(time.plusDays(3), new Service());
    }});

    Map<LocalDateTime, Service> map = new TreeMap<>(){{
      put(time.plusDays(5), new Service());
      put(time, new Service());
    }};


    when(registrationCardDao.getAll()).thenReturn(Arrays.asList(
            registrationCard1,
            registrationCard2,
            registrationCard3));

    assertThat(registrationCardService.showResidentServicesByCriterion(passportNumber, criteria),
            is(map.entrySet().toArray()));
  }

  @Test
  void showResidentServicesByCriterionShouldThrow2IllegalArgExc() {
    int passportNumber = 123;
    ServiceSortingCriteria criteria = ServiceSortingCriteria.DATE;

    HotelResident hotelResident1 = new HotelResident();
    hotelResident1.setPassportNumber(1234);
    HotelResident hotelResident2 = new HotelResident();
    hotelResident2.setPassportNumber(123);

    RegistrationCard registrationCard1 = new RegistrationCard();
    registrationCard1.setResidents(Collections.singletonList(hotelResident1));
    RegistrationCard registrationCard2 = new RegistrationCard();
    registrationCard2.setResidents(Collections.singletonList(hotelResident2));

    when(registrationCardDao.getAll())
            .thenReturn(Collections.singletonList(registrationCard1))
            .thenReturn(Collections.singletonList(registrationCard2));

    assertThrows(IllegalArgumentException.class, () ->
            registrationCardService.showResidentServicesByCriterion(passportNumber, criteria));
    assertThrows(IllegalArgumentException.class, () ->
            registrationCardService.showResidentServicesByCriterion(passportNumber, criteria));
  }
}