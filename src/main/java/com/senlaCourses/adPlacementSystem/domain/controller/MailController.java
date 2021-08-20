package com.senlaCourses.adPlacementSystem.domain.controller;

import com.senlaCourses.adPlacementSystem.domain.controller.interfaces.IMailController;
import com.senlaCourses.adPlacementSystem.domain.service.interfaces.IMailService;
import com.senlaCourses.adPlacementSystem.exceptions.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class-Controller for working with chats.
 */
@AllArgsConstructor
@RestController
public class MailController implements IMailController {

  private final IMailService mailService;

  @Override
  @PostMapping(value = "/lost-password")
  public ResponseEntity<?> sendMail(@RequestBody String mailTo)
      throws EntityNotFoundException {
    mailService.sendEmail(mailTo);

    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}
