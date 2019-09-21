package com.apschulewitz.resdb.security;

import com.apschulewitz.resdb.common.ApplicationResponse;
import com.apschulewitz.resdb.common.ResponseStatus;
import com.apschulewitz.resdb.refdata.model.entity.AccountStatus;
import com.apschulewitz.resdb.security.model.dao.UserAccountDao;
import com.apschulewitz.resdb.security.model.dto.UserLogonDto;
import com.apschulewitz.resdb.security.model.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mockitoSession;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class UserAuthenticationServiceTest {

  @Autowired
  private CsrfTokenRepository csrfTokenRepository;

  @MockBean
  private UserAccountDao mockedUserAccountDao;

  @MockBean
  private AuthenticationManager mockedAuthenticationManager;

  @Test
  public void given_Active_user_credentials_when_logon_is_executed_then_return_success() {
    // Given
    UserAuthenticationService userAuthenticationService = new UserAuthenticationService(mockedUserAccountDao, mockedAuthenticationManager, csrfTokenRepository);

    UserLogonDto userLogonDto = new UserLogonDto();
    userLogonDto.setUserName("testuser");
    userLogonDto.setPassword("testpassword");

    UserAccount userAccount = constructUserAccount(userLogonDto, AccountStatus.Active);

    // When
    when(mockedUserAccountDao.findByLogonName(userLogonDto.getUserName())).thenReturn(userAccount);

    ApplicationResponse applicationResponse = userAuthenticationService.authenticateUser(userLogonDto.getUserName(), userLogonDto.getPassword());

    // Then
    assertNotNull(applicationResponse);
    assertNotNull(applicationResponse.getResponseStatus());
    assertEquals(ResponseStatus.AUTHENTICATED, applicationResponse.getResponseStatus());
  }

  @Test
  public void given_Inactive_user_credentials_when_logon_is_executed_then_return_success() {
    // Given
    UserAuthenticationService userAuthenticationService = new UserAuthenticationService(mockedUserAccountDao, mockedAuthenticationManager, csrfTokenRepository);

    UserLogonDto userLogonDto = new UserLogonDto();
    userLogonDto.setUserName("testuser");
    userLogonDto.setPassword("testpassword");

    UserAccount userAccount = constructUserAccount(userLogonDto, AccountStatus.Inactive);

    // When
    when(mockedUserAccountDao.findByLogonName(userLogonDto.getUserName())).thenReturn(userAccount);

    ApplicationResponse applicationResponse = userAuthenticationService.authenticateUser(userLogonDto.getUserName(), userLogonDto.getPassword());

    // Then
    assertNotNull(applicationResponse);
    assertNotNull(applicationResponse.getResponseStatus());
    assertEquals(ResponseStatus.ACCOUNT_LOCKED, applicationResponse.getResponseStatus());
  }

  @Test
  public void given_Locked_user_credentials_when_logon_is_executed_then_return_account_locked() {
    // Given
    UserAuthenticationService userAuthenticationService = new UserAuthenticationService(mockedUserAccountDao, mockedAuthenticationManager, csrfTokenRepository);

    UserLogonDto userLogonDto = new UserLogonDto();
    userLogonDto.setUserName("testuser");
    userLogonDto.setPassword("testpassword");

    UserAccount userAccount = constructUserAccount(userLogonDto, AccountStatus.Locked);

    // When
    when(mockedUserAccountDao.findByLogonName(userLogonDto.getUserName())).thenReturn(userAccount);

    ApplicationResponse applicationResponse = userAuthenticationService.authenticateUser(userLogonDto.getUserName(), userLogonDto.getPassword());

    // Then
    assertNotNull(applicationResponse);
    assertNotNull(applicationResponse.getResponseStatus());
    assertEquals(ResponseStatus.ACCOUNT_LOCKED, applicationResponse.getResponseStatus());
  }

  @Test
  public void given_PasswordNeedsResetting_user_credentials_when_logon_is_executed_then_return_password_needs_reset() {
    // Given
    UserAuthenticationService userAuthenticationService = new UserAuthenticationService(mockedUserAccountDao, mockedAuthenticationManager, csrfTokenRepository);

    UserLogonDto userLogonDto = new UserLogonDto();
    userLogonDto.setUserName("testuser");
    userLogonDto.setPassword("testpassword");

    UserAccount userAccount = constructUserAccount(userLogonDto, AccountStatus.PasswordNeedsResetting);

    // When
    when(mockedUserAccountDao.findByLogonName(userLogonDto.getUserName())).thenReturn(userAccount);

    ApplicationResponse applicationResponse = userAuthenticationService.authenticateUser(userLogonDto.getUserName(), userLogonDto.getPassword());

    // Then
    assertNotNull(applicationResponse);
    assertNotNull(applicationResponse.getResponseStatus());
    assertEquals(ResponseStatus.PASSWORD_NEEDS_RESET, applicationResponse.getResponseStatus());
  }

  @Test
  public void given_Suspended_user_credentials_when_logon_is_executed_then_return_account_locked() {
    // Given
    UserAuthenticationService userAuthenticationService = new UserAuthenticationService(mockedUserAccountDao, mockedAuthenticationManager, csrfTokenRepository);

    UserLogonDto userLogonDto = new UserLogonDto();
    userLogonDto.setUserName("testuser");
    userLogonDto.setPassword("testpassword");

    UserAccount userAccount = constructUserAccount(userLogonDto, AccountStatus.Suspended);

    // When
    when(mockedUserAccountDao.findByLogonName(userLogonDto.getUserName())).thenReturn(userAccount);

    ApplicationResponse applicationResponse = userAuthenticationService.authenticateUser(userLogonDto.getUserName(), userLogonDto.getPassword());

    // Then
    assertNotNull(applicationResponse);
    assertNotNull(applicationResponse.getResponseStatus());
    assertEquals(ResponseStatus.ACCOUNT_LOCKED, applicationResponse.getResponseStatus());
  }

  @Test
  public void given_Unknown_user_credentials_when_logon_is_executed_then_return_account_locked() {
    // Given
    UserAuthenticationService userAuthenticationService = new UserAuthenticationService(mockedUserAccountDao, mockedAuthenticationManager, csrfTokenRepository);

    UserLogonDto userLogonDto = new UserLogonDto();
    userLogonDto.setUserName("testuser");
    userLogonDto.setPassword("testpassword");

    UserAccount userAccount = constructUserAccount(userLogonDto, AccountStatus.Unknown);

    // When
    when(mockedUserAccountDao.findByLogonName(userLogonDto.getUserName())).thenReturn(userAccount);

    ApplicationResponse applicationResponse = userAuthenticationService.authenticateUser(userLogonDto.getUserName(), userLogonDto.getPassword());

    // Then
    assertNotNull(applicationResponse);
    assertNotNull(applicationResponse.getResponseStatus());
    assertEquals(ResponseStatus.ACCOUNT_LOCKED, applicationResponse.getResponseStatus());
  }

  @Test
  public void given_invalid_user_credentials_when_logon_is_executed_then_return_invalid_credentials() {
    // Given
    UserAuthenticationService userAuthenticationService = new UserAuthenticationService(mockedUserAccountDao, mockedAuthenticationManager, csrfTokenRepository);

    UserLogonDto userLogonDto = new UserLogonDto();
    userLogonDto.setUserName("testuser");
    userLogonDto.setPassword("testpassword");

    UserLogonDto invalidUserLogonDto = new UserLogonDto();
    invalidUserLogonDto.setUserName("testuser");
    invalidUserLogonDto.setPassword("no way jose");

    UserAccount userAccount = constructUserAccount(invalidUserLogonDto, AccountStatus.Active);

    // When
    when(mockedUserAccountDao.findByLogonName(userLogonDto.getUserName())).thenReturn(userAccount);

    ApplicationResponse applicationResponse = userAuthenticationService.authenticateUser(userLogonDto.getUserName(), userLogonDto.getPassword());

    // Then
    assertNotNull(applicationResponse);
    assertNotNull(applicationResponse.getResponseStatus());
    assertEquals(ResponseStatus.INVALID_CREDENTIALS, applicationResponse.getResponseStatus());
  }

  @Test
  public void given_unknown_user_credentials_when_logon_is_executed_then_return_success() {
    // Given
    UserAuthenticationService userAuthenticationService = new UserAuthenticationService(mockedUserAccountDao, mockedAuthenticationManager, csrfTokenRepository);

    UserLogonDto userLogonDto = new UserLogonDto();
    userLogonDto.setUserName("testuser");
    userLogonDto.setPassword("testpassword");

    UserAccount userAccount = constructUserAccount(userLogonDto, AccountStatus.Active);

    // When
    when(mockedUserAccountDao.findByLogonName("unknown user")).thenReturn(userAccount);

    ApplicationResponse applicationResponse = userAuthenticationService.authenticateUser(userLogonDto.getUserName(), userLogonDto.getPassword());

    // Then
    assertNotNull(applicationResponse);
    assertNotNull(applicationResponse.getResponseStatus());
    assertEquals(ResponseStatus.NO_DATA_FOUND, applicationResponse.getResponseStatus());
  }

  @Test
  public void given_valid_user_credentials_when_logon_is_executed_and_exception_is_thrown_then_return_error() {
    // Given
    UserAuthenticationService userAuthenticationService = new UserAuthenticationService(mockedUserAccountDao, mockedAuthenticationManager, csrfTokenRepository);

    UserLogonDto userLogonDto = new UserLogonDto();
    userLogonDto.setUserName("testuser");
    userLogonDto.setPassword("testpassword");

    UserAccount userAccount = constructUserAccount(userLogonDto, AccountStatus.Active);

    // When
    when(mockedUserAccountDao.findByLogonName(userLogonDto.getUserName())).thenThrow(new RuntimeException("Simulate error reading from database"));

    ApplicationResponse applicationResponse = userAuthenticationService.authenticateUser(userLogonDto.getUserName(), userLogonDto.getPassword());

    // Then
    assertNotNull(applicationResponse);
    assertNotNull(applicationResponse.getResponseStatus());
    assertEquals(ResponseStatus.ERROR, applicationResponse.getResponseStatus());
  }

  @Test
  @Ignore
  public void given_valid_user_credentials_when_extractPermissions_is_executed_then_return_permissions() {
    // Given
    UserAuthenticationService userAuthenticationService = new UserAuthenticationService(mockedUserAccountDao, mockedAuthenticationManager, csrfTokenRepository);

    UserLogonDto userLogonDto = new UserLogonDto();
    userLogonDto.setUserName("testuser");
    userLogonDto.setPassword("testpassword");

    UserAccount userAccount = constructUserAccount(userLogonDto, AccountStatus.Active);

    // When
//    when(mockedUserAccountDao.findByLogonName(userLogonDto.getUserName())).thenThrow(new RuntimeException("Simulate error reading from database"));

    Collection<Permission> permissions = userAuthenticationService.extractPermissions(true, userAccount);

    // Then
    assertNotNull(permissions);
    assertEquals(2, permissions.size());
  }

  public UserAccount constructUserAccount(UserLogonDto userLogonDto, AccountStatus accountStatus) {

    UserPassword userPassword = UserPassword.builder()
      .password(userLogonDto.getPassword())
      .validFrom(LocalDate.of(2019, 1, 1))
      .validUntil(LocalDate.MAX)
      .build();
    Collection<UserPassword> passwords = Collections.singletonList(userPassword);

    UserGroup userGroup = UserGroup.builder()
      .displayName("Test group 1")
      .name("group1")
      .id(1L)
      .groupPermissions(null)
      .build();

    UserGroupMembership groupMembership = UserGroupMembership.builder()
      .group(userGroup)
      .build();

    Collection<UserGroupMembership> groupMemberships = Collections.singletonList(groupMembership);

    return UserAccount.builder()
      .id(1L)
      .familyName("Schulewitz")
      .firstName("Adrian")
      .groupMemberships(groupMemberships)
      .logonName(userLogonDto.getUserName())
      .passwords(passwords)
      .status(accountStatus)
      .build();
  }
}
