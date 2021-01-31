package com.apschulewitz.resdb.refdata.model.mapper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dto.AddressTypeDto;
import com.apschulewitz.resdb.refdata.model.dto.CountryDto;
import com.apschulewitz.resdb.refdata.model.entity.AddressType;
import com.apschulewitz.resdb.refdata.model.entity.Country;
import com.apschulewitz.resdb.refdata.model.helper.AddressTypeTestHelper;
import com.apschulewitz.resdb.refdata.model.helper.CountryTestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@ContextConfiguration(classes = {CountryMapper.class, ImageMapper.class, ImageTypeMapper.class, CountryTestHelper.class})
@RunWith(SpringRunner.class)
public class CountryMapperTest {

  @Autowired
  private CountryTestHelper testHelper;

  @Autowired
  private CountryMapper mapper;

  @Test
  public void test_context() {
    assertNotNull(mapper);
  }

  @Test
  public void given_null_entity_and_mandatory_values_when_toDto_is_executed_then_return_error() {
    // given
    Country entity = null;

    // when
    assertThatThrownBy(() -> mapper.toDto(entity))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageContaining("Null country cannot be mapped to dto");

    // then
  }

  @Test
  public void given_entity_and_all_values_when_toDto_is_executed_then_return_dto() {
    // given
    Country entity = testHelper.constructUnsavedMinimalEntity();

    // when
    CountryDto dto = mapper.toDto(entity);

    // then
    assertNotNull(dto);
    assertEquals(entity.getName(), dto.getName());
    assertNull(dto.getCreatedBy());
    assertNull(dto.getId());
    assertNull(dto.getStatus());
    assertNull(dto.getUpdatedBy());
    assertNull(dto.getVersionNumber());
  }

  @Test
  public void given_entity_and_mandatory_values_when_toDto_is_executed_then_return_dto() {
    // given
    Country entity = testHelper.constructUnsavedMinimalEntity();

    // when
    CountryDto dto = mapper.toDto(entity);

    // then
    assertNotNull(dto);
    assertEquals(entity.getName(), dto.getName());
    assertNull(dto.getCreatedBy());
    assertNull(dto.getId());
    assertNull(dto.getStatus());
    assertNull(dto.getUpdatedBy());
    assertNull(dto.getVersionNumber());
  }

  @Test
  public void given_entity_and_onlyActive_is_false_when_toDto_is_executed_then_return_dto() {
    // given
    Country entity = testHelper.constructUnsavedMinimalEntity();

    // when
    CountryDto dto = mapper.toDto(entity, false);

    // then
    assertNotNull(dto);
    assertEquals(entity.getName(), dto.getName());
    assertNull(dto.getCreatedBy());
    assertNull(dto.getId());
    assertNull(dto.getStatus());
    assertNull(dto.getUpdatedBy());
    assertNull(dto.getVersionNumber());
  }

  @Test
  public void given_cancelled_entity_and_onlyActive_is_true_when_toDto_is_executed_then_return_null() {
    // given
    Country entity = testHelper.constructUnsavedMinimalEntity();

    // when
    CountryDto dto = mapper.toDto(entity, true);

    // then
    assertNull(dto);
  }

  @Test
  public void given_null_entity_when_toDto_is_executed_then_return_error() {
    // given
    Country entity = null;

    // when
    assertThatThrownBy(() -> mapper.toDto(entity))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageContaining("Null country cannot be mapped to dto");

    // then
  }

  @Test
  public void given_null_entity_and_onlyActive_when_toDto_is_executed_then_return_error() {
    // given
    Country entity = null;

    // when
    assertThatThrownBy(() -> mapper.toDto(entity, false))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageContaining("Null country cannot be mapped to dto");

    // then
  }

  @Test
  public void given_null_dto_and_mandatory_values_when_toEntity_is_executed_then_return_error() {
    // given
    CountryDto dto = null;

    // when
    assertThatThrownBy(() -> mapper.toEntity(dto))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageContaining("Null country cannot be mapped to entity");

    // then
  }

  @Test
  public void given_dto_and_all_values_when_toEntity_is_executed_then_return_entity() {
    // given
    CountryDto dto = testHelper.constructNewDtoWithAllValues();

    // when
    Country entity = mapper.toEntity(dto);

    // then
    assertNotNull(entity);
    assertEquals(dto.getCreatedBy(), entity.getCreatedBy());
    assertEquals(dto.getId(), entity.getId());
    assertEquals(dto.getLastUpdated(), entity.getLastUpdated());
    assertEquals(dto.getName(), entity.getName());
    assertEquals(dto.getUpdatedBy(), entity.getUpdatedBy());
  }

  @Test
  public void given_dto_and_mandatory_values_when_toEntity_is_executed_then_return_entity() {
    // given
    CountryDto dto = testHelper.constructUnsavedMinimalDto();

    // when
    Country entity = mapper.toEntity(dto);

    // then
    assertNotNull(entity);
    assertEquals(dto.getCreatedBy(), entity.getCreatedBy());
    assertEquals(dto.getId(), entity.getId());
    assertEquals(dto.getLastUpdated(), entity.getLastUpdated());
    assertEquals(dto.getName(), entity.getName());
    assertEquals(dto.getUpdatedBy(), entity.getUpdatedBy());
  }

  @Test
  public void given_dto_with_no_status_and_onlyActive_false_when_toEntity_is_executed_then_return_entity() {
    // given
    CountryDto dto = testHelper.constructUnsavedMinimalDto();

    // when
    Country entity = mapper.toEntity(dto, false);

    // then
    assertNotNull(entity);
    assertEquals(dto.getCreatedBy(), entity.getCreatedBy());
    assertEquals(dto.getName(), entity.getName());
  }

  @Test
  public void given_dto_with_inactive_status_and_onlyActive_true_when_toEntity_is_executed_then_return_entity() {
    // given
    CountryDto dto = testHelper.constructUnsavedMinimalDto();
    dto.setStatus(VersionStatus.Cancel.name());

    // when
    Country entity = mapper.toEntity(dto, true);

    // then
    assertNull(entity);
  }

  @Test
  public void given_null_dto_and_onlyActive_when_toEntity_is_executed_then_return_error() {
    // given
    CountryDto dto = null;

    // when
    assertThatThrownBy(() -> mapper.toEntity(dto, false))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageContaining("Null country cannot be mapped to entity");

    // then
  }

//  @Test
//  public void given_dto_and_all_booleans_false_when_toEntity_is_executed_then_return_no_error() {
//    // given
//    CountryDto dto = CountryTestHelper.constructNewDtoWithMandatoryValues("Address type1");
//
//    // when
//    Country entity = CountryMapper.toEntity(dto);
//
//    // then
//    assertNotNull(entity);
//  }

//  @Test
//  public void given_dto_and_all_booleans_true_when_toEntity_is_executed_then_return_no_error() {
//    // given
//    LanguageDto dto = LanguageTestHelper.constructNewDtoWithMandatoryValuesAndAllBooleansTrue("Language1");
//
//    // when
//    Language entity = languageMapper.toEntity(dto);
//
//    // then
//    assertNotNull(entity);
//    assertEquals("Y", entity.getConstructed().getCode());
//    assertEquals("Y", entity.getDeciphered().getCode());
//    assertEquals("Y", entity.getLiving().getCode());
//    assertEquals("Y", entity.getMacroLanguage().getCode());
//  }
}
