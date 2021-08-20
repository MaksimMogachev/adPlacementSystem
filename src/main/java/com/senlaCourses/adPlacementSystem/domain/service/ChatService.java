package com.senlaCourses.adPlacementSystem.domain.service;

import com.senlaCourses.adPlacementSystem.dao.interfaces.IChatDao;
import com.senlaCourses.adPlacementSystem.dao.interfaces.IMessageDao;
import com.senlaCourses.adPlacementSystem.dao.interfaces.IUserDao;
import com.senlaCourses.adPlacementSystem.domain.dto.request.MessageDto;
import com.senlaCourses.adPlacementSystem.domain.model.Chat;
import com.senlaCourses.adPlacementSystem.domain.model.Message;
import com.senlaCourses.adPlacementSystem.domain.model.User;
import com.senlaCourses.adPlacementSystem.domain.service.interfaces.IChatService;
import com.senlaCourses.adPlacementSystem.exceptions.EntityNotFoundException;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Class-Service for working with chats.
 */
@Slf4j
@AllArgsConstructor
@Service
public class ChatService implements IChatService {

  private final IUserDao userDao;
  private final IMessageDao messageDao;
  private final IChatDao chatDao;

  /**
   * Sends message to user.
   * Method creates new chat if not found or changing current.
   *
   * @param messageDto message data for create Message.class object id DB.
   * @return changed Chat.class object.
   */
  @Override
  public Chat sendMassage(MessageDto messageDto) throws EntityNotFoundException {
    Message message = new Message();
    message.setSenderName(getCurrentUser().getUsername());
    message.setMessageText(messageDto.getMessageText());
    Chat chat = findChat(messageDto.getReceiverId());
    if (chat == null) {
      chat = new Chat();
      User currentUser = getCurrentUser();
      User receiver = userDao.read(messageDto.getReceiverId());
      if (receiver == null) {
        log.error("EntityNotFoundException(\"User not found\")");
        throw new EntityNotFoundException("User not found");
      }

      currentUser.getChats().add(chat);
      receiver.getChats().add(chat);

      chat.getMessages().add(message);
      chatDao.create(chat);
      userDao.update(currentUser);
      userDao.update(receiver);
    } else {
      chat.getMessages().add(message);
      chatDao.update(chat);
    }

    message.setChat(chat);
    messageDao.create(message);

    return chat;
  }

  /**
   * Gets messages from current chat.
   *
   * @param userId Id of the user whose chat you want to receive.
   * @return list of messages
   * @throws EntityNotFoundException if chat wasn't found in DB.
   */
  @Override
  public Set<Message> getAllMessagesFromChat(long userId) throws EntityNotFoundException {
    Chat chat = findChat(userId);
    if (chat == null) {
      log.error("EntityNotFoundException(\"Chat not found\")");
      throw new EntityNotFoundException("Chat not found");
    }

    return chat.getMessages();
  }

  @Override
  public Set<Chat> getAllChats() {
    return getCurrentUser().getChats();
  }

  private Chat findChat(long receiverId) {
    User loggedUser = getCurrentUser();
    User receiver = userDao.read(receiverId);

    for (Chat chat : loggedUser.getChats()) {
      if (chat.getUsers().contains(receiver)) {
        return chat;
      }
    }
    return null;
  }

  private User getCurrentUser() {
    UserDetails userDetails = (UserDetails) SecurityContextHolder
        .getContext().getAuthentication().getPrincipal();
    return userDao.readByNaturalId(userDetails.getUsername());
  }
}
