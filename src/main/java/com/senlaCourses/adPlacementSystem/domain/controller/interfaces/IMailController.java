package com.senlaCourses.adPlacementSystem.domain.controller.interfaces;

import com.senlaCourses.adPlacementSystem.exceptions.EntityNotFoundException;
import org.springframework.http.ResponseEntity;

/**
 * Interface for MailController realization.
 */
public interface IMailController {

  ResponseEntity<?> sendMail(String mailTo) throws EntityNotFoundException;
}
