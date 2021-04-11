package com.apschulewitz.resdb.common.service;

import com.apschulewitz.resdb.common.EmailConfiguration;
import com.apschulewitz.resdb.common.SystemConfiguration;
import com.apschulewitz.resdb.common.exception.ResearchDatabaseCommonException;
import com.apschulewitz.resdb.config.model.entity.SystemParameterTypes;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.constraints.NotEmpty;
import java.io.File;
import java.io.IOException;
import java.util.Map;

@Component
@Getter
@Slf4j
public class EmailServiceImpl {

  private final JavaMailSender emailSender;

  private final EmailConfiguration emailConfiguration;
  //
  private final boolean sendUserVerificationEmailWhenRegistrating;

  public EmailServiceImpl(JavaMailSender emailSender,
                          EmailConfiguration emailConfiguration,
                          Boolean sendUserVerificationEmailWhenRegistering) {
    this.emailSender = emailSender;
    this.emailConfiguration = emailConfiguration;

    sendUserVerificationEmailWhenRegistrating = sendUserVerificationEmailWhenRegistering;
  }

  public void sendSimpleMessage(String to, String subject, String text) {

    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom(emailConfiguration.getEmailDefaultFrom());
    message.setTo(to);
    message.setSubject(subject);
    message.setText(text);
    emailSender.send(message);
  }

  public void sendEmailWithAttachment(@NotEmpty String to,
                                      @NotEmpty String subject,
                                      String text,
                                      String attachmentFilename) {
    MimeMessage message = emailSender.createMimeMessage();

    try {
      MimeMessageHelper helper = new MimeMessageHelper(message, true);

      helper.setFrom(emailConfiguration.getEmailDefaultFrom());
      helper.setTo(to);
      helper.setSubject(subject);
      helper.setText(text);

      FileSystemResource file
        = new FileSystemResource(new File(attachmentFilename));
      helper.addAttachment("Attachment", file);
      emailSender.send(message);

    } catch (MessagingException messagingException) {
      throw new ResearchDatabaseCommonException(messagingException);
    }
  }

  public void sendTemplateMessage(String to, String subject, String templatePath, Map<String, Object> templateModel)
    throws IOException, TemplateException {
    Template template = getTemplate(templatePath);
    String htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(template, templateModel);
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom(emailConfiguration.getEmailDefaultFrom());
    message.setTo(to);
    message.setSubject(subject);
    message.setText(htmlText);
    emailSender.send(message);
  }

  public Template getTemplate(String templatePath) throws IOException {
    Configuration freemarkerConfiguration = new Configuration(Configuration.VERSION_2_3_31);
    freemarkerConfiguration.setClassForTemplateLoading(getClass(), "/");
    return freemarkerConfiguration.getTemplate(templatePath);
  }
}
