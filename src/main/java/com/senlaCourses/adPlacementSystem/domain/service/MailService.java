package com.senlaCourses.adPlacementSystem.domain.service;

import com.senlaCourses.adPlacementSystem.dao.interfaces.IUserDao;
import com.senlaCourses.adPlacementSystem.domain.model.Mail;
import com.senlaCourses.adPlacementSystem.domain.model.User;
import com.senlaCourses.adPlacementSystem.domain.service.interfaces.IMailService;
import com.senlaCourses.adPlacementSystem.exceptions.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

/**
 * Class-Service for working with chats.
 */
@Slf4j
@AllArgsConstructor
@Service
public class MailService implements IMailService {

  private final JavaMailSender mailSender;
  private final VelocityEngine velocityEngine;
  private final IUserDao userDao;
  private final BCryptPasswordEncoder encoder;

  @Override
  public void sendEmail(String mailTo) throws EntityNotFoundException {
    User user = userDao.readByNaturalId(mailTo);
    if (user == null) {
      log.error("EntityNotFoundException(\"User not found\")");
      throw new EntityNotFoundException("User not found");
    }

    String newPassword = Integer.toString(
        ThreadLocalRandom.current().nextInt(1_000_000, 5_000_000));

    user.setPassword(encoder.encode(newPassword));

    Mail mail = new Mail();
    mail.setMailFrom("myMail@gmail.com");
    mail.setMailTo(mailTo);
    mail.setMailSubject("Lost password.");

    Map<String, Object> model = new HashMap<>();
    model.put("username", user.getUsername());
    model.put("password", ThreadLocalRandom.current().nextInt(1_000_000, 5_000_000));
    mail.setModel(model);

    MimeMessage mimeMessage = mailSender.createMimeMessage();
    try {
      MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

      mimeMessageHelper.setSubject(mail.getMailSubject());
      mimeMessageHelper.setFrom(mail.getMailFrom());
      mimeMessageHelper.setTo(mail.getMailTo());
      mail.setMailContent(getContentFromTemplate(mail.getModel()));
      mimeMessageHelper.setText(mail.getMailContent(), true);

      mailSender.send(mimeMessageHelper.getMimeMessage());
    } catch (MessagingException e) {
      e.printStackTrace();
    }

    userDao.update(user);
  }

  private String getContentFromTemplate(Map<String, Object> model) {
    StringBuilder content = new StringBuilder();
    try {
      content.append(VelocityEngineUtils
          .mergeTemplateIntoString(velocityEngine, "/templates/email-template.vm", model));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return content.toString();
  }
}
