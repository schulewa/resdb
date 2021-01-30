package com.apschulewitz.resdb.refdata.model.mapper;

import com.apschulewitz.resdb.common.model.TitleTestHelper;
import com.apschulewitz.resdb.common.model.mapper.TitleMapper;
import com.apschulewitz.resdb.refdata.model.dto.PersonTitleDto;
import com.apschulewitz.resdb.refdata.model.entity.PersonTitle;
import com.apschulewitz.resdb.refdata.model.helper.PersonTitleTestHelper;
import com.apschulewitz.resdb.research.model.helper.PersonTestHelper;
import com.apschulewitz.resdb.research.model.helper.PlaceTestHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@ContextConfiguration(classes = {PersonTitleMapper.class,
                                  PersonTitleTestHelper.class,
                                  PersonMapper.class,
                                  PersonTestHelper.class,
                                  PlaceMapper.class,
                                  PlaceTestHelper.class,
                                  TitleTestHelper.class,
                                  TitleMapper.class})
@RunWith(SpringRunner.class)
public class PersonTitleMapperTest {

  private PersonTitleMapper personTitleMapper;

  @Autowired
  private PersonTitleTestHelper personTitleTestHelper;

  @Autowired
  private PersonMapper personMapper;

  @Autowired
  private TitleMapper titleMapper;

  @Before
  public void beforeEachTest() {
    personTitleMapper = new PersonTitleMapper(personMapper, titleMapper);
  }

  @Test
  public void test_context() {
    assertNotNull(personTitleMapper);
  }

  @Test
  public void given_null_entity_and_mandatory_values_when_toDto_is_executed_then_return_error() {
    // given
    PersonTitle entity = null;

    // when
    assertThatThrownBy(() -> personTitleMapper.toDto(entity))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageContaining("Null person title cannot be mapped to dto");

    // then
  }

  @Test
  public void given_entity_and_all_values_when_toDto_is_executed_then_return_dto() {
    // given
    PersonTitle entity = personTitleTestHelper.constructUnsavedMinimalEntity();

    // when
    PersonTitleDto dto = personTitleMapper.toDto(entity);

    // then
    assertNotNull(dto);
    assertNull(dto.getCreatedBy());
    assertNull(dto.getId());
    assertNull(dto.getLastUpdated());
    assertNotNull(dto.getPerson());
    assertNotNull(dto.getPosition());
    assertNull(dto.getStatus());
    assertNotNull(dto.getTitle());
    assertNull(dto.getUpdatedBy());
    assertNull(dto.getVersionNumber());
  }

  @Test
  public void given_entity_and_mandatory_values_when_toDto_is_executed_then_return_dto() {
    // given
    PersonTitle entity = personTitleTestHelper.constructUnsavedMinimalEntity();

    // when
    PersonTitleDto dto = personTitleMapper.toDto(entity);

    // then
    assertNotNull(dto);
    assertNull(dto.getCreatedBy());
    assertNull(dto.getId());
    assertNull(dto.getLastUpdated());
    assertNotNull(dto.getPerson());
    assertNotNull(dto.getPosition());
    assertNull(dto.getStatus());
    assertNotNull(dto.getTitle());
    assertNull(dto.getUpdatedBy());
    assertNull(dto.getVersionNumber());
  }

//  @Test
//  public void given_entity_and_region_when_toDto_is_executed_then_return_dto() {
//    // given
//    PersonTitle entity = personTitleTestHelper.constructUnsavedMinimalEntity();
//
//    // when
//    PersonTitleDto dto = personTitleMapper.toDto(entity);
//
//    // then
//    assertNotNull(dto);
//    assertNull(dto.getCreatedBy());
//    assertNull(dto.getId());
//    assertNull(dto.getLastUpdated());
//    assertNull(dto.getStatus());
//    assertNull(dto.getUpdatedBy());
//    assertNull(dto.getVersionNumber());
//  }

  @Test
  public void given_null_dto_and_mandatory_values_when_toEntity_is_executed_then_return_error() {
    // given
    PersonTitleDto dto = null;

    // when
    assertThatThrownBy(() -> personTitleMapper.toEntity(dto))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageContaining("Null person title cannot be mapped to entity");

    // then
  }

