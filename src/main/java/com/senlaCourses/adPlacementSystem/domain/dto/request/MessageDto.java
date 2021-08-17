package com.senlaCourses.adPlacementSystem.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * Class for creation Message.class object.
 */
@Getter
@AllArgsConstructor
public class MessageDto {

  private final long receiverId;
  @NotNull
  private final String messageText;
}
