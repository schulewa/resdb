package com.apschulewitz.resdb.common.service;

import com.apschulewitz.resdb.common.model.VersionableDataDto;
import com.apschulewitz.resdb.common.model.VersionableDataEntity;
import com.apschulewitz.resdb.common.model.dao.DataDao;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.model.mapper.VersionableEntityDtoMapper;
import com.apschulewitz.resdb.common.model.validator.DataComparator;
import com.apschulewitz.resdb.research.model.AbstractTestHelper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@Slf4j
public class DataServiceTestRunner<D extends VersionableDataDto<K>,
                                E extends VersionableDataEntity<K>,
                                K,
                                C extends DataComparator<D,K>,
                                H extends AbstractTestHelper<E, D>,
                                M extends VersionableEntityDtoMapper<E, D>,
                                R extends DataDao<E, K> & CrudRepository<E, K>,
                                S extends AbstractService<D, E, K, M, R>> {

  private H testHelper;
  private S service;
  private C comparator;

  public DataServiceTestRunner(H testHelper, S service, C comparator) {
    this.testHelper = testHelper;
    this.service = service;
    this.comparator = comparator;
  }

  @Test
  public void given_valid_dto_when_add_is_executed_then_return_saved_dto() {
    // given
    D unsavedDto = testHelper.constructUnsavedMinimalDto();

    // when
    D savedDto = service.add(unsavedDto);

    // then
    assertNotNull(savedDto);
    assertNotNull(savedDto.getId());
    assertNotNull(savedDto.getCreatedBy());
    assertNull(savedDto.getLastUpdated());
    assertTrue(comparator.areEqual(unsavedDto, savedDto));
    assertEquals(VersionStatus.New.name(), savedDto.getStatus());
    assertNull(savedDto.getUpdatedBy());
    assertTrue(savedDto.getVersionNumber() == 1);
  }

  @Test
  public void given_existing_id_when_deleteById_is_executed_then_return_dto_marked_as_cancelled() {
    // given
    D unsavedDto = testHelper.constructUnsavedMinimalDto();
    D savedDto = service.add(unsavedDto);

    // when
    D deletedDto = service.deleteById(savedDto.getId());

    // then
    assertNotNull(deletedDto);
    assertEquals(VersionStatus.Cancel.name(), deletedDto.getStatus());
  }

  @Test
  public void given_entities_when_findAll_is_executed_then_return_list() {
    // given
    List<D> unsavedDtos = testHelper.constructListOfUnsavedMinimalDto();
    service.add(unsavedDtos.get(0));
    service.add(unsavedDtos.get(1));

    // when
    List<D> allEntities = service.findAll(false);

    // then
    assertNotNull(allEntities);
    assertTrue(allEntities.size() == 2);
    assertTrue(comparator.areEqual(unsavedDtos.get(0), allEntities.get(0)));
    assertTrue(comparator.areEqual(unsavedDtos.get(1), allEntities.get(1)));
  }

  @Test
  public void given_valid_id_when_findById_is_executed_then_return_data() {
    // given
    D unsavedDto = testHelper.constructUnsavedMinimalDto();
    D savedDto = service.add(unsavedDto);

    // when
    D found = service.findById(savedDto.getId());

    // then
    assertNotNull(found);
  }

  @Test
  public void given_valid_dto_with_minimal_data_when_update_is_executed_then_return_saved_dto() {
    // given
    D unsavedDto = testHelper.constructUnsavedMinimalDto();
    D savedDto = service.add(unsavedDto);

    // when
    savedDto = testHelper.constructModifiedMinimalDto(savedDto);
    D updatedDto = service.update(savedDto);

    // then
    assertNotNull(updatedDto);
    assertEquals(savedDto.getId(), updatedDto.getId());
    assertEquals(savedDto.getCreatedBy(), updatedDto.getCreatedBy());
    assertNotNull(updatedDto.getLastUpdated());
    assertTrue(comparator.areEqual(savedDto, updatedDto));
    assertEquals(VersionStatus.Amend.name(), updatedDto.getStatus());
    assertNotNull(updatedDto.getUpdatedBy());
    assertTrue(updatedDto.getVersionNumber() == 2);
  }

  @Test
  public void given_valid_amended_dto_with_minimal_data_when_update_is_executed_then_return_saved_dto() {
    // given
    D unsavedDto = testHelper.constructUnsavedMinimalDto();
    D savedDto = service.add(unsavedDto);
    D updatedDto = testHelper.constructModifiedMinimalDto(savedDto);
    updatedDto = service.update(updatedDto);

    // when
    updatedDto = testHelper.constructModifiedMinimalDto(updatedDto);
    D updatedDto2 = service.update(updatedDto);

    // then
    assertNotNull(updatedDto2);
    assertEquals(savedDto.getId(), updatedDto2.getId());
    assertEquals(savedDto.getCreatedBy(), updatedDto2.getCreatedBy());
    assertNotNull(updatedDto2.getLastUpdated());
    assertTrue(comparator.areEqual(updatedDto, updatedDto2));
    assertEquals(VersionStatus.Amend.name(), updatedDto2.getStatus());
    assertNotNull(updatedDto2.getUpdatedBy());
    assertTrue(updatedDto2.getVersionNumber() == 3);
  }
}
