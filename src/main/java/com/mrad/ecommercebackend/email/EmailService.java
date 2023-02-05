package com.mrad.ecommercebackend.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements EmailSender {

  private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
  private final JavaMailSender mailSender;

  @Autowired
  public EmailService(JavaMailSender javaMailSender) {
    this.mailSender = javaMailSender;
  }

  @Value("${spring.mail.username}")
  private String from;

  @Override
  @Async
  public void send(String to, String email) {
    try {
      MimeMessage mimeMessage = mailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
      helper.setText(email, true);
      helper.setTo(to);
      helper.setSubject("Confirm your email");
      helper.setFrom(from);
      mailSender.send(mimeMessage);
    }catch (MessagingException e){
      LOGGER.error("failed to send email",e);
      throw new IllegalStateException("failed to send email");
    }
  }
}
