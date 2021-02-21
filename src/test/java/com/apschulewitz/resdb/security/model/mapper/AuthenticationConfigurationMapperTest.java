package com.apschulewitz.resdb.security.model.mapper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.security.model.dto.AuthenticationConfigurationDto;
import com.apschulewitz.resdb.security.model.entity.AuthenticationConfiguration;
import com.apschulewitz.resdb.security.model.helper.AuthenticationConfigurationTestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@ContextConfiguration(classes = {AuthenticationConfigurationMapper.class, AuthenticationConfigurationTestHelper.class})
@RunWith(SpringRunner.class)
public class AuthenticationConfigurationMapperTest {

  @Autowired
  private AuthenticationConfigurationTestHelper testHelper;

  @Autowired
  private AuthenticationConfigurationMapper mapper;

  @Test
  public void test_context() {
    assertNotNull(mapper);
  }

  @Test
  public void given_entity_and_all_values_when_toDto_is_executed_then_return_dto() {
    // given
    AuthenticationConfiguration entity = testHelper.constructNewEntityWithAllValues();

    // when
    AuthenticationConfigurationDto dto = mapper.toDto(entity);

    // then
    assertNotNull(dto);
    assertNotNull(dto.getCreatedBy());
    assertNotNull(dto.getId());
    assertNotNull(dto.getLastUpdated());
    assertNotNull(dto.getMaximumPasswordLength());
    assertNotNull(dto.getMaximumPasswordAgeInDays());
    assertNotNull(dto.getMinimumLowercaseCharacters());
    assertNotNull(dto.getMinimumNumberCharacters());
    assertNotNull(dto.getMinimumPasswordLength());
    assertNotNull(dto.getMinimumSpecialCharacters());
    assertNotNull(dto.getMinimumUppercaseCharacters());
    assertNotNull(dto.getStatus());
    assertNotNull(dto.getUpdatedBy());
    assertNotNull(dto.getVersionNumber());
  }

  @Test
  public void given_entity_and_onlyActive_is_false_when_toDto_is_executed_then_return_dto() {
    // given
    AuthenticationConfiguration entity = testHelper.constructUnsavedMinimalEntity();

    // when
    AuthenticationConfigurationDto dto = mapper.toDto(entity, false);

    // then
    assertNotNull(dto);
    assertNull(dto.getCreatedBy());
    assertNull(dto.getId());
    assertNull(dto.getStatus());
    assertNull(dto.getUpdatedBy());
    assertNull(dto.getVersionNumber());
  }

  @Test
  public void given_cancelled_entity_and_onlyActive_is_true_when_toDto_is_executed_then_return_null() {
    // given
    AuthenticationConfiguration entity = testHelper.constructUnsavedMinimalEntity();

    // when
    AuthenticationConfigurationDto dto = mapper.toDto(entity, true);

    // then
    assertNull(dto);
  }

  @Test
  public void given_null_entity_when_toDto_is_executed_then_return_error() {
    // given
    AuthenticationConfiguration entity = null;

    // when
    assertThatThrownBy(() -> mapper.toDto(entity))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageContaining("Null authentication configuration cannot be mapped to dto");

    // then
  }

  @Test
  public void given_null_entity_and_onlyActive_when_toDto_is_executed_then_return_error() {
    // given
    AuthenticationConfiguration entity = null;

    // when
    assertThatThrownBy(() -> mapper.toDto(entity, false))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageContaining("Null authentication configuration cannot be mapped to dto");

    // then
  }

  @Test
  public void given_dto_and_all_values_when_toEntity_is_executed_then_return_entity() {
    // given
    AuthenticationConfigurationDto dto = testHelper.constructNewDtoWithAllValues();

    // when
    AuthenticationConfiguration entity = mapper.toEntity(dto);

    // then
    assertNotNull(entity);
    assertEquals(dto.getCreatedBy(), entity.getCreatedBy());
    assertEquals(dto.getId(), entity.getId());
    assertEquals(dto.getLastUpdated(), entity.getLastUpdated());
    assertEquals(dto.getUpdatedBy(), entity.getUpdatedBy());
  }

  @Test
  public void given_dto_with_no_status_and_onlyActive_false_when_toEntity_is_executed_then_return_entity() {
    // given
    AuthenticationConfigurationDto dto = testHelper.constructUnsavedMinimalDto();

    // when
    AuthenticationConfiguration entity = mapper.toEntity(dto, false);

    // then
    assertNotNull(entity);
    assertEquals(dto.getCreatedBy(), entity.getCreatedBy());
  }

  @Test
  public void given_dto_with_inactive_status_and_onlyActive_true_when_toEntity_is_executed_then_return_entity() {
    // given
    AuthenticationConfigurationDto dto = testHelper.constructUnsavedMinimalDto();
    dto.setStatus(VersionStatus.Cancel.name());

    // when
    AuthenticationConfiguration entity = mapper.toEntity(dto, true);

    // then
    assertNull(entity);
  }

  @Test
  public void given_null_dto_and_onlyActive_when_toEntity_is_executed_then_return_error() {
    // given
    AuthenticationConfigurationDto dto = null;

    // when
    assertThatThrownBy(() -> mapper.toEntity(dto, false))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageContaining("Null authentication configuration cannot be mapped to entity");

    // then
  }

}
