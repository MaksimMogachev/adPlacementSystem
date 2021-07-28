package com.senlacourses.electronicHotelAdministrator.domain.controller;

import com.senlacourses.electronicHotelAdministrator.domain.controller.interfaces.IHotelRoomController;
import com.senlacourses.electronicHotelAdministrator.domain.dto.request.HotelRoomDto;
import com.senlacourses.electronicHotelAdministrator.domain.model.HotelRoom;
import com.senlacourses.electronicHotelAdministrator.domain.model.RoomCondition;
import com.senlacourses.electronicHotelAdministrator.domain.service.criteriaForSorting.RoomSortingCriteria;
import com.senlacourses.electronicHotelAdministrator.domain.service.interfaces.IHotelRoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HotelRoomController implements IHotelRoomController {

  private final IHotelRoomService hotelRoomService;

  public HotelRoomController(IHotelRoomService hotelRoomService) {
    this.hotelRoomService = hotelRoomService;
  }

  @Override
  @GetMapping(value = "/hotel-rooms")
  public ResponseEntity<List<HotelRoom>> showAllRooms() {
    List<HotelRoom> hotelRooms = hotelRoomService.getAllRooms();

    return hotelRooms != null
            ? new ResponseEntity<>(hotelRooms, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @Override
  @PostMapping(value = "/hotel-rooms")
  public ResponseEntity<?> addNewRoom(@RequestBody HotelRoomDto hotelRoomDto) {
    hotelRoomService.addNewRoom(hotelRoomDto);

    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @Override
  @PutMapping(value = "/hotel-rooms/{numberOfRoom}/condition")
  public ResponseEntity<HotelRoom> changeRoomCondition(
          @PathVariable(name = "numberOfRoom") int numberOfRoom,
          @RequestBody String roomCondition) {

    HotelRoom hotelRoom = hotelRoomService.changeRoomCondition(
            numberOfRoom, RoomCondition.valueOf(roomCondition.toUpperCase()));

    return hotelRoom != null
            ? new ResponseEntity<>(hotelRoom, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @Override
  @PutMapping(value = "/hotel-rooms/{numberOfRoom}/price")
  public ResponseEntity<HotelRoom> changeRoomPrice(
          @PathVariable(name = "numberOfRoom") int numberOfRoom,
          @RequestBody String newPrice) {

    HotelRoom hotelRoom = hotelRoomService.changeRoomPrice(numberOfRoom, Integer.parseInt(newPrice));
    return hotelRoom != null
            ? new ResponseEntity<>(hotelRoom, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @Override
  @GetMapping(value = "/hotel-rooms/free")
  public ResponseEntity<?> showNumberOfFreeRooms() {
    return new ResponseEntity<>(hotelRoomService.showNumberOfFreeRooms(), HttpStatus.OK);
  }

  @Override
  @GetMapping(value = "/hotel-rooms/criterion")
  public ResponseEntity<List<HotelRoom>> showAllRoomsByCriterion(@RequestBody String criterion) {
    List<HotelRoom> hotelRooms = hotelRoomService
            .showAllRoomsByCriterion(RoomSortingCriteria.valueOf(criterion.toUpperCase()));

    return hotelRooms != null
            ? new ResponseEntity<>(hotelRooms, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @Override
  @GetMapping(value = "/hotel-rooms/criterion/free")
  public ResponseEntity<List<HotelRoom>> showFreeRoomsByCriterion(@RequestBody String criterion) {
    List<HotelRoom> hotelRooms = hotelRoomService
            .showFreeRoomsByCriterion(RoomSortingCriteria.valueOf(criterion.toUpperCase()));

    return hotelRooms != null
            ? new ResponseEntity<>(hotelRooms, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @Override
  @GetMapping(value = "/hotel-rooms/date")
  public ResponseEntity<List<HotelRoom>> showRoomsByDate(@RequestParam int year,
                                                         @RequestParam int month,
                                                         @RequestParam int dayOfMonth) {

    List<HotelRoom> hotelRooms = hotelRoomService.showRoomsByDate(year, month, dayOfMonth);

    return hotelRooms != null
            ? new ResponseEntity<>(hotelRooms, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @Override
  @GetMapping(value = "/hotel-rooms/{numberOfRoom}/residents")
  public ResponseEntity<List<String>> showLastResidentsOfRoom(
          @PathVariable(name = "numberOfRoom") int numberOfRoom) {

    List<String> lastResidents = hotelRoomService.showLastResidentsOfRoom(numberOfRoom);

    return lastResidents != null
            ? new ResponseEntity<>(lastResidents, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @Override
  @GetMapping(value = "/hotel-rooms/{numberOfRoom}")
  public ResponseEntity<String> showRoomDetails(
          @PathVariable(name = "numberOfRoom") int numberOfRoom) {

    String details = hotelRoomService.showRoomDetails(numberOfRoom);

    return details != null
            ? new ResponseEntity<>(details, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
