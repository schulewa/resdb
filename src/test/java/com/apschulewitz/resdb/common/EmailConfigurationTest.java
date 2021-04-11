package com.apschulewitz.resdb.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {EmailConfiguration.class})
public class EmailConfigurationTest {

  @Autowired
  private EmailConfiguration emailConfiguration;

  @Test
  public void given_emailconfiguration_when_getproperty_is_executed_then_return_value() {
    // given
    assertNotNull(emailConfiguration);

    // when

    // then
    assertFalse(StringUtils.isEmpty(emailConfiguration.getEmailHost()));
    assertFalse(StringUtils.isEmpty(emailConfiguration.getEmailPort()));
    assertFalse(StringUtils.isEmpty(emailConfiguration.getEmailUsername()));
    assertFalse(StringUtils.isEmpty(emailConfiguration.getEmailPassword()));
    assertFalse(StringUtils.isEmpty(emailConfiguration.getEmailDefaultFrom()));
  }

  @Test
  public void given_emailConfiguration_when_verifyUserEmailTemplate_is_executed_then_return_simplemailmessage() {
    // given
    assertNotNull(emailConfiguration);

    // when
    SimpleMailMessage verifyuserEmailTemplate = emailConfiguration.verifyUserEmailTemplate();

    // then
    assertNotNull(verifyuserEmailTemplate);
    assertFalse(StringUtils.isEmpty(verifyuserEmailTemplate.getText()));
  }

  @Test
  public void given_emailConfiguration_when_resetPasswordEmailTemplate_is_executed_then_return_simplemailmessage() {
    // given
    assertNotNull(emailConfiguration);

    // when
    SimpleMailMessage resetPasswordEmailTemplate = emailConfiguration.resetPasswordEmailTemplate();

    // then
    assertNotNull(resetPasswordEmailTemplate);
    assertFalse(StringUtils.isEmpty(resetPasswordEmailTemplate.getText()));
  }
}
