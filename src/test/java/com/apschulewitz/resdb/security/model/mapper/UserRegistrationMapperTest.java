package com.apschulewitz.resdb.security.model.mapper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.security.model.dto.UserRegistrationDto;
import com.apschulewitz.resdb.security.model.entity.RegistrationStatus;
import com.apschulewitz.resdb.security.model.entity.UserRegistration;
import com.apschulewitz.resdb.security.model.helper.UserRegistrationTestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {UserRegistrationMapper.class, UserRegistrationTestHelper.class})
public class UserRegistrationMapperTest {

  @Autowired
  private UserRegistrationMapper mapper;

  @Autowired
  private UserRegistrationTestHelper testHelper;

  @Test
  public void given_null_userregistration_when_toDto_is_executed_then_throw_exception() {
    assertThatThrownBy(() -> mapper.toDto(null))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageContaining("Null user registration cannot be mapped to dto");
  }

  @Test
  public void given_active_userregistration_when_toDto_is_executed_then_return_dto() {
    // given
    UserRegistration entity = testHelper.constructUnsavedMinimalEntity();

    // when
    UserRegistrationDto dto = mapper.toDto(entity);

    // then
    assertNotNull(dto);
  }

  @Test
  public void given_active_userregistration_and_activeonly_when_toDto_is_executed_then_return_dto() {
    // given
    UserRegistration entity = testHelper.constructUnsavedMinimalEntity();

    // when
    UserRegistrationDto dto = mapper.toDto(entity, true);

    // then
    assertNotNull(dto);
  }

  @Test
  public void given_inactive_userregistration_and_activeonly_when_toDto_is_executed_then_return_null() {
    // given
    UserRegistration entity = testHelper.constructNewEntityWithAllValues();
    entity.setRegistrationStatus(RegistrationStatus.Rejected);

    // when
    UserRegistrationDto dto = mapper.toDto(entity, true);

    // then
    assertNull(dto);
  }

  @Test
  public void given_inactive_userregistration_and_not_activeonly_when_toDto_is_executed_then_return_dto() {
    // given
    UserRegistration entity = testHelper.constructNewEntityWithAllValues();
    entity.setStatus(VersionStatus.New);

    // when
    UserRegistrationDto dto = mapper.toDto(entity, false);

    // then
    assertNotNull(dto);
  }

}
