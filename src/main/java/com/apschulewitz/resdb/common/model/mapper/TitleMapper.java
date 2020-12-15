package com.apschulewitz.resdb.common.model.mapper;

import com.apschulewitz.resdb.common.model.entity.Gender;
import com.apschulewitz.resdb.common.model.entity.Title;
import com.apschulewitz.resdb.common.model.entity.TitleType;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.utils.StringUtils;
import com.apschulewitz.resdb.refdata.model.dto.TitleDto;
import org.springframework.stereotype.Component;

@Component
public class TitleMapper implements EntityMapper<Title, TitleDto> {

  @Override
  public TitleDto toDto(Title entity) {
    if (entity == null) {
      throw new IllegalArgumentException("Null Title entity supplied");
    }

    TitleDto dto = TitleDto.builder()
      .appliesTo(entity.getAppliesTo().name())
      .createdBy(entity.getCreatedBy())
      .status(entity.getStatus().name())
      .title(entity.getTitle())
      .titleType(entity.getTitleType().name())
      // optional fields
      .id(entity.getId())
      .description(entity.getDescription())
      .lastUpdated(entity.getLastUpdated())
      .updatedBy(entity.getUpdatedBy())
      .build();
    return dto;
  }

  @Override
  public Title toEntity(TitleDto dto) {
    if (dto == null) {
      return null;
    }

    VersionStatus status = VersionStatus.New;
    if (!StringUtils.isEmpty(dto.getStatus())) {
      status = VersionStatus.getInstance(dto.getStatus());
    }

    if (!TitleType.isValidName(dto.getTitleType())) {
      throw new IllegalArgumentException("Invalid title type name supplied in dto");
    }

    Title entity = Title.builder()
      .appliesTo(Gender.getGenderFor(dto.getAppliesTo()))
      .createdBy(dto.getCreatedBy())
      .status(status)
      .title(dto.getTitle())
      .titleType(TitleType.getTypeFor(dto.getTitleType()))
      // optional fields
      .id(dto.getId())
      .description(dto.getDescription())
      .lastUpdated(dto.getLastUpdated())
      .updatedBy(dto.getUpdatedBy())
      .build();
    return entity;
  }

}
