package com.senlaCourses.adPlacementSystem.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * POJO class for comments.
 */
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "message", schema = "aps")
public class Message implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @ManyToOne
  @JoinTable(name = "message_chat", schema = "aps")
  @JsonIgnore
  private Chat chat;
  private final LocalDateTime messageSendingTime = LocalDateTime.now();
  private String senderName;
  private String messageText;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Message message = (Message) o;
    return id == message.id && messageSendingTime.equals(message.messageSendingTime) && senderName
        .equals(message.senderName) && messageText.equals(message.messageText);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, messageSendingTime, senderName, messageText);
  }
}
