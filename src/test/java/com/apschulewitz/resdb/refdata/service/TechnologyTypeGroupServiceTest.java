package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.refdata.model.helper.TechnologyTypeGroupTestHelper;
import com.apschulewitz.resdb.refdata.model.dao.TechnologyTypeGroupDao;
import com.apschulewitz.resdb.refdata.model.dto.TechnologyTypeGroupDto;
import com.apschulewitz.resdb.refdata.model.entity.TechnologyTypeGroup;
import com.apschulewitz.resdb.refdata.model.mapper.TechnologyTypeGroupMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@Ignore
@SpringBootTest
@RunWith(SpringRunner.class)
//@DataJpaTest
@Slf4j
public class TechnologyTypeGroupServiceTest {

  private TechnologyTypeGroupService technologyTypeGroupService;

  @MockBean
  private TechnologyTypeGroupDao mockedDao;

  @Autowired
  private TechnologyTypeGroupTestHelper technologyTypeGroupTestHelper;

  @Autowired
  private TechnologyTypeGroupDao technologyTypeGroupDao;

  @Autowired
  private TechnologyTypeGroupMapper technologyTypeGroupMapper;

  @Before
  public void beforeEachTest() {
    technologyTypeGroupService = new TechnologyTypeGroupService(technologyTypeGroupDao, technologyTypeGroupMapper);
    technologyTypeGroupDao.deleteAll();
  }

  @Test
  public void given_valid_dto_when_add_is_executed_then_return_saved_dto() {
    // given
    TechnologyTypeGroup entity = technologyTypeGroupTestHelper.constructUnsavedMinimalEntity();
    entity.setId(null);
    TechnologyTypeGroup saved = technologyTypeGroupTestHelper.constructUnsavedMinimalEntity();
    saved.setId(1L);
    TechnologyTypeGroupDto dto = technologyTypeGroupMapper.toDto(entity);
    when(mockedDao.save(any(TechnologyTypeGroup.class))).thenReturn(saved);

    // when
    TechnologyTypeGroupDto savedDto = technologyTypeGroupService.add(dto);

    // then
    assertNotNull(savedDto);
    assertNotNull(savedDto.getId());
    assertEquals(entity.getCreatedBy(), savedDto.getCreatedBy());
    assertEquals(entity.getLastUpdated(), savedDto.getLastUpdated());
    assertEquals(entity.getName(), savedDto.getName());
    assertEquals(entity.getStatus().name(), savedDto.getStatus());
    assertEquals(entity.getUpdatedBy(), savedDto.getUpdatedBy());
    assertEquals(entity.getVersionNumber(), savedDto.getVersionNumber());
  }

  @Test
  public void given_existing_id_when_deleteById_is_executed_then_return_dto_marked_as_cancelled() {
    // given
    TechnologyTypeGroup existingEntity = technologyTypeGroupTestHelper.constructUnsavedMinimalEntity();
    TechnologyTypeGroupDto existingDto = technologyTypeGroupTestHelper.constructUnsavedMinimalDto();
    when(mockedDao.findById(existingDto.getId())).thenReturn(Optional.of(existingEntity));
    when(mockedDao.save(any(TechnologyTypeGroup.class))).thenReturn(existingEntity);

    // when
    TechnologyTypeGroupDto deletedDto = technologyTypeGroupService.deleteById(existingDto.getId());

    // then
    assertNotNull(deletedDto);
  }

  @Test
  public void given_entities_when_findAll_is_executed_then_return_list() {
    // given
    TechnologyTypeGroup existingEntity1 = technologyTypeGroupTestHelper.constructUnsavedMinimalEntity();
    TechnologyTypeGroup existingEntity2 = technologyTypeGroupTestHelper.constructUnsavedMinimalEntity();
    when(mockedDao.findAll()).thenReturn(Arrays.asList(existingEntity1, existingEntity2));

    // when
    List<TechnologyTypeGroupDto> allEntities = technologyTypeGroupService.findAll(false);

    // then
    assertNotNull(allEntities);
    assertTrue(allEntities.size() == 2);
  }

  @Test
  public void given_valid_id_when_findById_is_executed_then_return_data() {
    // given
    TechnologyTypeGroup existingEntity1 = technologyTypeGroupTestHelper.constructUnsavedMinimalEntity();
    when(mockedDao.findById(anyLong())).thenReturn(Optional.of(existingEntity1));

    // when
    TechnologyTypeGroupDto found = technologyTypeGroupService.findById(1L);

    // then
    assertNotNull(found);
  }

  @Test
  public void given_valid_dto_with_minimal_data_when_update_is_executed_then_return_saved_dto() {
    // given
    TechnologyTypeGroup entity = technologyTypeGroupTestHelper.constructUnsavedMinimalEntity();
    TechnologyTypeGroup saved = technologyTypeGroupTestHelper.constructUnsavedMinimalEntity();
    saved.setId(1L);
    TechnologyTypeGroupDto dto = technologyTypeGroupMapper.toDto(entity);
    when(mockedDao.save(any(TechnologyTypeGroup.class))).thenReturn(saved);
    TechnologyTypeGroupDto savedDto = technologyTypeGroupService.add(dto);

    // when
    TechnologyTypeGroup updated = entity;
    updated.setId(1L);
    TechnologyTypeGroupDto updatedDto = savedDto;
    updatedDto.setName("Technology type group1 - modified");
    when(mockedDao.save(any(TechnologyTypeGroup.class))).thenReturn(updated);
    updatedDto = technologyTypeGroupService.update(updatedDto);

    // then
    assertNotNull(updatedDto);
    Long versionNumber = 1L;
    assertEquals(versionNumber, updatedDto.getId());
    assertEquals(entity.getCreatedBy(), updatedDto.getCreatedBy());
    assertEquals(entity.getLastUpdated(), updatedDto.getLastUpdated());
    assertEquals(entity.getName(), updatedDto.getName());
    assertEquals(entity.getStatus().name(), updatedDto.getStatus());
    assertEquals(entity.getUpdatedBy(), updatedDto.getUpdatedBy());
    assertEquals(entity.getVersionNumber(), updatedDto.getVersionNumber());
  }
}
