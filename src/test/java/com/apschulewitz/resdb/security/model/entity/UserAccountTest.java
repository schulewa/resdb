package com.apschulewitz.resdb.security.model.entity;

import com.apschulewitz.resdb.refdata.model.entity.Language;
import com.apschulewitz.resdb.refdata.model.entity.LanguageGroup;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@Slf4j
public class UserAccountTest {

    private ObjectMapper objectMapper;

    @Before
    public void beforeEachTest() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void given_list_of_passwords_when_getCurrentPassword_is_executed_then_return_current() {
        // Given
        UserPassword userPasswordOld = UserPassword.builder()
                .password("old")
                .validFrom(LocalDate.of(2019, 1, 1))
                .validUntil(LocalDate.of(2019, 3, 1))
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
                .validFrom(LocalDate.of(2019, 1, 1))
                .validUntil(LocalDate.of(2019, 3, 1))
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
                .validFrom(LocalDate.of(2019, 1, 1))
                .validUntil(LocalDate.of(2022, 1, 1))
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

    @Test
    public void given_user_and_language_when_serialised_then_return_string() throws JsonProcessingException {
        // Given
        LanguageGroup languageGroup = LanguageGroup.builder()
                .id(1L)
                .createdBy("test user")
                .lastUpdated(ZonedDateTime.now())
                .name("English")
                .build();

        Language preferredLanguage = Language.builder()
                .id(2L)
                .iso6391Code1("en")
                .iso6392CodeAlpha2t("eng")
                .iso6392CodeAlpha2b("eng")
                .iso6392CodeAlpha3("eng")
                .languageGroup(languageGroup)
                .name("English")
                .nativeName("English")
                .build();

        Permission readPermission = Permission.builder()
                .id(3L)
                .name("Read permission")
                .build();

        UserGroupPermission readUserGroupPermission = UserGroupPermission.builder()
                .id(3L)
                .permission(readPermission)
                .build();

        UserGroup adminUserGroup = UserGroup.builder()
                .id(3L)
                .displayName("Admin Group")
                .name("Admninistration Group")
                .build();

        adminUserGroup.setGroupPermissions(Collections.singleton(readUserGroupPermission));

        readUserGroupPermission.setGroup(adminUserGroup);

        UserGroupMembership ugm1 = UserGroupMembership.builder()
                .id(3L)
                .group(adminUserGroup)
                .build();

        UserAccount userAccount = UserAccount.builder()
                .id(4L)
                .logonName("test user")
                .firstName("test")
                .familyName("user")
                .groupMemberships(Collections.singleton(ugm1))
                .preferredLanguage(preferredLanguage)
                .build();

        ugm1.setUser(userAccount);
        ugm1.setGroup(adminUserGroup);

        // When
        String json = objectMapper.writeValueAsString(userAccount);

        // Then
        log.info("json={}", json);
        assertNotNull(json);
    }
}
