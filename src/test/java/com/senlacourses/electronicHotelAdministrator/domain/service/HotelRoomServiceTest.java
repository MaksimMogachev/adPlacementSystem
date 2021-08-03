package com.senlacourses.electronicHotelAdministrator.domain.service;

import com.senlacourses.electronicHotelAdministrator.dao.interfaces.IHotelRoomDao;
import com.senlacourses.electronicHotelAdministrator.dao.interfaces.IRegistrationCardDao;
import com.senlacourses.electronicHotelAdministrator.domain.dto.request.HotelRoomDto;
import com.senlacourses.electronicHotelAdministrator.domain.model.HotelRoom;
import com.senlacourses.electronicHotelAdministrator.domain.model.RegistrationCard;
import com.senlacourses.electronicHotelAdministrator.domain.model.RoomCondition;
import com.senlacourses.electronicHotelAdministrator.domain.service.criteriaForSorting.RoomSortingCriteria;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class HotelRoomServiceTest {

  @Mock
  private IHotelRoomDao hotelRoomDao;
  @Mock
  private IRegistrationCardDao registrationCardDao;
  @InjectMocks
  private HotelRoomService hotelRoomService;

  @Test
  public void getAllRooms() {
    assertThat(hotelRoomService.getAllRooms(),
            is(new ArrayList<HotelRoom>(){}));
  }

  @Test
  public void addNewRoom() {
    int numberOfRoom = 1;
    int numberOfStars = 1;
    int roomCapacity = 1;
    int roomPrice = 100;
    HotelRoomDto hotelRoomDto = new HotelRoomDto(numberOfRoom, numberOfStars, roomCapacity, roomPrice);

    HotelRoom hotelRoom = new HotelRoom();
    hotelRoom.setNumberOfRoom(hotelRoomDto.getNumberOfRoom());
    hotelRoom.setNumberOfStars(hotelRoomDto.getNumberOfStars());
    hotelRoom.setRoomCapacity(hotelRoomDto.getRoomCapacity());
    hotelRoom.setPrice(hotelRoomDto.getPrice());

    hotelRoomService.addNewRoom(hotelRoomDto);

    ArgumentCaptor<HotelRoom> captor = ArgumentCaptor.forClass(HotelRoom.class);
    verify(hotelRoomDao).create(captor.capture());

    assertThat(captor.getValue(), is(hotelRoom));
  }

  @Test
  public void addNewRoomShouldThrowIllegalArgExc() {
    int numberOfRoom = 1;
    int numberOfStars = 1;
    int roomCapacity = 1;
    int roomPrice = 100;
    HotelRoomDto hotelRoomDto = new HotelRoomDto(numberOfRoom, numberOfStars, roomCapacity, roomPrice);

    when(hotelRoomDao.read(hotelRoomDto.getNumberOfRoom())).thenReturn(new HotelRoom());

    assertThrows(IllegalArgumentException.class, () ->
            hotelRoomService.addNewRoom(hotelRoomDto));
  }

  @Test
  public void changeRoomCondition() {
    int numberOfRoom = 1;
    RoomCondition roomCondition = RoomCondition.MAINTAINED;

    HotelRoom hotelRoom = new HotelRoom();
    hotelRoom.setRoomCondition(roomCondition);

    when(hotelRoomDao.read(numberOfRoom)).thenReturn(new HotelRoom());

    assertThat(hotelRoomService.changeRoomCondition(numberOfRoom, roomCondition),
            is(hotelRoom));
  }

  @Test
  public void changeRoomConditionShouldThrowIllegalArgExc() {
    int numberOfRoom = 1;
    RoomCondition roomCondition = RoomCondition.MAINTAINED;

    assertThrows(IllegalArgumentException.class, () ->
            hotelRoomService.changeRoomCondition(numberOfRoom, roomCondition));
  }

  @Test
  public void changeRoomConditionShouldThrowUnsupportedOperationExc() {
    int numberOfRoom = 1;
    RoomCondition roomCondition = RoomCondition.MAINTAINED;

    HotelRoom hotelRoom = new HotelRoom();
    hotelRoom.setRoomIsOccupied(true);

    when(hotelRoomDao.read(numberOfRoom)).thenReturn(hotelRoom);

    assertThrows(UnsupportedOperationException.class, () ->
            hotelRoomService.changeRoomCondition(numberOfRoom, roomCondition));
  }

  @Test
  public void changeRoomPrice() {
    int numberOfRoom = 1;
    int newPrice = 5;

    HotelRoom hotelRoom = new HotelRoom();
    hotelRoom.setPrice(5);

    when(hotelRoomDao.read(numberOfRoom)).thenReturn(new HotelRoom());

    assertThat(hotelRoomService.changeRoomPrice(numberOfRoom, newPrice),
            is(hotelRoom));
  }

  @Test
  public void changeRoomPriceShouldThrow2IllegalArgExc() {
    int numberOfRoom = 1;
    int newPrice = -5;

    when(hotelRoomDao.read(numberOfRoom))
            .thenReturn(null)
            .thenReturn(new HotelRoom());

    assertThrows(IllegalArgumentException.class, () ->
            hotelRoomService.changeRoomPrice(numberOfRoom, newPrice));

    assertThrows(IllegalArgumentException.class, () ->
            hotelRoomService.changeRoomPrice(numberOfRoom, newPrice));
  }

  @Test
  public void showNumberOfFreeRooms() {
    String str = "Total number of free rooms: 1";

    when(hotelRoomDao.getAll()).thenReturn(new ArrayList<>(
            Arrays.asList(new HotelRoom(), new HotelRoom())));

    when(registrationCardDao.getAll()).thenReturn(new ArrayList<>(
            Collections.singletonList(new RegistrationCard())
    ));

    assertThat(hotelRoomService.showNumberOfFreeRooms(), is(str));
  }

  @Test
  public void showAllRoomsByCriterion() {
    HotelRoom hotelRoom1 = new HotelRoom();
    hotelRoom1.setPrice(5);
    HotelRoom hotelRoom2 = new HotelRoom();
    hotelRoom2.setPrice(1);
    HotelRoom hotelRoom3 = new HotelRoom();
    hotelRoom3.setPrice(3);

    List<HotelRoom> hotelRoomsForInput = new ArrayList<>(Arrays.asList(hotelRoom1, hotelRoom2, hotelRoom3));
    List<HotelRoom> hotelRoomsForResult = new ArrayList<>(Arrays.asList(hotelRoom2, hotelRoom3, hotelRoom1));
    RoomSortingCriteria roomSortingCriteria = RoomSortingCriteria.PRICE;

    when(hotelRoomDao.getAll()).thenReturn(hotelRoomsForInput);

    assertThat(hotelRoomService
            .showAllRoomsByCriterion(roomSortingCriteria), is(hotelRoomsForResult));
  }

  @Test
  public void showFreeRoomsByCriterion() {
    HotelRoom hotelRoom1 = new HotelRoom();
    hotelRoom1.setPrice(5);
    HotelRoom hotelRoom2 = new HotelRoom();
    hotelRoom2.setRoomIsOccupied(true);
    hotelRoom2.setPrice(1);
    HotelRoom hotelRoom3 = new HotelRoom();
    hotelRoom3.setPrice(3);

    List<HotelRoom> hotelRoomsForInput = new ArrayList<>(Arrays.asList(hotelRoom1, hotelRoom2, hotelRoom3));
    List<HotelRoom> hotelRoomsForResult = new ArrayList<>(Arrays.asList(hotelRoom3, hotelRoom1));
    RoomSortingCriteria roomSortingCriteria = RoomSortingCriteria.PRICE;

    when(hotelRoomDao.getAll()).thenReturn(hotelRoomsForInput);

    assertThat(hotelRoomService
            .showFreeRoomsByCriterion(roomSortingCriteria), is(hotelRoomsForResult));
  }

  @Test
  public void showRoomsByDate() {
    int year = 2021;
    int month = 7;
    int dayOfMonth = 20;
    RegistrationCard registrationCard1 = new RegistrationCard();
    registrationCard1.setDepartureDate(LocalDate.of(2021, 6, 20));
    registrationCard1.setHotelRoom(1);
    RegistrationCard registrationCard2 = new RegistrationCard();
    registrationCard2.setDepartureDate(LocalDate.of(2021, 8, 20));
    registrationCard2.setHotelRoom(2);
    RegistrationCard registrationCard3 = new RegistrationCard();
    registrationCard3.setDepartureDate(LocalDate.of(2021, 7, 20));
    registrationCard3.setHotelRoom(3);

    HotelRoom hotelRoom1 = new HotelRoom();
    hotelRoom1.setNumberOfRoom(1);
    HotelRoom hotelRoom2 = new HotelRoom();
    hotelRoom2.setNumberOfRoom(2);
    HotelRoom hotelRoom3 = new HotelRoom();
    hotelRoom3.setNumberOfRoom(3);

    List<RegistrationCard> registrationCardsForInput
            = Arrays.asList(registrationCard1, registrationCard2, registrationCard3);
    List<HotelRoom> hotelRoomsForResult
            = Arrays.asList(hotelRoom1, hotelRoom3);

    when(registrationCardDao.getAll()).thenReturn(registrationCardsForInput);
    when(hotelRoomDao.read(registrationCard1.getHotelRoom())).thenReturn(hotelRoom1);
    when(hotelRoomDao.read(registrationCard3.getHotelRoom())).thenReturn(hotelRoom3);

    assertThat(hotelRoomService.showRoomsByDate(year, month, dayOfMonth)
            , is(hotelRoomsForResult));
  }

  @Test
  public void showLastResidentsOfRoom() {
    int numberOfRoom = 1;
    List<String> stringList = Arrays.asList("abc", "def");

    HotelRoom hotelRoom = new HotelRoom();
    hotelRoom.setNumberOfRoom(numberOfRoom);
    hotelRoom.setLastResidents(stringList);

    when(hotelRoomDao.read(numberOfRoom)).thenReturn(hotelRoom);

    assertThat(hotelRoomService.showLastResidentsOfRoom(numberOfRoom)
            , is(stringList));
  }

  @Test
  public void showLastResidentsOfRoomShouldThrowIllegalArgExc() {
    int numberOfRoom = 1;

    assertThrows(IllegalArgumentException.class, () ->
            hotelRoomService.showLastResidentsOfRoom(numberOfRoom));
  }

  @Test
  public void showRoomDetails() {
    String result = "Number of room: 1; Number of stars: 0; Room capacity: 0;" +
            " Price: 0; Room condition: MAINTAINED; Room is occupied now";
    int numberOfRoom = 1;
    boolean roomIsOccupied = true;

    HotelRoom hotelRoom = new HotelRoom();
    hotelRoom.setNumberOfRoom(numberOfRoom);
    hotelRoom.setRoomIsOccupied(roomIsOccupied);

    when(hotelRoomDao.read(numberOfRoom)).thenReturn(hotelRoom);

    assertThat(hotelRoomService.showRoomDetails(numberOfRoom)
            , is(result));
  }

  @Test
  public void showRoomDetailsShouldThrowIllegalArgExc() {
    int numberOfRoom = 3;

    assertThrows(IllegalArgumentException.class, () ->
            hotelRoomService.showRoomDetails(numberOfRoom));
  }
}