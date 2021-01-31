package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.service.AbstractService;
import com.apschulewitz.resdb.common.service.DataService;
import com.apschulewitz.resdb.refdata.model.dao.PersonDao;
import com.apschulewitz.resdb.refdata.model.dto.PersonDto;
import com.apschulewitz.resdb.refdata.model.entity.Person;
import com.apschulewitz.resdb.refdata.model.mapper.PersonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PersonService
  extends AbstractService<PersonDto, Person, Long, PersonMapper, PersonDao>
  implements DataService<PersonDto, Person, Long> {

  private PersonDao personDao;

  private PersonMapper personMapper;

  public PersonService(PersonDao personDao, PersonMapper personMapper) {
    this.personDao = personDao;
    this.personMapper = personMapper;
  }

  public PersonDto add(PersonDto dto) {
    return add(EntityTypeEnum.PERSON, personMapper, personDao, dto);
  }

  public PersonDto deleteById(Long id) {
    return deleteById(EntityTypeEnum.PERSON, personMapper, personDao, id);
  }

  public List<PersonDto> findAll(boolean onlyActive) {
    return findAll(EntityTypeEnum.PERSON, personMapper, personDao, onlyActive);
  }

  public PersonDto findById(Long id) {
    return findById(EntityTypeEnum.PERSON, personMapper, personDao, id);
  }

//  public List<PersonDto> findByStatusIn(List<VersionStatus> statuses) {
//    String statusList = statuses.stream().map(e -> e.name()).collect(Collectors.joining(","));
//    log.info("Searching for persons with status in [{}]", statusList);
//    List<Person> personList = personDao.findByStatusIn(statuses);
//    List<PersonDto> persons =  personList.stream()
//      .map(p -> personMapper.toDto(p))
//      .collect(Collectors.toList());
//    log.info("Found {} persons with status in [{}]", persons.size(), statusList);
//    return persons;
//  }

  public PersonDto update(PersonDto dto) {
    return update(EntityTypeEnum.PERSON, personMapper, personDao, dto);
  }

//  @Override
//  public PersonDto add(@NotNull PersonDto dto) {
//    if (dto.getId() != null) {
//      String errMsg = String .format("Unable to add new person as the id in dto is already set [%d]", dto.getId());
//      log.error(errMsg);
//      throw new IllegalArgumentException(errMsg);
//    }
//    return null;
//  }

//  @Override
//  public PersonDto add(Object dto) {
//    return null;
//  }
}
