package com.senlaCourses.adPlacementSystem.domain.controller.interfaces;

import com.senlaCourses.adPlacementSystem.domain.dto.request.MessageDto;
import com.senlaCourses.adPlacementSystem.domain.model.Chat;
import com.senlaCourses.adPlacementSystem.domain.model.Message;
import com.senlaCourses.adPlacementSystem.exceptions.EntityNotFoundException;
import java.util.Set;
import org.springframework.http.ResponseEntity;

/**
 * Interface for ChatController realization.
 */
public interface IChatController {

  ResponseEntity<Chat> sendMassage(MessageDto messageDto) throws EntityNotFoundException;

  ResponseEntity<Set<Message>> getAllMessagesFromChat(long userId)
      throws EntityNotFoundException;

  ResponseEntity<Set<Chat>> getAllChats();
}
