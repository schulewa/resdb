package com.apschulewitz.resdb.security.controller;

import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.security.model.entity.AccountStatus;
import com.apschulewitz.resdb.security.model.dao.UserAccountDao;
import com.apschulewitz.resdb.security.model.dao.UserPasswordDao;
import com.apschulewitz.resdb.security.model.dto.UserLogonDto;
import com.apschulewitz.resdb.security.model.entity.UserAccount;
import com.apschulewitz.resdb.security.model.entity.UserPassword;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserAuthenticationControllerIT {

  private static final String USER_LOGON = "testuser";
  private static final String USER_PASSWORD = "testpassword";
  private static final String USER_FIRST_NAME = "Firstname";
  private static final String USER_FAMILY_NAME = "Familyname";
  private static final LocalDate PASSWORD_VALID_FROM = LocalDate.of(2020, Month.JANUARY, 1);

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private UserAccountDao userAccountDao;

  @Autowired
  private UserPasswordDao userPasswordDao;

  private Gson gson = new Gson();

  @Before
  public void beforeEachTest() {
    UserAccount savedTestUser = userAccountDao.save(constructTestUserAccount());
    if (savedTestUser == null) {
      fail("Failed to create test user account");
    }
  }

  @Test
  public void when_sayHello_is_executed_then_return_ok() throws Exception {
    mockMvc.perform(
      get(RestUrlPaths.TEST_PAGE_URL)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
    ).andExpect(status().isOk());
  }

  @Test
  public void when_sayHelloWithData_is_executed_then_return_ok() throws Exception {
    mockMvc.perform(
      post(RestUrlPaths.TEST_PAGE_WITH_DATA_URL)
        .content("Test")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
    ).andExpect(status().isOk());
  }

  @Test
  public void given_valid_user_when_logon_is_executed_then_return_ok() throws Exception {
    UserLogonDto dto = new UserLogonDto();
    dto.setUserName(USER_LOGON);
    dto.setPassword(USER_PASSWORD);

    String json = gson.toJson(dto);

    mockMvc.perform(
      post(RestUrlPaths.LOGIN_PAGE_URL)
        .content(json)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
    ).andExpect(status().isOk());
  }

  @Test
  public void given_unknown_user_when_logon_is_executed_then_return_unathorized() throws Exception {
    UserLogonDto dto = new UserLogonDto();
    dto.setUserName("unknown");
    dto.setPassword("unknown");

    String json = gson.toJson(dto);

    mockMvc.perform(
      post(RestUrlPaths.LOGIN_PAGE_URL)
        .content(json)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
    ).andExpect(status().isUnauthorized());
  }

  private UserAccount constructTestUserAccount() {
    UserAccount userAccount = UserAccount.builder()
      .firstName(USER_FIRST_NAME)
      .familyName(USER_FAMILY_NAME)
      .logonName(USER_LOGON)
      .status(AccountStatus.Active)
      .build();

    UserPassword userPassword = UserPassword.builder()
      .user(userAccount)
      .password(USER_PASSWORD)
      .validFrom(PASSWORD_VALID_FROM)
      .build();

    userAccount.setPasswords(Collections.singletonList(userPassword));

    return userAccount;
  }
}
