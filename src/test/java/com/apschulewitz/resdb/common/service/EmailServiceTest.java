package com.apschulewitz.resdb.common.service;

import com.apschulewitz.resdb.common.EmailConfiguration;
import com.apschulewitz.resdb.common.SystemConfiguration;
import com.apschulewitz.resdb.common.model.ConfigurationPropertyName;
import com.apschulewitz.resdb.config.model.dao.SystemParameterDao;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest//(classes = {
//  EmailConfiguration.class,
//  SystemConfiguration.class,
//  SystemParameterDao.class
//})
@Slf4j
public class EmailServiceTest {

  @Autowired
  private EmailConfiguration emailConfiguration;

  @Test
  public void testEmailConfiguration() {
    assertNotNull(emailConfiguration);
    assertEquals("smtp.gmail.com", emailConfiguration.getEmailHost());
    assertEquals("587", emailConfiguration.getEmailPort());
    assertEquals("historyresearchdb", emailConfiguration.getEmailUsername());
    assertEquals("#Y6iM52i$%JXhUaI", emailConfiguration.getEmailPassword());
    assertEquals("historyresearchdb@gmail.com", emailConfiguration.getEmailDefaultFrom());
  }

  @Test
  public void testVerifyUserEmailTemplate() {
    SimpleMailMessage message = emailConfiguration.verifyUserEmailTemplate();
    assertNotNull(message);
    message.getText();
  }

  @Test
  public void given_valid_template_when_sendTemplateMessage_is_executed_then_send_content() throws IOException, TemplateException {
    // given
    JavaMailSender mockedEmailSender = mock(JavaMailSender.class);
    EmailServiceImpl emailService = new EmailServiceImpl(mockedEmailSender, emailConfiguration, false);
    String templatePath = "" +
                          EmailConfiguration.EMAIL_TEMPLATES_PATH + "/" +
                          EmailConfiguration.USER_REGISTRATION_EMAIL_TEMPLATE + "/en" +
                          "/verify_user.html";

    Map<String, Object> templateModel = new HashMap<>();
    templateModel.put("recipientName", "recipient");
    templateModel.put("text", "Hello world");
    templateModel.put("senderName", emailConfiguration.getEmailDefaultFrom());
    templateModel.put("verify_user_url", "/verify/user/XYZ123");
    templateModel.put("expiry_timestamp", ZonedDateTime.now(ZoneOffset.UTC));

    // when
    emailService.sendTemplateMessage("user@company.com", "Test subject", templatePath, templateModel);

    // then
    verify(mockedEmailSender).send(any(SimpleMailMessage.class));
  }

  @Test
  public void given_invalid_template_when_sendTemplateMessage_is_executed_then_send_content() {
    // given
    JavaMailSender mockedEmailSender = mock(JavaMailSender.class);
    EmailServiceImpl emailService = new EmailServiceImpl(mockedEmailSender, emailConfiguration, false);
    String templatePath = "non-existent-template.xyz";
    Map<String, Object> templateModel = new HashMap<>();

    // when

    // then
    assertThatThrownBy(() -> emailService.sendTemplateMessage("user@company.com",
                                                      "Test subject",
                                                              templatePath,
                                                              templateModel))
      .isInstanceOf(TemplateNotFoundException.class)
      .hasMessageStartingWith("Template not found for name");
  }
}
