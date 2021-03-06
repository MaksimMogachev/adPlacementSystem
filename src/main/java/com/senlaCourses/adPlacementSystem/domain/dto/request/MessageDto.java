package com.senlaCourses.adPlacementSystem.domain.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Class for creation Message.class object.
 */
@Getter
@NoArgsConstructor
public class MessageDto {

  private long receiverId;
  private String messageText;
}
