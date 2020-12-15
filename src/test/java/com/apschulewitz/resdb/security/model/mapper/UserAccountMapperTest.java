package com.apschulewitz.resdb.security.model.mapper;

import com.apschulewitz.resdb.refdata.model.entity.AccountStatus;
import com.apschulewitz.resdb.refdata.model.mapper.LanguageMapper;
import com.apschulewitz.resdb.security.SecurityTestHelper;
import com.apschulewitz.resdb.security.model.dto.UserAccountDto;
import com.apschulewitz.resdb.security.model.entity.UserAccount;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
public class UserAccountMapperTest {

  private UserAccountMapper userAccountMapper;
  private UserGroupMembershipMapper userGroupMembershipMapper;
  private LanguageMapper languageMapper;

  @Before
  public void beforeEachTest() {
    languageMapper = new LanguageMapper();
    userGroupMembershipMapper = new UserGroupMembershipMapper();
    userAccountMapper = new UserAccountMapper(userGroupMembershipMapper, languageMapper);
  }

  @Test
  public void given_null_useraccount_when_toDto_is_executed_then_throw_exception() {
    assertThatThrownBy(() -> userAccountMapper.toDto(null))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageContaining("Null user account cannot be mapped to dto");
  }

  @Test
  public void given_active_useraccount_when_toDto_is_executed_then_return_dto() {
    // given
    UserAccount userAccount = SecurityTestHelper.constructActiveUserAccount();

    // when
    UserAccountDto userAccountDto = userAccountMapper.toDto(userAccount);

    // then
    assertNotNull(userAccountDto);
  }

  @Test
  public void given_active_useraccount_and_activeonly_when_toDto_is_executed_then_return_dto() {
    // given
    UserAccount userAccount = SecurityTestHelper.constructActiveUserAccount();

    // when
    UserAccountDto userAccountDto = userAccountMapper.toDto(userAccount, true);

    // then
    assertNotNull(userAccountDto);
  }

  @Test
  public void given_inactive_useraccount_and_activeonly_when_toDto_is_executed_then_return_null() {
    // given
    UserAccount userAccount = SecurityTestHelper.constructActiveUserAccount();
    userAccount.setStatus(AccountStatus.Inactive);

    // when
    UserAccountDto userAccountDto = userAccountMapper.toDto(userAccount, true);

    // then
    assertNull(userAccountDto);
  }

  @Test
  public void given_inactive_useraccount_and_not_activeonly_when_toDto_is_executed_then_return_dto() {
    // given
    UserAccount userAccount = SecurityTestHelper.constructActiveUserAccount();
    userAccount.setStatus(AccountStatus.Inactive);

    // when
    UserAccountDto userAccountDto = userAccountMapper.toDto(userAccount, false);

    // then
    assertNotNull(userAccountDto);
  }

  @Test
  public void given_active_useraccount_and_no_groupmemberships_when_toDto_is_executed_then_return_dto() {
    // given
    UserAccount userAccount = SecurityTestHelper.constructActiveUserAccount();
    userAccount.setGroupMemberships(null);

    // when
    UserAccountDto userAccountDto = userAccountMapper.toDto(userAccount);

    // then
    assertNotNull(userAccountDto);
  }

  @Test
  public void given_active_useraccount_and_no_preferredlanguage_when_toDto_is_executed_then_return_dto() {
    // given
    UserAccount userAccount = SecurityTestHelper.constructActiveUserAccount();
    userAccount.setPreferredLanguage(null);

    // when
    UserAccountDto userAccountDto = userAccountMapper.toDto(userAccount);

    // then
    assertNotNull(userAccountDto);
  }
}
