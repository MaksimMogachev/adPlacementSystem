package com.senlaCourses.adPlacementSystem.domain.service.interfaces;

import com.senlaCourses.adPlacementSystem.domain.model.Mail;
import com.senlaCourses.adPlacementSystem.exceptions.EntityNotFoundException;

/**
 * Interface for MailService realization.
 */
public interface IMailService {

  void sendEmail(String mailTo) throws EntityNotFoundException;
}
