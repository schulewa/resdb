package com.apschulewitz.resdb.common.model.mapper;

import com.apschulewitz.resdb.common.model.entity.Gender;
import com.apschulewitz.resdb.common.model.entity.Title;
import com.apschulewitz.resdb.common.model.entity.TitleType;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dto.TitleDto;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TitleMapperTest {

  private TitleMapper titleMapper = new TitleMapper();

  @Test
  public void given_valid_dto_when_toEntity_is_executed_then_return_entity() {
    // given
    TitleDto dto = TitleDto.builder()
      .appliesTo("Male")
      .createdBy("testuser")
      .description("Title description")
      .id(1L)
      .lastUpdated(ZonedDateTime.now())
      .status("New")
      .title("Sir")
      .titleType("Prefix")
      .updatedBy("user2")
      .build();

    // when
    Title entity = titleMapper.toEntity(dto);

    // then
    assertEquals(Gender.Male, entity.getAppliesTo());
    assertEquals(dto.getCreatedBy(), entity.getCreatedBy());
    assertEquals(dto.getDescription(), entity.getDescription());
    assertEquals(dto.getId(), entity.getId());
    assertEquals(dto.getLastUpdated(), entity.getLastUpdated());
    assertEquals(VersionStatus.New, entity.getStatus());
    assertEquals(dto.getTitle(), entity.getTitle());
    assertEquals(TitleType.Prefix, entity.getTitleType());
    assertEquals(dto.getUpdatedBy(), entity.getUpdatedBy());
  }

  @Test
  public void given_dto_with_invalid_appliesTo_when_toEntity_is_executed_then_return_entity_with_unknown_title() {
    // given
    TitleDto dto = TitleDto.builder()
      .appliesTo("bad gender")
      .createdBy("testuser")
      .description("Title description")
      .id(1L)
      .lastUpdated(ZonedDateTime.now())
      .status("New")
      .title("Sir")
      .titleType("Prefix")
      .updatedBy("user2")
      .build();

    // when
    Title entity = titleMapper.toEntity(dto);

    // then
    assertNotNull(entity);
    assertEquals(Gender.Unknown, entity.getAppliesTo());
  }

  @Test
  public void given_dto_with_invalid_status_when_toEntity_is_executed_then_return_entity() {
    // given
    TitleDto dto = TitleDto.builder()
      .appliesTo("Female")
      .createdBy("testuser")
      .description("Title description")
      .id(1L)
      .lastUpdated(ZonedDateTime.now())
      .status("unknown status")
      .title("Sir")
      .titleType("Prefix")
      .updatedBy("user2")
      .build();

    // when
    assertThatThrownBy(() -> titleMapper.toEntity(dto))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageStartingWith("Invalid code or name supplied as VersionStatus");

    // then

  }

  @Test
  public void given_dto_with_invalid_titleType_when_toEntity_is_executed_then_return_entity() {
    // given
    TitleDto dto = TitleDto.builder()
      .appliesTo("Female")
      .createdBy("testuser")
      .description("Title description")
      .id(1L)
      .lastUpdated(ZonedDateTime.now())
      .status("Amend")
      .title("Sir")
      .titleType("unknown title type")
      .updatedBy("user2")
      .build();

    // when
    assertThatThrownBy(() -> titleMapper.toEntity(dto))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageStartingWith("Invalid title type name supplied in dto");

    // then

  }
}