  @Test
  public void given_dto_and_all_values_when_toEntity_is_executed_then_return_entity() {
    // given
    PersonTitleDto dto = personTitleTestHelper.constructNewDtoWithAllValues();

    // when
    PersonTitle entity = personTitleMapper.toEntity(dto);

    // then
    assertNotNull(entity);
    assertEquals(dto.getCreatedBy(), entity.getCreatedBy());
    assertEquals(dto.getId(), entity.getId());
    assertEquals(dto.getLastUpdated(), entity.getLastUpdated());
    assertNotNull(dto.getPerson());
    assertNotNull(dto.getPosition());
    assertNotNull(dto.getTitle());
    assertEquals(dto.getUpdatedBy(), entity.getUpdatedBy());
    assertNotNull(dto.getVersionNumber());
  }

  @Test
  public void given_dto_and_mandatory_values_when_toEntity_is_executed_then_return_entity() {
    // given
    PersonTitleDto dto = personTitleTestHelper.constructUnsavedMinimalDto();

    // when
    PersonTitle entity = personTitleMapper.toEntity(dto);

    // then
    assertNotNull(entity);
    assertEquals(dto.getCreatedBy(), entity.getCreatedBy());
    assertEquals(dto.getId(), entity.getId());
    assertEquals(dto.getLastUpdated(), entity.getLastUpdated());
    assertNotNull(dto.getPerson());
    assertNotNull(dto.getPosition());
    assertNotNull(dto.getTitle());
    assertEquals(dto.getUpdatedBy(), entity.getUpdatedBy());
  }

//  @Test
//  public void given_dto_and_region_when_toEntity_is_executed_then_return_entity() {
//    // given
//    PersonTitleDto dto = personTitleTestHelper.constructUnsavedMinimalDto();
//
//    // when
//    PersonTitle entity = personTitleMapper.toEntity(dto);
//
//    // then
//    assertNotNull(entity);
//    assertEquals(dto.getCreatedBy(), entity.getCreatedBy());
//    assertEquals(dto.getId(), entity.getId());
//    assertEquals(dto.getLastUpdated(), entity.getLastUpdated());
//    assertEquals(dto.getUpdatedBy(), entity.getUpdatedBy());
//  }

//  @Test
//  public void given_dto_and_all_booleans_false_when_toEntity_is_executed_then_return_no_error() {
//    // given
//    TaleTypeDto dto = taleTypeTestHelper.constructUnsavedMinimalDto();
//
//    // when
//    TaleType entity = taleTypeMapper.toEntity(dto);
//
//    // then
//    assertNotNull(entity);
//  }

//  @Test
//  public void given_dto_and_all_booleans_true_when_toEntity_is_executed_then_return_no_error() {
//    // given
//    TaleTypeDto dto = taleTypeTestHelper.constructUnsavedMinimalDto();
//
//    // when
//    TaleType entity = taleTypeMapper.toEntity(dto);
//
//    // then
//    assertNotNull(entity);
//  }

  @Test
  public void given_null_dto_and_onlyactive_when_toEntity_is_executed_then_return_error() {
    // given
    PersonTitleDto dto = null;

    // when
    assertThatThrownBy(() -> personTitleMapper.toEntity(dto, true))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageContaining("Null person title cannot be mapped to entity");

    // then
  }

  @Test
  public void given_null_entity_and_onlyactive_when_toDto_is_executed_then_return_error() {
    // given
    PersonTitle entity = null;

    // when
    assertThatThrownBy(() -> personTitleMapper.toDto(entity, true))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageContaining("Null person title cannot be mapped to dto");

    // then
  }

  @Test
  public void given_dto_and_onlyactive_false_when_toEntity_is_executed_then_return_entity() {
    // given
    PersonTitleDto dto = personTitleTestHelper.constructUnsavedMinimalDto();

    // when
    PersonTitle entity = personTitleMapper.toEntity(dto, false);

    // then
    assertNotNull(entity);
  }

  @Test
  public void given_entity_and_onlyactive_false_when_toDto_is_executed_then_return_dto() {
    // given
    PersonTitle entity = personTitleTestHelper.constructUnsavedMinimalEntity();

    // when
    PersonTitleDto dto = personTitleMapper.toDto(entity, false);

    // then
    assertNotNull(dto);
  }
}
