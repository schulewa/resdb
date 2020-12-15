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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class SecurityTestHelper {

  public static final String TEST_USER_NAME = "testuser";
  public static final String TEST_USER_PASSWORD = "testpassword";

  public static final LocalDate VALID_FROM_DATE = LocalDate.of(2019, 1, 1);
  public static final LocalTime VALID_FROM_TIME = LocalTime.of(9, 1, 1, 0);

  public static final LocalDateTime VALID_FROM_DATE_TIME = LocalDateTime.of(VALID_FROM_DATE, VALID_FROM_TIME);

  public static UserAccount constructActiveUserAccount() {
    Region region = Region.builder()
      .createdBy(TEST_USER_NAME)
      .name("region")
      .status(VersionStatus.New)
      .id(1L)
      .operation(DataOperation.CREATE)
      .build();

    LanguageGroup languageGroup = LanguageGroup.builder()
      .createdBy(TEST_USER_NAME)
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
      .password(TEST_USER_PASSWORD)
      .validFrom(VALID_FROM_DATE)
//      .id(1L)
      .build();
    Collection<UserPassword> passwords = Collections.singletonList(userPassword);

    UserGroup userGroup = UserGroup.builder()
      .displayName("User group display name")
//      .id(1L)
      .name("User group")
//      .groupPermissions(Arrays.asList(userGroupPermission))
      .build();

    UserGroupMembership userGroupMembership = UserGroupMembership.builder()
      .group(userGroup)
//      .id(1L)
      .validFrom(VALID_FROM_DATE_TIME)
      .build();
    Collection<UserGroupMembership> groupMemberships = Collections.singletonList(userGroupMembership);

    Permission theoryMaintenancePermission = Permission.builder()
      .operationType(Permission.OperationType.CreateReadUpdateDelete)
      .status(Permission.PermissionStatus.Active)
      .name("Theory maintenance")
      .build();

    Permission artefactMaintenancePermission = Permission.builder()
      .operationType(Permission.OperationType.CreateReadUpdateDelete)
      .status(Permission.PermissionStatus.Active)
      .name("Artefact maintenance")
      .build();

    UserGroupPermission theoryMaintenanceUserGroupPermission = UserGroupPermission.builder()
      .group(userGroup)
      .permission(theoryMaintenancePermission)
      .build();

    UserGroupPermission artefactMaintenanceUserGroupPermission = UserGroupPermission.builder()
      .group(userGroup)
      .permission(artefactMaintenancePermission)
      .build();

    Collection<UserGroupPermission> groupPermissions = Arrays.asList(theoryMaintenanceUserGroupPermission, artefactMaintenanceUserGroupPermission);

//    UserGroupPermission userGroupPermission = UserGroupPermission.builder()
//      .id(1L)
//      .permission(permission)
//      .build();
//


//    userGroupPermission.setGroup(userGroup);



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


    userPassword.setUser(userAccount);
    userGroupMembership.setUser(userAccount);
    userGroup.setGroupPermissions(groupPermissions);

//    Collection<UserGroupPermission> groupPermissions = Arrays.asList(theoryMaintenanceUserGroupPermission, artefactMaintenanceUserGroupPermission);

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
