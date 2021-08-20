package com.senlaCourses.adPlacementSystem.domain.service.interfaces;

import com.senlaCourses.adPlacementSystem.domain.dto.request.MessageDto;
import com.senlaCourses.adPlacementSystem.domain.model.Chat;
import com.senlaCourses.adPlacementSystem.domain.model.Message;
import com.senlaCourses.adPlacementSystem.exceptions.EntityNotFoundException;
import java.util.Set;

/**
 * Interface for ChatService realization.
 */
public interface IChatService {

  Chat sendMassage(MessageDto messageDto) throws EntityNotFoundException;

  Set<Message> getAllMessagesFromChat(long userId) throws EntityNotFoundException;

  Set<Chat> getAllChats();
}
