package com.apschulewitz.resdb.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
  UserAuthenticationConfiguration.class,
  UserAuthenticationTestConfig.class
})
@Slf4j
public class UserAuthenticationConfigurationTest {

  private static final String RAW_PASSWORD = "password";

  private static final String ENCODED_RAW_PASSWORD = "{bcrypt}$2a$12$/E5In0LC.EIRmowqljaxLO8OggY96Net9fm1/fhnXyzUudyW1FHqa";

  @Autowired
  private UserAuthenticationConfiguration userAuthenticationConfiguration;

  @Before
  public void beforeEachTest() {
    log.info("userAuthenticationConfiguration = {}");
  }

  @Test
  public void testUserAuthenticationConfiguration() {
    assertNotNull(userAuthenticationConfiguration);
  }

  @Test
  public void given_passwordencoder_when_encode_is_executed_then_return_encoded_password() {
    // given
    PasswordEncoder passwordEncoder = userAuthenticationConfiguration.passwordEncoder();
    String encodedRawPassword = passwordEncoder.encode(RAW_PASSWORD);

    // when
    boolean passwordMatches = passwordEncoder.matches(RAW_PASSWORD, ENCODED_RAW_PASSWORD);

    // then
    log.info("ENCODED_RAW_PASSWORD={}, encodedRawPassword={}", ENCODED_RAW_PASSWORD, encodedRawPassword);
    assertNotNull(encodedRawPassword); // $2a$12$xMQyA3tKwtvRaWZjxzUR6O1j1g9A7AM74MwPWQAE.vCe1derFYvte
    assertTrue(passwordMatches);
  }
}
