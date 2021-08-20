package com.senlaCourses.adPlacementSystem.domain.model;

import java.util.Date;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

/**
 * POJO class for mails.
 */
@Getter
@Setter
public class Mail {

  private String mailFrom;
  private String mailTo;
  private String mailCc;
  private String mailBcc;
  private String mailSubject;
  private String mailContent;
  private String contentType;
  private List<Object> attachments;
  private Map<String, Object> model;

  public Mail() {
    contentType = "text/plain";
  }

  public Date getMailSendDate() {
    return new Date();
  }
}
