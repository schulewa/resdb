package com.apschulewitz.resdb.security.service;

import com.apschulewitz.resdb.common.ApplicationResponse;
import com.apschulewitz.resdb.common.ResponseStatus;
import com.apschulewitz.resdb.config.UserAuthenticationConfiguration;
import com.apschulewitz.resdb.security.model.dao.UserAccountDao;
import com.apschulewitz.resdb.security.model.dto.UserLogonDto;
import com.apschulewitz.resdb.security.model.entity.AccountStatus;
import com.apschulewitz.resdb.security.model.entity.Permission;
import com.apschulewitz.resdb.security.model.entity.UserAccount;
import com.apschulewitz.resdb.security.model.helper.SecurityTestHelper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class UserAuthenticationServiceTest {

  @Autowired
  private CsrfTokenRepository csrfTokenRepository;

//  @Autowired
  private UserAuthenticationConfiguration userAuthenticationConfiguration;

  @MockBean
  private UserAccountDao mockedUserAccountDao;

  @MockBean
  private AuthenticationManager mockedAuthenticationManager;

  private PasswordEncoder passwordEncoder;

  @Autowired
  private SecurityTestHelper securityTestHelper;

  @Before
  public void beforeEachTest() {
    userAuthenticationConfiguration = new UserAuthenticationConfiguration();
    userAuthenticationConfiguration.setAuthenticationStrength(12);
    passwordEncoder = userAuthenticationConfiguration.passwordEncoder();
    securityTestHelper.setPasswordEncoder(passwordEncoder);
    log.info("Before each test, passwordEncoder={}, securityTestHelper={}", passwordEncoder, securityTestHelper);
  }

  @Test
  public void given_Active_user_credentials_when_logon_is_executed_then_return_success() {
    // Given
    UserAuthenticationService userAuthenticationService = createUserAuthenticationService();

    UserLogonDto userLogonDto = new UserLogonDto();
    userLogonDto.setUserName(SecurityTestHelper.TEST_USER_NAME);
    userLogonDto.setPassword(SecurityTestHelper.TEST_USER_PASSWORD);

    UserAccount userAccount = securityTestHelper.constructActiveUserAccount();

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
    UserAuthenticationService userAuthenticationService = createUserAuthenticationService();

    UserLogonDto userLogonDto = new UserLogonDto();
    userLogonDto.setUserName(SecurityTestHelper.TEST_USER_NAME);
    userLogonDto.setPassword(SecurityTestHelper.TEST_USER_PASSWORD);

    UserAccount userAccount = securityTestHelper.constructActiveUserAccount();
    userAccount.setStatus(AccountStatus.Inactive);

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
    UserAuthenticationService userAuthenticationService = createUserAuthenticationService();

    UserLogonDto userLogonDto = new UserLogonDto();
    userLogonDto.setUserName(SecurityTestHelper.TEST_USER_NAME);
    userLogonDto.setPassword(SecurityTestHelper.TEST_USER_PASSWORD);

    UserAccount userAccount = securityTestHelper.constructActiveUserAccount();
    userAccount.setStatus(AccountStatus.Locked);

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
    UserAuthenticationService userAuthenticationService = createUserAuthenticationService();

    UserLogonDto userLogonDto = new UserLogonDto();
    userLogonDto.setUserName(SecurityTestHelper.TEST_USER_NAME);
    userLogonDto.setPassword(SecurityTestHelper.TEST_USER_PASSWORD);

    UserAccount userAccount = securityTestHelper.constructActiveUserAccount();
    userAccount.setStatus(AccountStatus.PasswordNeedsResetting);

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
    UserAuthenticationService userAuthenticationService = createUserAuthenticationService();

    UserLogonDto userLogonDto = new UserLogonDto();
    userLogonDto.setUserName(SecurityTestHelper.TEST_USER_NAME);
    userLogonDto.setPassword(SecurityTestHelper.TEST_USER_PASSWORD);

    UserAccount userAccount = securityTestHelper.constructActiveUserAccount();
    userAccount.setStatus(AccountStatus.Suspended);

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
    UserAuthenticationService userAuthenticationService = createUserAuthenticationService();

    UserLogonDto userLogonDto = new UserLogonDto();
    userLogonDto.setUserName(SecurityTestHelper.TEST_USER_NAME);
    userLogonDto.setPassword(SecurityTestHelper.TEST_USER_PASSWORD);

    UserAccount userAccount = securityTestHelper.constructActiveUserAccount();
    userAccount.setStatus(AccountStatus.Unknown);

    // When
    when(mockedUserAccountDao.findByLogonName(userLogonDto.getUserName())).thenReturn(userAccount);

    ApplicationResponse applicationResponse = userAuthenticationService.authenticateUser(userLogonDto.getUserName(), userLogonDto.getPassword());

    // Then
    assertNotNull(applicationResponse);
    assertNotNull(applicationResponse.getResponseStatus());
    assertEquals(ResponseStatus.ACCOUNT_LOCKED, applicationResponse.getResponseStatus());
  }

  @Test
  public void given_unmatched_user_password_when_logon_is_executed_then_return_invalid_credentials() {
    // Given
    UserAuthenticationService userAuthenticationService = createUserAuthenticationService();

    UserLogonDto invalidUserLogonDto = new UserLogonDto();
    invalidUserLogonDto.setUserName(SecurityTestHelper.TEST_USER_NAME);
    invalidUserLogonDto.setPassword("no way jose");

    UserAccount userAccount = securityTestHelper.constructActiveUserAccount();

    // When
    when(mockedUserAccountDao.findByLogonName(invalidUserLogonDto.getUserName())).thenReturn(userAccount);

    ApplicationResponse applicationResponse = userAuthenticationService.authenticateUser(invalidUserLogonDto.getUserName(), invalidUserLogonDto.getPassword());

    // Then
    assertNotNull(applicationResponse);
    assertNotNull(applicationResponse.getResponseStatus());
    assertEquals(ResponseStatus.INVALID_CREDENTIALS, applicationResponse.getResponseStatus());
  }

  @Test
  public void given_unknown_user_credentials_when_logon_is_executed_then_return_success() {
    // Given
    UserAuthenticationService userAuthenticationService = createUserAuthenticationService();

    UserLogonDto userLogonDto = new UserLogonDto();
    userLogonDto.setUserName(SecurityTestHelper.TEST_USER_NAME);
    userLogonDto.setPassword(SecurityTestHelper.TEST_USER_PASSWORD);

    UserAccount userAccount = securityTestHelper.constructActiveUserAccount();

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
    UserAuthenticationService userAuthenticationService = createUserAuthenticationService();

    UserLogonDto userLogonDto = new UserLogonDto();
    userLogonDto.setUserName(SecurityTestHelper.TEST_USER_NAME);
    userLogonDto.setPassword(SecurityTestHelper.TEST_USER_PASSWORD);

    // When
    when(mockedUserAccountDao.findByLogonName(userLogonDto.getUserName())).thenThrow(new RuntimeException("Simulate error reading from database"));

    ApplicationResponse applicationResponse = userAuthenticationService.authenticateUser(userLogonDto.getUserName(), userLogonDto.getPassword());

    // Then
    assertNotNull(applicationResponse);
    assertNotNull(applicationResponse.getResponseStatus());
    assertEquals(ResponseStatus.ERROR, applicationResponse.getResponseStatus());
  }

  @Test
  public void given_valid_user_credentials_with_active_groups_and_permissions_when_extractPermissions_is_executed_then_return_permissions() {
    // Given
    UserAuthenticationService userAuthenticationService = createUserAuthenticationService();

    UserLogonDto userLogonDto = new UserLogonDto();
    userLogonDto.setUserName(SecurityTestHelper.TEST_USER_NAME);
    userLogonDto.setPassword(SecurityTestHelper.TEST_USER_PASSWORD);

    UserAccount userAccount = securityTestHelper.constructActiveUserAccount();

    // When
    Collection<Permission> permissions = userAuthenticationService.extractPermissions(true, userAccount);

    // Then
    assertNotNull(permissions);
    assertEquals(2, permissions.size());
  }

  @Test
  public void given_valid_user_credentials_with_expired_permissions_when_extractPermissions_is_executed_with_onlyActive_true_then_return_permissions() {
    // Given
    UserAuthenticationService userAuthenticationService = createUserAuthenticationService();

    UserLogonDto userLogonDto = new UserLogonDto();
    userLogonDto.setUserName(SecurityTestHelper.TEST_USER_NAME);
    userLogonDto.setPassword(SecurityTestHelper.TEST_USER_PASSWORD);

    LocalDateTime validTillYesterday = LocalDateTime.now().minusDays(1);

    UserAccount userAccount = securityTestHelper.constructActiveUserAccount();
    userAccount.getGroupMemberships().stream()
      .forEach(ugp -> ugp.setValidTo(validTillYesterday));

    // When
    Collection<Permission> permissions = userAuthenticationService.extractPermissions(true, userAccount);

    // Then
    assertNotNull(permissions);
    assertEquals(0, permissions.size());
  }

  @Test
  public void given_valid_user_credentials_with_expired_permissions_when_extractPermissions_is_executed_with_onlyActive_false_then_return_permissions() {
    // Given
    UserAuthenticationService userAuthenticationService = createUserAuthenticationService();

    UserLogonDto userLogonDto = new UserLogonDto();
    userLogonDto.setUserName(SecurityTestHelper.TEST_USER_NAME);
    userLogonDto.setPassword(SecurityTestHelper.TEST_USER_PASSWORD);

    LocalDateTime validTillYesterday = LocalDateTime.now().minusDays(1);

    UserAccount userAccount = securityTestHelper.constructActiveUserAccount();
    userAccount.getGroupMemberships().stream()
      .forEach(ugp -> ugp.setValidTo(validTillYesterday));

    // When
    Collection<Permission> permissions = userAuthenticationService.extractPermissions(false, userAccount);

    // Then
    assertNotNull(permissions);
    assertEquals(2, permissions.size());
  }

  @Test
  public void given_valid_user_credentials_with_inactive_permissions_when_extractPermissions_is_executed_with_onlyActive_true_then_return_permissions() {
    // Given
    UserAuthenticationService userAuthenticationService = createUserAuthenticationService();

    UserLogonDto userLogonDto = new UserLogonDto();
    userLogonDto.setUserName(SecurityTestHelper.TEST_USER_NAME);
    userLogonDto.setPassword(SecurityTestHelper.TEST_USER_PASSWORD);

    UserAccount userAccount = securityTestHelper.constructActiveUserAccount();
    userAccount.getGroupMemberships().stream()
      .forEach(ugm -> ugm.getGroup().getGroupPermissions().stream()
                  .forEach(ugp -> ugp.getPermission().setStatus(Permission.PermissionStatus.Suspended)));

    // When
    Collection<Permission> permissions = userAuthenticationService.extractPermissions(true, userAccount);

    // Then
    assertNotNull(permissions);
    assertEquals(0, permissions.size());
  }

  @Test
  public void given_valid_user_credentials_with_expired_permissions_when_extractPermissionsAsString_is_executed_with_onlyActive_false_then_return_permissions() {
    // Given
    UserAuthenticationService userAuthenticationService = createUserAuthenticationService();

    UserLogonDto userLogonDto = new UserLogonDto();
    userLogonDto.setUserName(SecurityTestHelper.TEST_USER_NAME);
    userLogonDto.setPassword(SecurityTestHelper.TEST_USER_PASSWORD);

    LocalDateTime validTillYesterday = LocalDateTime.now().minusDays(1);

    UserAccount userAccount = securityTestHelper.constructActiveUserAccount();
    userAccount.getGroupMemberships().stream()
      .forEach(ugp -> ugp.setValidTo(validTillYesterday));

    // When
    Optional<String> optionalPermissions = userAuthenticationService.extractPermissionsAsString(false, userAccount);

    // Then
    assertNotNull(optionalPermissions);
    assertTrue(optionalPermissions.isPresent());
    assertEquals("Theory maintenance,Artefact maintenance", optionalPermissions.get());
  }

  @Test
  public void given_valid_user_credentials_with_inactive_permissions_when_extractPermissionsAsString_is_executed_with_onlyActive_true_then_return_permissions() {
    // Given
    UserAuthenticationService userAuthenticationService = createUserAuthenticationService();

    UserLogonDto userLogonDto = new UserLogonDto();
    userLogonDto.setUserName(SecurityTestHelper.TEST_USER_NAME);
    userLogonDto.setPassword(SecurityTestHelper.TEST_USER_PASSWORD);

    UserAccount userAccount = securityTestHelper.constructActiveUserAccount();
    userAccount.getGroupMemberships().stream()
      .forEach(ugm -> ugm.getGroup().getGroupPermissions().stream()
        .forEach(ugp -> ugp.getPermission().setStatus(Permission.PermissionStatus.Suspended)));

    // When
    Optional<String> optionalPermissions = userAuthenticationService.extractPermissionsAsString(true, userAccount);

    // Then
    assertNotNull(optionalPermissions);
    assertTrue(optionalPermissions.isEmpty());
  }

  private UserAuthenticationService createUserAuthenticationService() {
    return new UserAuthenticationService(mockedUserAccountDao,
      mockedAuthenticationManager,
      passwordEncoder,
      csrfTokenRepository);
  }
}
