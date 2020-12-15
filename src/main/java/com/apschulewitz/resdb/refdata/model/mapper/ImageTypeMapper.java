package com.apschulewitz.resdb.refdata.model.mapper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.model.mapper.VersionableEntityDtoMapper;
import com.apschulewitz.resdb.refdata.model.dto.ImageTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.ImageType;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ImageTypeMapper implements VersionableEntityDtoMapper<ImageType, ImageTypeDto> {


  @Override
  public ImageType toEntity(ImageTypeDto dto) {

    return ImageType.builder()
      .createdBy(dto.getCreatedBy())
      .id(dto.getId())
      .lastUpdated(dto.getLastUpdated())
      .name(dto.getName())
      .status(VersionStatus.getInstance(dto.getStatus()))
      .build();
  }

  @Override
  public ImageTypeDto toDto(ImageType entity) {
    if (entity == null) {
      throw new IllegalArgumentException("Null image type cannot be mapped to dto");
    }

    return ImageTypeDto.builder()
      .createdBy(entity.getCreatedBy())
      .id(entity.getId())
      .lastUpdated(entity.getLastUpdated())
      .name(entity.getName())
      .status(entity.getStatus().name())
      .build();
  }

  @Override
  public ImageTypeDto toDto(ImageType entity, boolean onlyActive) {
    if (VersionStatus.getLiveStatuses().contains(entity.getStatus()) || !onlyActive) {
      return toDto(entity);
    }
    return null;
  }

  @Override
  public ImageType toEntity(ImageTypeDto dto, boolean onlyActive) {
    return null;
  }

}
