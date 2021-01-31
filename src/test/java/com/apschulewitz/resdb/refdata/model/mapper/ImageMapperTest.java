package com.apschulewitz.resdb.refdata.model.mapper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dto.ImageDto;
import com.apschulewitz.resdb.refdata.model.dto.ImageTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.Image;
import com.apschulewitz.resdb.refdata.model.entity.ImageType;
import com.apschulewitz.resdb.refdata.model.helper.ImageTestHelper;
import com.apschulewitz.resdb.refdata.model.helper.ImageTypeTestHelper;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@ContextConfiguration(classes = {ImageMapper.class, ImageTestHelper.class, ImageTypeMapper.class, ImageTypeTestHelper.class})
@RunWith(SpringRunner.class)
public class ImageMapperTest {

//  @Autowired
  private ImageTestHelper testHelper = new ImageTestHelper();

  private ImageTypeMapper imageTypeMapper = new ImageTypeMapper();

  //  @Autowired
  private ImageMapper mapper = new ImageMapper(imageTypeMapper);

//  @Before
//  public void beforeAnything() {
//    testHelper = new ImageTestHelper();
//    imageTypeMapper = new ImageTypeMapper();
//    mapper = new ImageMapper(imageTypeMapper);
//  }

  @Test
  public void test_context() {
    assertNotNull(mapper);
  }

  @Test
  public void given_null_entity_and_mandatory_values_when_toDto_is_executed_then_return_error() {
    // given
    Image entity = null;

    // when
    assertThatThrownBy(() -> mapper.toDto(entity))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageContaining("Null image cannot be mapped to dto");

    // then
  }

  @Test
  public void given_entity_and_all_values_when_toDto_is_executed_then_return_dto() {
    // given
    Image entity = testHelper.constructUnsavedMinimalEntity();

    // when
    ImageDto dto = mapper.toDto(entity);

    // then
    assertNotNull(dto);
    assertEquals(entity.getFilePath(), dto.getFilePath());
    assertEquals(entity.getFileName(), dto.getFileName());
    assertEquals(entity.getImageType().getName(), dto.getImageType().getName());
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
    Image entity = testHelper.constructUnsavedMinimalEntity();

    // when
    ImageDto dto = mapper.toDto(entity);

    // then
    assertNotNull(dto);
    assertEquals(entity.getFilePath(), dto.getFilePath());
    assertEquals(entity.getFileName(), dto.getFileName());
    assertNotNull(entity.getImageType());
    assertNotNull(dto.getImageType());
    assertEquals(entity.getImageType().getName(), dto.getImageType().getName());
  }

  @Test
  public void given_entity_and_onlyActive_is_false_when_toDto_is_executed_then_return_dto() {
    // given
    Image entity = testHelper.constructUnsavedMinimalEntity();

    // when
    ImageDto dto = mapper.toDto(entity, false);

    // then
    assertNotNull(dto);
    assertEquals(entity.getFilePath(), dto.getFilePath());
    assertEquals(entity.getFileName(), dto.getFileName());
    assertNotNull(entity.getImageType());
    assertNotNull(dto.getImageType());
    assertEquals(entity.getImageType().getName(), dto.getImageType().getName());
  }

  @Test
  public void given_cancelled_entity_and_onlyActive_is_true_when_toDto_is_executed_then_return_null() {
    // given
    Image entity = testHelper.constructUnsavedMinimalEntity();

    // when
    ImageDto dto = mapper.toDto(entity, true);

    // then
    assertNull(dto);
  }

  @Test
  public void given_null_entity_when_toDto_is_executed_then_return_error() {
    // given
    Image entity = null;

    // when
    assertThatThrownBy(() -> mapper.toDto(entity))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageContaining("Null image cannot be mapped to dto");

    // then
  }

  @Test
  public void given_null_entity_and_onlyActive_when_toDto_is_executed_then_return_error() {
    // given
    Image entity = null;

    // when
    assertThatThrownBy(() -> mapper.toDto(entity, false))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageContaining("Null image cannot be mapped to dto");

    // then
  }

  @Test
  public void given_null_dto_and_mandatory_values_when_toEntity_is_executed_then_return_error() {
    // given
    ImageDto dto = null;

    // when
    assertThatThrownBy(() -> mapper.toEntity(dto))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageContaining("Null image cannot be mapped to entity");

    // then
  }

  @Test
  public void given_dto_and_all_values_when_toEntity_is_executed_then_return_entity() {
    // given
    ImageDto dto = testHelper.constructNewDtoWithAllValues();

    // when
    Image entity = mapper.toEntity(dto);

    // then
    assertNotNull(entity);
    assertEquals(dto.getCreatedBy(), entity.getCreatedBy());
    assertEquals(dto.getId(), entity.getId());
    assertEquals(dto.getLastUpdated(), entity.getLastUpdated());
    assertEquals(dto.getFilePath(), entity.getFilePath());
    assertEquals(dto.getFileName(), entity.getFileName());
    assertNotNull(entity.getImageType());
    assertNotNull(dto.getImageType());
    assertEquals(entity.getImageType().getName(), dto.getImageType().getName());
    assertEquals(entity.getStatus().name(), dto.getStatus());
    assertEquals(dto.getUpdatedBy(), entity.getUpdatedBy());
    assertNotNull(dto.getVersionNumber());
  }

  @Test
  public void given_dto_and_mandatory_values_when_toEntity_is_executed_then_return_entity() {
    // given
    ImageDto dto = testHelper.constructUnsavedMinimalDto();

    // when
    Image entity = mapper.toEntity(dto);

    // then
    assertNotNull(entity);
    assertEquals(dto.getFilePath(), entity.getFilePath());
    assertEquals(dto.getFileName(), entity.getFileName());
    assertNotNull(entity.getImageType());
    assertNotNull(dto.getImageType());
    assertEquals(entity.getImageType().getName(), dto.getImageType().getName());
  }

  @Test
  public void given_dto_with_no_status_and_onlyActive_false_when_toEntity_is_executed_then_return_entity() {
    // given
    ImageDto dto = testHelper.constructUnsavedMinimalDto();

    // when
    Image entity = mapper.toEntity(dto, false);

    // then
    assertNotNull(entity);
    assertEquals(dto.getCreatedBy(), entity.getCreatedBy());
    assertEquals(dto.getFilePath(), entity.getFilePath());
    assertEquals(dto.getFileName(), entity.getFileName());
  }

  @Test
  public void given_dto_with_inactive_status_and_onlyActive_true_when_toEntity_is_executed_then_return_entity() {
    // given
    ImageDto dto = testHelper.constructUnsavedMinimalDto();
    dto.setStatus(VersionStatus.Cancel.name());

    // when
    Image entity = mapper.toEntity(dto, true);

    // then
    assertNull(entity);
  }

  @Test
  public void given_null_dto_and_onlyActive_when_toEntity_is_executed_then_return_error() {
    // given
    ImageDto dto = null;

    // when
    assertThatThrownBy(() -> mapper.toEntity(dto, false))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageContaining("Null image cannot be mapped to entity");

    // then
  }

//  @Test
//  public void given_dto_and_all_booleans_false_when_toEntity_is_executed_then_return_no_error() {
//    // given
//    ImageDto dto = ImageTestHelper.constructNewDtoWithMandatoryValues("Address type1");
//
//    // when
//    Image entity = ImageMapper.toEntity(dto);
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
