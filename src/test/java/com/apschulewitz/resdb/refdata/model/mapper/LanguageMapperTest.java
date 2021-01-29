package com.apschulewitz.resdb.refdata.model.mapper;

import com.apschulewitz.resdb.refdata.model.helper.LanguageTestHelper;
import com.apschulewitz.resdb.refdata.model.dto.LanguageDto;
import com.apschulewitz.resdb.refdata.model.entity.Language;
import org.apache.commons.lang3.BooleanUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@ContextConfiguration(classes = {LanguageMapper.class, LanguageGroupMapper.class, LanguageTestHelper.class})
@RunWith(SpringRunner.class)
public class LanguageMapperTest {

  @Autowired
  private LanguageTestHelper languageTestHelper;

  @Autowired
  private LanguageMapper languageMapper;

  @Test
  public void test_context() {
    assertNotNull(languageMapper);
  }

  @Test
  public void given_null_entity_and_mandatory_values_when_toDto_is_executed_then_return_error() {
    // given
    Language entity = null;

    // when
    assertThatThrownBy(() -> languageMapper.toDto(entity))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageContaining("Null language cannot be mapped to dto");

    // then
  }

  @Test
  public void given_entity_and_all_values_when_toDto_is_executed_then_return_dto() {
    // given
    Language entity = languageTestHelper.constructUnsavedMinimalEntity();

    // when
    LanguageDto dto = languageMapper.toDto(entity);

    // then
    boolean entityConstructed = BooleanUtils.toBoolean(entity.getConstructed().getCode());
    boolean entityDeciphered = BooleanUtils.toBoolean(entity.getDeciphered().getCode());
    boolean entityLiving = BooleanUtils.toBoolean(entity.getLiving().getCode());
    boolean entityMacro = BooleanUtils.toBoolean(entity.getMacroLanguage().getCode());
    assertNotNull(dto);
    assertEquals(entityConstructed, dto.getConstructed());
    assertEquals(entity.getCreatedBy(), dto.getCreatedBy());
    assertEquals(entityDeciphered, dto.getDeciphered());
    assertEquals(entity.getId(), dto.getId());
    assertEquals(entity.getLastUpdated(), dto.getLastUpdated());
    assertEquals(entityLiving, dto.getLiving());
    assertEquals(entityMacro, dto.getMacroLanguage());
    assertEquals(entity.getIso6391Code1(), dto.getIso6391Code1());
    assertEquals(entity.getIso6392CodeAlpha2b(), dto.getIso6392CodeAlpha2b());
    assertEquals(entity.getIso6392CodeAlpha2t(), dto.getIso6392CodeAlpha2t());
    assertEquals(entity.getIso6392CodeAlpha3(), dto.getIso6392CodeAlpha3());
    assertEquals(entity.getName(), dto.getName());
    assertEquals(entity.getNativeName(), dto.getNativeName());
    assertNull(dto.getCreatedBy());
    assertNull(dto.getId());
    assertNull(dto.getLastUpdated());
    assertNull(dto.getStatus());
    assertNull(dto.getUpdatedBy());
    assertNull(dto.getVersionNumber());
  }

  @Test
  public void given_entity_and_mandatory_values_when_toDto_is_executed_then_return_dto() {
    // given
    Language entity = languageTestHelper.constructUnsavedMinimalEntity();

    // when
    LanguageDto dto = languageMapper.toDto(entity);

    // then
    assertNotNull(dto);
    assertNull(dto.getCreatedBy());
    assertNull(dto.getId());
    assertNull(dto.getLastUpdated());
    assertNull(dto.getStatus());
    assertNull(dto.getUpdatedBy());
    assertNull(dto.getVersionNumber());
    assertEquals(false, dto.getConstructed());
    assertEquals(false, dto.getDeciphered());
    assertEquals(false, dto.getLiving());
    assertEquals(false, dto.getMacroLanguage());
    assertEquals(entity.getIso6391Code1(), dto.getIso6391Code1());
    assertEquals(entity.getIso6392CodeAlpha2b(), dto.getIso6392CodeAlpha2b());
    assertEquals(entity.getIso6392CodeAlpha2t(), dto.getIso6392CodeAlpha2t());
    assertEquals(entity.getIso6392CodeAlpha3(), dto.getIso6392CodeAlpha3());
    assertEquals(entity.getName(), dto.getName());
    assertEquals(entity.getNativeName(), dto.getNativeName());
  }

  @Test
  public void given_null_dto_and_mandatory_values_when_toEntity_is_executed_then_return_error() {
    // given
    LanguageDto dto = null;

    // when
    assertThatThrownBy(() -> languageMapper.toEntity(dto))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageContaining("Null language cannot be mapped to entity");

    // then
  }

  @Test
  public void given_dto_and_all_values_when_toEntity_is_executed_then_return_entity() {
    // given
    LanguageDto dto = languageTestHelper.constructUnsavedMinimalDto();

    // when
    Language entity = languageMapper.toEntity(dto);

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
    LanguageDto dto = languageTestHelper.constructUnsavedMinimalDto();

    // when
    Language entity = languageMapper.toEntity(dto);

    // then
    assertNotNull(entity);
    assertEquals(dto.getName(), entity.getName());
    assertEquals(dto.getNativeName(), entity.getNativeName());
    assertEquals("N", entity.getConstructed().getCode());
    assertEquals("N", entity.getDeciphered().getCode());
    assertEquals("N", entity.getLiving().getCode());
    assertEquals("N", entity.getMacroLanguage().getCode());
    assertEquals(dto.getCreatedBy(), entity.getCreatedBy());
    assertEquals(dto.getId(), entity.getId());
    assertEquals(dto.getLastUpdated(), entity.getLastUpdated());
    assertEquals(dto.getUpdatedBy(), entity.getUpdatedBy());
  }

  @Test
  public void given_dto_and_all_booleans_false_when_toEntity_is_executed_then_return_no_error() {
    // given
    LanguageDto dto = languageTestHelper.constructUnsavedMinimalDto();

    // when
    Language entity = languageMapper.toEntity(dto);

    // then
    assertNotNull(entity);
    assertEquals("N", entity.getConstructed().getCode());
    assertEquals("N", entity.getDeciphered().getCode());
    assertEquals("N", entity.getLiving().getCode());
    assertEquals("N", entity.getMacroLanguage().getCode());
  }

  @Test
  public void given_dto_and_all_booleans_true_when_toEntity_is_executed_then_return_no_error() {
    // given
    LanguageDto dto = languageTestHelper.constructUnsavedMinimalDto();

    // when
    Language entity = languageMapper.toEntity(dto);

    // then
    assertNotNull(entity);
    assertEquals("N", entity.getConstructed().getCode());
    assertEquals("N", entity.getDeciphered().getCode());
    assertEquals("N", entity.getLiving().getCode());
    assertEquals("N", entity.getMacroLanguage().getCode());
  }
}
