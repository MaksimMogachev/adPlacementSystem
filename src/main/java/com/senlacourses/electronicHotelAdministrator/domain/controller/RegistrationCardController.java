package com.senlacourses.electronicHotelAdministrator.domain.controller;

import com.senlacourses.electronicHotelAdministrator.domain.controller.interfaces.IRegistrationCardController;
import com.senlacourses.electronicHotelAdministrator.domain.model.RegistrationCard;
import com.senlacourses.electronicHotelAdministrator.domain.service.criteriaForSorting.OccupiedRoomSortingCriteria;
import com.senlacourses.electronicHotelAdministrator.domain.service.criteriaForSorting.ServiceSortingCriteria;
import com.senlacourses.electronicHotelAdministrator.domain.service.interfaces.IRegistrationCardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RegistrationCardController implements IRegistrationCardController {

  private final IRegistrationCardService registrationCardService;

  public RegistrationCardController(IRegistrationCardService registrationCardService) {
    this.registrationCardService = registrationCardService;
  }

  @Override
  @GetMapping(value = "/registration-cards")
  public ResponseEntity<List<RegistrationCard>> showOccupiedRooms() {
    List<RegistrationCard> registrationCards = registrationCardService.showOccupiedRooms();

    return registrationCards != null
            ? new ResponseEntity<>(registrationCards, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @Override
  @PostMapping(value = "/registration-cards")
  public ResponseEntity<?> createNewCard(@RequestBody RegistrationCard registrationCard) {

    registrationCardService.createNewCard(registrationCard);

    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @Override
  @PutMapping(value = "/registration-cards/{numberOfRoom}")
  public ResponseEntity<RegistrationCard> putInTheRoom(@PathVariable(value = "numberOfRoom") int numberOfRoom,
                                                       @RequestBody String passportNumber) {

    RegistrationCard registrationCard = registrationCardService.putInTheRoom(
            numberOfRoom, Integer.parseInt(passportNumber));

    return registrationCard != null
            ? new ResponseEntity<>(registrationCard, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @Override
  @PutMapping(value = "/registration-cards/{numberOfRoom}/evict")
  public ResponseEntity<RegistrationCard> evictFromTheRoom(@PathVariable(value = "numberOfRoom") int numberOfRoom,
                                                           @RequestParam int indexOfResidentInRoom) {

    RegistrationCard registrationCard = registrationCardService.evictFromTheRoom(numberOfRoom, indexOfResidentInRoom);

    return registrationCard != null
            ? new ResponseEntity<>(registrationCard, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @Override
  @DeleteMapping(value = "/registration-cards/{numberOfRoom}")
  public ResponseEntity<?> evictFromTheRoom(@PathVariable(value = "numberOfRoom") int numberOfRoom) {
    registrationCardService.evictFromTheRoom(numberOfRoom);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  @PutMapping(value = "/registration-cards/{numberOfRoom}/service")
  public ResponseEntity<RegistrationCard> addServiceToOccupiedRoom(
                                          @PathVariable(value = "numberOfRoom") int numberOfRoom,
                                          @RequestBody String nameOfService) {

    RegistrationCard registrationCard = registrationCardService.addServiceToOccupiedRoom(numberOfRoom, nameOfService);

    return registrationCard != null
            ? new ResponseEntity<>(registrationCard, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @Override
  @GetMapping(value = "/registration-cards/criterion")
  public ResponseEntity<List<RegistrationCard>> showOccupiedRoomsByCriterion(@RequestBody String criterion) {

    OccupiedRoomSortingCriteria criterionForSorting = OccupiedRoomSortingCriteria.valueOf(criterion.toUpperCase());
    List<RegistrationCard> registrationCards = registrationCardService.showOccupiedRoomsByCriterion(criterionForSorting);

    return registrationCards != null
            ? new ResponseEntity<>(registrationCards, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @Override
  @GetMapping(value = "/registration-cards/current-residents")
  public ResponseEntity<String> showNumberOfCurrentResidents() {
    return new ResponseEntity<>(registrationCardService.showNumberOfCurrentResidents(), HttpStatus.OK);
  }

  @Override
  @GetMapping(value = "/registration-cards/{numberOfRoom}/payment")
  public ResponseEntity<String> showAmountOfPayment(@PathVariable(value = "numberOfRoom") int numberOfRoom, 
                                                    @RequestParam int daysOfStay) {
    return new ResponseEntity<>(registrationCardService.showAmountOfPayment(numberOfRoom, daysOfStay), HttpStatus.OK);
  }

  @Override
  @GetMapping(value = "/registration-cards/{passportNumber}/criterion")
  public ResponseEntity<Object[]> showResidentServicesByCriterion(
          @PathVariable(value = "passportNumber") int passportNumber, 
          @RequestBody String sortingCriteria) {
    
    ServiceSortingCriteria criterion = ServiceSortingCriteria.valueOf(sortingCriteria.toUpperCase());
    Object[] objects = registrationCardService.showResidentServicesByCriterion(passportNumber, criterion);
    
    return objects != null
            ? new ResponseEntity<>(objects, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
