package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.service.AbstractService;
import com.apschulewitz.resdb.common.service.DataService;
import com.apschulewitz.resdb.refdata.model.dao.ImageTypeDao;
import com.apschulewitz.resdb.refdata.model.dto.ImageTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.ImageType;
import com.apschulewitz.resdb.refdata.model.mapper.ImageTypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ImageTypeService
  extends AbstractService<ImageTypeDto, ImageType, Long, ImageTypeMapper, ImageTypeDao>
  implements DataService<ImageTypeDto, ImageType, Long> {

  private ImageTypeDao imageTypeDao;

  private ImageTypeMapper imageTypeMapper;

  public ImageTypeService(ImageTypeDao imageTypeDao, ImageTypeMapper imageTypeMapper) {
    this.imageTypeDao = imageTypeDao;
    this.imageTypeMapper = imageTypeMapper;
  }

  public ImageTypeDto add(ImageTypeDto dto) {
    return add(EntityTypeEnum.IMAGE_TYPE, imageTypeMapper, imageTypeDao, dto);
  }

  public ImageTypeDto deleteById(Long id) {
    return deleteById(EntityTypeEnum.IMAGE_TYPE, imageTypeMapper, imageTypeDao, id);
  }

  public List<ImageTypeDto> findAll(boolean onlyActive) {
    return findAll(EntityTypeEnum.IMAGE_TYPE, imageTypeMapper, imageTypeDao, onlyActive);
  }

  public ImageTypeDto findById(Long id) {
    return findById(EntityTypeEnum.IMAGE_TYPE, imageTypeMapper, imageTypeDao, id);
  }

  public ImageTypeDto update(ImageTypeDto dto) {
    return update(EntityTypeEnum.IMAGE_TYPE, imageTypeMapper, imageTypeDao, dto);
  }
}
