package com.apschulewitz.resdb.security;

import com.apschulewitz.resdb.common.model.entity.DataOperation;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.entity.AccountStatus;
import com.apschulewitz.resdb.refdata.model.entity.Language;
import com.apschulewitz.resdb.refdata.model.entity.LanguageGroup;
import com.apschulewitz.resdb.refdata.model.entity.Region;
import com.apschulewitz.resdb.security.model.AuthenticationResult;
import com.apschulewitz.resdb.security.model.entity.Permission;
import com.apschulewitz.resdb.security.model.entity.UserAccount;
import com.apschulewitz.resdb.security.model.entity.UserGroup;
import com.apschulewitz.resdb.security.model.entity.UserGroupMembership;
import com.apschulewitz.resdb.security.model.entity.UserGroupPermission;
import com.apschulewitz.resdb.security.model.entity.UserPassword;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;

public class SecurityTestHelper {

  public static UserAccount constructActiveUserAccount() {
    Region region = Region.builder()
      .createdBy("testuser")
      .name("region")
      .status(VersionStatus.New)
      .id(1L)
      .operation(DataOperation.CREATE)
      .build();

    LanguageGroup languageGroup = LanguageGroup.builder()
      .createdBy("testuser")
      .name("Language group")
      .status(VersionStatus.New)
      .id(1L)
      .region(region)
      .operation(DataOperation.CREATE)
      .build();

    Language language = Language.builder()
      .id(1L)
      .iso6391Code1("iso6391Code1")
      .iso6392CodeAlpha2b("iso6392CodeAlpha2b")
      .iso6392CodeAlpha2t("iso6392CodeAlpha2t")
      .iso6392CodeAlpha3("iso6392CodeAlpha3")
      .languageGroup(languageGroup)
      .build();

    UserPassword userPassword = UserPassword.builder()
      .password("password")
      .validFrom(LocalDate.of(2020, Month.OCTOBER, 1))
      .id(1L)
      .build();

    Permission permission = Permission.builder()
      .description("Permission description")
      .id(1L)
      .status(Permission.PermissionStatus.Active)
      .operationType(Permission.OperationType.Create)
      .name("Permission")
      .build();

    UserGroupPermission userGroupPermission = UserGroupPermission.builder()
      .id(1L)
      .permission(permission)
      .build();

    UserGroup userGroup = UserGroup.builder()
      .displayName("User group display name")
      .id(1L)
      .name("User group")
      .groupPermissions(Arrays.asList(userGroupPermission))
      .build();

    userGroupPermission.setGroup(userGroup);

    UserGroupMembership userGroupMembership = UserGroupMembership.builder()
      .group(userGroup)
      .id(1L)
      .build();

    UserAccount userAccount = UserAccount.builder()
      .status(AccountStatus.Active)
      .id(1L)
      .firstName("Winston")
      .familyName("Churchill")
      .logonName("winston")
      .passwords(Arrays.asList(userPassword))
      .groupMemberships(Arrays.asList(userGroupMembership))
      .preferredLanguage(language)
      .invalidAccessCount(0)
      .authenticationResult(AuthenticationResult.UnauthenticatedUser)
      .build();

    userGroupMembership.setGroup(userGroup);
    userPassword.setUser(userAccount);

    return userAccount;
  }

  public static UserGroupPermission constructActiveUserGroupPermission() {
    Permission permission1 = Permission.builder()
      .name("Permission1")
      .status(Permission.PermissionStatus.Active)
      .id(1L)
      .operationType(Permission.OperationType.Create)
      .description("Permission description 1")
      .build();

    UserGroup userGroup = UserGroup.builder()
      .displayName("User group display name")
      .id(3L)
      .name("User group name")
      .status(AccountStatus.Active)
      .build();

    UserGroupPermission userGroupPermission1 = UserGroupPermission.builder()
      .permission(permission1)
      .id(12L)
      .group(userGroup)
      .build();

    userGroup.setGroupPermissions(Collections.singletonList(userGroupPermission1));

    return userGroupPermission1;
  }
}
