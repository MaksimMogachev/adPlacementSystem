package com.senlaCourses.adPlacementSystem.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * POJO class for chats.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "chat", schema = "aps")
public class Chat implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @OneToMany(mappedBy = "chat")
  private Set<Message> messages = new HashSet<>();
  @ManyToMany(mappedBy = "chats")
  @JsonIgnore
  private Set<User> users = new HashSet<>();

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Chat chat = (Chat) o;
    return id == chat.id && messages.equals(chat.messages);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, messages);
  }
}
