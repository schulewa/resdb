package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.utils.StringUtils;
import com.apschulewitz.resdb.refdata.model.helper.ArtefactTypeTestHelper;
import com.apschulewitz.resdb.refdata.model.dao.ArtefactTypeDao;
import com.apschulewitz.resdb.refdata.model.dto.ArtefactTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.ArtefactType;
import com.apschulewitz.resdb.refdata.model.mapper.ArtefactTypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@RunWith(SpringRunner.class)
@WithMockUser(value = "testuser")
@Slf4j
public class ArtefactTypeServiceIT {

  @Autowired
  private ArtefactTypeTestHelper artefactTypeTestHelper;

  @Autowired
  private ArtefactTypeService artefactTypeService;

  @Autowired
  private ArtefactTypeMapper artefactTypeMapper;

  @Autowired
  private ArtefactTypeDao artefactTypeDao;

//  @MockBean
//  private ArtefactTypeDao mockedDao;

  @Before
  public void beforeEachTest() {
    artefactTypeDao.deleteAll();
  }

  @Test
  public void given_valid_dto_when_add_is_executed_then_return_saved_dto() {
    // given
    ArtefactType entity = artefactTypeTestHelper.constructUnsavedMinimalEntity();
    ArtefactTypeDto dto = artefactTypeMapper.toDto(entity);

    // when
    ArtefactTypeDto savedDto = artefactTypeService.add(dto);

    // then
    assertNotNull(savedDto);
    assertNotNull(savedDto.getId());
    assertFalse(StringUtils.isEmpty(savedDto.getCreatedBy()));
  }

  @Test
  public void given_existing_id_when_deleteById_is_executed_then_return_dto_marked_as_cancelled() {
    // given
    ArtefactTypeDto unsavedDto = artefactTypeTestHelper.constructUnsavedMinimalDto();
    ArtefactTypeDto savedDto = artefactTypeService.add(unsavedDto);

    // when
    ArtefactTypeDto deletedDto = artefactTypeService.deleteById(savedDto.getId());

    // then
    assertNotNull(deletedDto);
    assertEquals(VersionStatus.Cancel.name(), deletedDto.getStatus());
  }

  @Test
  public void given_entities_when_findAll_is_executed_then_return_list() {
    // given
    ArtefactTypeDto unsavedDto1 = artefactTypeTestHelper.constructUnsavedMinimalDto();
    ArtefactTypeDto unsavedDto2 = artefactTypeTestHelper.constructUnsavedMinimalDto();
    artefactTypeService.add(unsavedDto1);
    artefactTypeService.add(unsavedDto2);

    // when
    List<ArtefactTypeDto> allEntities = artefactTypeService.findAll(false);

    // then
    assertNotNull(allEntities);
    assertTrue(allEntities.size() == 2);
  }

  @Test
  public void given_valid_id_when_findById_is_executed_then_return_data() {
    // given
    ArtefactTypeDto unsavedDto = artefactTypeTestHelper.constructUnsavedMinimalDto();
    ArtefactTypeDto savedDto = artefactTypeService.add(unsavedDto);

    // when
    ArtefactTypeDto found = artefactTypeService.findById(savedDto.getId());

    // then
    assertNotNull(found);
  }

  @Test
  public void given_valid_dto_with_minimal_data_when_update_is_executed_then_return_saved_dto() {
    // given
    ArtefactTypeDto unsavedDto = artefactTypeTestHelper.constructUnsavedMinimalDto();
    ArtefactTypeDto savedDto = artefactTypeService.add(unsavedDto);

    // when
    savedDto.setName("Artefact type - v2");
    savedDto = artefactTypeService.update(savedDto);

    // then
    assertNotNull(savedDto);
    assertNotNull(savedDto.getId());
    assertNotNull(savedDto.getCreatedBy());
    assertEquals(VersionStatus.Amend.name(), savedDto.getStatus());
  }

  @Test
  public void given_valid_amended_dto_with_minimal_data_when_update_is_executed_then_return_saved_dto() {
    // given
    ArtefactType unsavedEntity = artefactTypeTestHelper.constructUnsavedMinimalEntity();
    ArtefactType savedEntity = artefactTypeTestHelper.constructUnsavedMinimalEntity();
    savedEntity.setId(1L);
    savedEntity.setCreatedBy("testuser");
    savedEntity.setStatus(VersionStatus.New);
    //
    ArtefactTypeDto dto = artefactTypeMapper.toDto(unsavedEntity);
    ArtefactTypeDto savedDto = artefactTypeService.add(dto);

    // when
    ArtefactType updated = artefactTypeMapper.toEntity(savedDto);
    updated.setId(1L);
    updated.setName("Artefact type - v2");
    updated.setStatus(VersionStatus.Amend);
    updated.setLastUpdated(ZonedDateTime.now(ZoneOffset.UTC));
    ArtefactTypeDto updatedDto = savedDto;
    updatedDto.setName("Artefact type - v2");
    updatedDto = artefactTypeService.update(updatedDto);

    // then
    assertNotNull(updatedDto);
  }
}
