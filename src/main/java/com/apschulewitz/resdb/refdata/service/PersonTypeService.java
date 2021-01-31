package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.service.AbstractService;
import com.apschulewitz.resdb.common.service.DataService;
import com.apschulewitz.resdb.refdata.model.dao.PersonTypeDao;
import com.apschulewitz.resdb.refdata.model.dto.PersonTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.PersonType;
import com.apschulewitz.resdb.refdata.model.mapper.PersonTypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PersonTypeService
  extends AbstractService<PersonTypeDto, PersonType, Long, PersonTypeMapper, PersonTypeDao>
  implements DataService<PersonTypeDto, PersonType, Long> {

  private PersonTypeDao personTypeDao;

  private PersonTypeMapper personTypeMapper;

  public PersonTypeService(PersonTypeDao personTypeDao, PersonTypeMapper personTypeMapper) {
    this.personTypeDao = personTypeDao;
    this.personTypeMapper = personTypeMapper;
  }

  public PersonTypeDto add(PersonTypeDto dto) {
    return add(EntityTypeEnum.PERSON_TYPE, personTypeMapper, personTypeDao, dto);
  }

  public PersonTypeDto deleteById(Long id) {
    return deleteById(EntityTypeEnum.PERSON_TYPE, personTypeMapper, personTypeDao, id);
  }

  public List<PersonTypeDto> findAll(boolean onlyActive) {
    return findAll(EntityTypeEnum.PERSON_TYPE, personTypeMapper, personTypeDao, onlyActive);
  }

  public PersonTypeDto findById(Long id) {
    return findById(EntityTypeEnum.PERSON_TYPE, personTypeMapper, personTypeDao, id);
  }

  public PersonTypeDto update(PersonTypeDto dto) {
    return update(EntityTypeEnum.PERSON_TYPE, personTypeMapper, personTypeDao, dto);
  }
}
