package com.apschulewitz.resdb.security.model.entity;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class UserAccountTest {

  @Test
  public void given_list_of_passwords_when_getCurrentPassword_is_executed_then_return_current() {
    // Given
    UserPassword userPasswordOld = UserPassword.builder()
      .password("old")
      .validFrom(LocalDate.of(2019,1, 1))
      .validUntil(LocalDate.of(2019, 3,1))
      .build();
    UserPassword userPasswordCurrent = UserPassword.builder()
      .password("current")
      .validFrom(LocalDate.of(2019, 3, 1))
      .build();
    List<UserPassword> userPasswords = Arrays.asList(userPasswordOld, userPasswordCurrent);
    UserAccount userAccount = UserAccount.builder()
      .passwords(userPasswords)
      .build();

    // When
    Optional<UserPassword> currentPassword = userAccount.getCurrentPassword();

    // Then
    assertTrue(currentPassword.isPresent());
    assertEquals(userPasswordCurrent, currentPassword.get());
  }

  @Test
  public void given_latest_password_of_today_when_getCurrentPassword_is_executed_then_return_current() {
    // Given
    UserPassword userPasswordOld = UserPassword.builder()
      .password("old")
      .validFrom(LocalDate.of(2019,1, 1))
      .validUntil(LocalDate.of(2019, 3,1))
      .build();
    UserPassword userPasswordCurrent = UserPassword.builder()
      .password("current")
      .validFrom(LocalDate.now())
      .build();
    List<UserPassword> userPasswords = Arrays.asList(userPasswordOld, userPasswordCurrent);
    UserAccount userAccount = UserAccount.builder()
      .passwords(userPasswords)
      .build();

    // When
    Optional<UserPassword> currentPassword = userAccount.getCurrentPassword();

    // Then
    assertTrue(currentPassword.isPresent());
    assertEquals(userPasswordCurrent, currentPassword.get());
  }

  @Test
  public void given_latest_password_in_future_when_getCurrentPassword_is_executed_then_return_present_password() {
    // Given
    UserPassword userPasswordPresent = UserPassword.builder()
      .password("present")
      .validFrom(LocalDate.of(2019,1, 1))
      .validUntil(LocalDate.of(2022, 1,1))
      .build();
    UserPassword userPasswordFuture = UserPassword.builder()
      .password("future")
      .validFrom(LocalDate.of(2022, 1, 1))
      .build();
    List<UserPassword> userPasswords = Arrays.asList(userPasswordPresent, userPasswordFuture);
    UserAccount userAccount = UserAccount.builder()
      .passwords(userPasswords)
      .build();

    // When
    Optional<UserPassword> currentPassword = userAccount.getCurrentPassword();

    // Then
    assertTrue(currentPassword.isPresent());
    assertEquals(userPasswordPresent, currentPassword.get());
  }

  @Test
  public void given_empty_list_of_passwords_when_getCurrentPassword_is_executed_then_return_current() {
    // Given
    UserAccount userAccount = UserAccount.builder()
      .passwords(null)
      .build();

    // When
    Optional<UserPassword> currentPassword = userAccount.getCurrentPassword();

    // Then
    assertFalse(currentPassword.isPresent());
  }
}
