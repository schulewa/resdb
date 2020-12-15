package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dao.ArtefactTypeDao;
import com.apschulewitz.resdb.refdata.model.dto.ArtefactTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.ArtefactType;
import com.apschulewitz.resdb.refdata.service.ArtefactTypeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ArtefactTypeControllerTest {

  private LocalDateTime now = LocalDateTime.now();

  private ArtefactTypeController controller;

  @Autowired
  private ArtefactTypeDao artefactTypeDao;

  @MockBean
  private ArtefactTypeService mockedService;

  @Before
  public void beforeEachTest() {
    artefactTypeDao.deleteAll();
    controller = new ArtefactTypeController(mockedService);
  }

  @WithMockUser(value = "adrian")
  @Test
  public void when_findAll_is_executed_then_return_list() {
    // Given

    ArtefactTypeDto unsaved1 = ArtefactTypeDto.builder()
      .createdBy("system")
      .lastUpdated(now)
      .name("Ceramics")
      .status(VersionStatus.New.name())
      .updatedBy("system")
      .build();

    ArtefactTypeDto unsaved2 = ArtefactTypeDto.builder()
      .createdBy("system")
      .lastUpdated(now)
      .name("Weapons")
      .status(VersionStatus.Cancel.name())
      .updatedBy("system")
      .build();

    when(mockedService.findAll(false)).thenReturn(Arrays.asList(unsaved1, unsaved2));

    // When
    ResponseEntity<List<ArtefactTypeDto>> responseEntityList = controller.findAll(false);

    // Then
    assertEquals(HttpStatus.OK, responseEntityList.getStatusCode());
    assertNotNull(responseEntityList.getBody());
    assertEquals(2, responseEntityList.getBody().size());
  }

  @WithMockUser(value = "adrian")
  @Test
  public void when_add_is_executed_then_return_saved_entity() {
    // Given
    ArtefactTypeDto unsaved = ArtefactTypeDto.builder()
      .createdBy("system")
      .lastUpdated(now)
      .name("Ceramics")
      .build();

    ArtefactTypeDto saved = ArtefactTypeDto.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Ceramics")
      .status(VersionStatus.New.name())
      .updatedBy("system")
      .build();

    when(mockedService.add(unsaved)).thenReturn(saved);

    // When
    ResponseEntity<ArtefactTypeDto> responseEntity = controller.add(unsaved);

    // Then
    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
    assertNotNull(responseEntity.getBody().getId());
  }

  @WithMockUser(value = "adrian")
  @Test
  public void when_delete_is_executed_then_return_mark_entity_as_cancelled() {
    // Given
    ArtefactTypeDto saved = ArtefactTypeDto.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Ceramics")
      .status(VersionStatus.New.name())
      .updatedBy("system")
      .build();

    ArtefactTypeDto deleted = ArtefactTypeDto.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Ceramics")
      .status(VersionStatus.Cancel.name())
      .updatedBy("system")
      .build();

    when(mockedService.deleteById(saved.getId())).thenReturn(deleted);

    // When
    ResponseEntity<ArtefactTypeDto> responseEntity = controller.delete(saved.getId());

    // Then
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    verify(mockedService, times(1)).deleteById(anyLong());

    assertEquals(deleted, responseEntity.getBody());
  }

  @Test
  @WithMockUser(value = "adrian")
  public void when_update_is_executed_then_return_updated_entity() {
    // Given
    ArtefactTypeDto saved = ArtefactTypeDto.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Ceramics")
      .status(VersionStatus.New.name())
      .build();

    ArtefactTypeDto updated = ArtefactTypeDto.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Ceramics")
      .status(VersionStatus.Amend.name())
      .updatedBy("system")
      .build();

    when(mockedService.update(saved)).thenReturn(saved);

    // When
    ResponseEntity<ArtefactTypeDto> responseUpdatedEntity = controller.update(updated);

    // Then
    assertNotNull(responseUpdatedEntity);
    assertEquals(HttpStatus.OK, responseUpdatedEntity.getStatusCode());
  }

}
