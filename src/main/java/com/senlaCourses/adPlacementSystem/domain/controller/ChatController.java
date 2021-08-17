package com.senlaCourses.adPlacementSystem.domain.controller;

import com.senlaCourses.adPlacementSystem.domain.controller.interfaces.IChatController;
import com.senlaCourses.adPlacementSystem.domain.dto.request.MessageDto;
import com.senlaCourses.adPlacementSystem.domain.model.Chat;
import com.senlaCourses.adPlacementSystem.domain.model.Message;
import com.senlaCourses.adPlacementSystem.domain.service.interfaces.IChatService;
import com.senlaCourses.adPlacementSystem.exceptions.EntityNotFoundException;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class-Controller for working with chats.
 */
@AllArgsConstructor
@RestController
public class ChatController implements IChatController {

  private final IChatService chatService;

  @Override
  @PutMapping(value = "/messages")
  public ResponseEntity<Chat> sendMassage(@RequestBody MessageDto messageDto)
      throws EntityNotFoundException {
    Chat chat = chatService.sendMassage(messageDto);

    return new ResponseEntity<>(chat, HttpStatus.OK);
  }

  @Override
  @GetMapping(value = "/messages/{userId}")
  public ResponseEntity<List<Message>> getAllMessagesFromChat(@PathVariable long userId)
      throws EntityNotFoundException {
    List<Message> messages = chatService.getAllMessagesFromChat(userId);

    return new ResponseEntity<>(messages, HttpStatus.OK);
  }

  @Override
  @GetMapping(value = "/messages")
  public ResponseEntity<Set<Chat>> getAllChats() {
    Set<Chat> chats = chatService.getAllChats();

    return new ResponseEntity<>(chats, HttpStatus.OK);
  }
}
