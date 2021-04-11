package com.apschulewitz.resdb.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = {UserRegistrationConfiguration.class})
@SpringBootTest
public class UserRegistrationConfigurationTest {

  @Autowired
  private UserRegistrationConfiguration userRegistrationConfiguration;

  @Test
  public void testUserRegConfigNotNull() {
    assertNotNull(userRegistrationConfiguration);
  }
}
