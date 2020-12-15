package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.service.DataService;
import com.apschulewitz.resdb.refdata.model.dao.LanguageGroupDao;
import com.apschulewitz.resdb.refdata.model.dto.LanguageGroupDto;
import com.apschulewitz.resdb.refdata.model.entity.LanguageGroup;
import com.apschulewitz.resdb.refdata.model.mapper.LanguageGroupMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LanguageGroupService
  extends AbstractService<LanguageGroupDto, LanguageGroup, Long, LanguageGroupMapper, LanguageGroupDao>
  implements DataService<LanguageGroupDto, LanguageGroup, Long> {

  private LanguageGroupDao languageGroupDao;

  private LanguageGroupMapper languageGroupMapper;

  public LanguageGroupService(LanguageGroupDao languageGroupDao, LanguageGroupMapper languageGroupMapper) {
    this.languageGroupDao = languageGroupDao;
    this.languageGroupMapper = languageGroupMapper;
  }

  public LanguageGroupDto add(LanguageGroupDto dto) {
     return add(EntityTypeEnum.LANGUAGE_GROUP, languageGroupMapper, languageGroupDao, dto);
  }

  public LanguageGroupDto deleteById(Long id) {
    return deleteById(EntityTypeEnum.LANGUAGE_GROUP, languageGroupMapper, languageGroupDao, id);
  }

  public List<LanguageGroupDto> findAll(boolean onlyActive) {
    return findAll(EntityTypeEnum.LANGUAGE_GROUP, languageGroupMapper, languageGroupDao, onlyActive);
  }

  public LanguageGroupDto findById(Long id) {
    return findById(EntityTypeEnum.LANGUAGE_GROUP, languageGroupMapper, languageGroupDao, id);
  }

  public LanguageGroupDto update(LanguageGroupDto dto) {
    return update(EntityTypeEnum.LANGUAGE_GROUP, languageGroupMapper, languageGroupDao, dto);
  }
}
