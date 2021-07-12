package com.senlacourses.electronicHotelAdministrator.domain.controller;

import com.senlacourses.electronicHotelAdministrator.domain.controller.interfaces.IHotelResidentController;
import com.senlacourses.electronicHotelAdministrator.domain.model.HotelResident;
import com.senlacourses.electronicHotelAdministrator.domain.service.interfaces.IHotelResidentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HotelResidentController implements IHotelResidentController {

  private final IHotelResidentService hotelResidentService;

  public HotelResidentController(IHotelResidentService hotelResidentService) {
    this.hotelResidentService = hotelResidentService;
  }

  @Override
  @GetMapping(value = "/residents")
  public ResponseEntity<List<HotelResident>> showAllResidents() {
    final List<HotelResident> hotelResidents = hotelResidentService.showAllResidents();

    return hotelResidents != null && !hotelResidents.isEmpty()
            ? new ResponseEntity<>(hotelResidents, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @Override
  @PostMapping(value = "/residents")
  public ResponseEntity<?> addNewResident(@RequestParam String fullName, @RequestParam int passportNumber) {
    hotelResidentService.addNewResident(fullName, passportNumber);

    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @Override
  @DeleteMapping(value = "/residents/{passportNumber}")
  public ResponseEntity<?> removeResident(@PathVariable(name = "passportNumber") int passportNumber) {
    boolean deleted = hotelResidentService.removeResident(passportNumber);

    return deleted
            ? new ResponseEntity<>(HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
  }
}
