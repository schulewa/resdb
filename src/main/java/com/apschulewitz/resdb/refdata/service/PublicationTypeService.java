package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.service.AbstractService;
import com.apschulewitz.resdb.common.service.DataService;
import com.apschulewitz.resdb.refdata.model.dao.PublicationTypeDao;
import com.apschulewitz.resdb.refdata.model.dto.PublicationTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.PublicationType;
import com.apschulewitz.resdb.refdata.model.mapper.PublicationTypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PublicationTypeService
  extends AbstractService<PublicationTypeDto, PublicationType, Long, PublicationTypeMapper, PublicationTypeDao>
  implements DataService<PublicationTypeDto, PublicationType, Long> {

  private PublicationTypeDao publicationTypeDao;

  private PublicationTypeMapper publicationTypeMapper;

  public PublicationTypeService(PublicationTypeDao publicationTypeDao, PublicationTypeMapper publicationTypeMapper) {
    this.publicationTypeDao = publicationTypeDao;
    this.publicationTypeMapper = publicationTypeMapper;
  }

  public PublicationTypeDto add(PublicationTypeDto dto) {
    return add(EntityTypeEnum.PUBLICATION_TYPE, publicationTypeMapper, publicationTypeDao, dto);
  }

  public PublicationTypeDto deleteById(Long id) {
    return deleteById(EntityTypeEnum.PUBLICATION_TYPE, publicationTypeMapper, publicationTypeDao, id);
  }

  public List<PublicationTypeDto> findAll(boolean onlyActive) {
    return findAll(EntityTypeEnum.PUBLICATION_TYPE, publicationTypeMapper, publicationTypeDao, onlyActive);
  }

  public PublicationTypeDto findById(Long id) {
    return findById(EntityTypeEnum.PUBLICATION_TYPE, publicationTypeMapper, publicationTypeDao, id);
  }

  public PublicationTypeDto update(PublicationTypeDto dto) {
    return update(EntityTypeEnum.PUBLICATION_TYPE, publicationTypeMapper, publicationTypeDao, dto);
  }
}
