package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.service.DataService;
import com.apschulewitz.resdb.refdata.model.dao.PersonDao;
import com.apschulewitz.resdb.refdata.model.dto.PersonDto;
import com.apschulewitz.resdb.refdata.model.entity.Person;
import com.apschulewitz.resdb.refdata.model.mapper.PersonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class PersonService implements DataService<PersonDto, Person, Long> {

  private PersonDao personDao;

  private PersonMapper personMapper;

  public PersonService(PersonDao personDao, PersonMapper personMapper) {
    this.personDao = personDao;
    this.personMapper = personMapper;
  }

  public List<PersonDto> findAll(boolean onlyActive) {
    List<PersonDto> persons = new ArrayList<>();
    Iterable<Person> personsIter;
    if (onlyActive)
      personsIter = personDao.findByStatusIn(VersionStatus.getLiveStatuses());
    else
      personsIter = personDao.findAll();

    StreamSupport.stream(personsIter.spliterator(), false)
      .forEach(e -> persons.add(personMapper.toDto(e)));
    return persons;
  }

  public List<PersonDto> findByStatusIn(List<VersionStatus> statuses) {
    String statusList = statuses.stream().map(e -> e.name()).collect(Collectors.joining(","));
    log.info("Searching for persons with status in [{}]", statusList);
    List<Person> personList = personDao.findByStatusIn(statuses);
    List<PersonDto> persons =  personList.stream()
      .map(p -> personMapper.toDto(p))
      .collect(Collectors.toList());
    log.info("Found {} persons with status in [{}]", persons.size(), statusList);
    return persons;
  }

  public PersonDto deleteById(Long id) {
    log.info("Deleting person with id {}", id);
    Optional<Person> found = personDao.findById(id);
    if (found.isEmpty()) {
      log.error("No person found for id {}", id);
      return null;
    }
    Person person = found.get();
    if (!VersionStatus.isActive(person.getStatus())) {
      log.error("No active person found for id {}", id);
      return null;
    }

    person.setStatus(VersionStatus.Cancel);
    person.setLastUpdated(LocalDateTime.now());
    // TODO setUpdatedBy to logged in user
    Person saved = personDao.save(person);
    PersonDto savedDto = personMapper.toDto(saved);
    log.info("Person with id {} marked as deleted", id);
    return savedDto;
  }

  public PersonDto add(PersonDto toBeSaved) {
    log.info("Saving person {}", toBeSaved);
    Person unsavedPerson = personMapper.toEntity(toBeSaved);
    if (unsavedPerson.getId() != null) {
      unsavedPerson.setStatus(VersionStatus.Amend);
      unsavedPerson.setLastUpdated(LocalDateTime.now());
      // TODO set updatedBy to current logged in user
    }
    Person savedPerson = personDao.save(unsavedPerson);
    log.info("Saved person [{}]", savedPerson);
    return personMapper.toDto(savedPerson);
  }

  public PersonDto update(PersonDto toBeSaved) {
    log.info("Saving person {}", toBeSaved);
    Person unsavedPerson = personMapper.toEntity(toBeSaved);
    if (unsavedPerson.getId() != null) {
      unsavedPerson.setStatus(VersionStatus.Amend);
      unsavedPerson.setLastUpdated(LocalDateTime.now());
      // TODO set updatedBy to current logged in user
    }
    Person savedPerson = personDao.save(unsavedPerson);
    log.info("Saved person [{}]", savedPerson);
    return personMapper.toDto(savedPerson);
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
