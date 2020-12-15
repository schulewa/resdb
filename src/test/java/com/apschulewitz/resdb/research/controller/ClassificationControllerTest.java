package com.apschulewitz.resdb.research.controller;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.research.model.ClassificationTestHelper;
import com.apschulewitz.resdb.research.model.dto.ClassificationCollectionDto;
import com.apschulewitz.resdb.research.service.ClassificationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClassificationControllerTest {

  private ClassificationController classificationController;

  @MockBean
  private ClassificationService mockedClassificationService;

  @Before
  public void beforeEachTest() {
    classificationController = new ClassificationController(mockedClassificationService);
  }

  @Test
  public void given_valid_classification_when_add_is_executed_then_classification_is_saved() {
    ClassificationCollectionDto unsavedCollection = ClassificationTestHelper.constructNewArtefactClassificationCollectionDto(); //constructNewClassificationCollectionDto();

    ClassificationCollectionDto saved = unsavedCollection.clone();
    saved.setId(1L);
    when(mockedClassificationService.save(any())).thenReturn(saved);

    ResponseEntity<ClassificationCollectionDto> responseEntity = classificationController.add(unsavedCollection);

    assertNotNull(responseEntity);
    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    ClassificationCollectionDto responseDto = responseEntity.getBody();
    assertNotNull(responseDto);
    assertEquals(Long.valueOf(1L), responseDto.getId());
  }

  @Test
  public void given_valid_classification_when_update_is_executed_then_classification_is_saved() {
    ClassificationCollectionDto unsavedCollection = ClassificationTestHelper.constructNewArtefactClassificationCollectionDto(); //constructNewClassificationCollectionDto();

    ClassificationCollectionDto saved = unsavedCollection.clone();
    saved.setId(1L);

    saved.setName("Updated Name");

    when(mockedClassificationService.save(any())).thenReturn(saved);

    ResponseEntity<ClassificationCollectionDto> responseEntity = classificationController.update(unsavedCollection);

    assertNotNull(responseEntity);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    ClassificationCollectionDto responseDto = responseEntity.getBody();
    assertNotNull(responseDto);
    assertEquals(Long.valueOf(1L), responseDto.getId());
    assertEquals("Updated Name", responseDto.getName());
  }

  @Test
  public void given_valid_classification_when_delete_is_executed_then_no_error_is_returned() {
    ClassificationCollectionDto unsavedCollection = ClassificationTestHelper.constructNewArtefactClassificationCollectionDto(); //constructNewClassificationCollectionDto();

    ClassificationCollectionDto saved = unsavedCollection.clone();
    saved.setId(1L);

    ClassificationCollectionDto deleted = saved.clone();
    deleted.setStatus(VersionStatus.Cancel);

    when(mockedClassificationService.save(any())).thenReturn(saved);

    // save initial collection
    ResponseEntity<ClassificationCollectionDto> addResponse = classificationController.add(unsavedCollection);

    assertNotNull(addResponse);
    assertEquals(HttpStatus.CREATED, addResponse.getStatusCode());
    ClassificationCollectionDto responseDto = addResponse.getBody();
    assertNotNull(responseDto);
    assertEquals(Long.valueOf(1L), responseDto.getId());

    // now delete it
    when(mockedClassificationService.delete(saved.getId())).thenReturn(deleted);
    ResponseEntity<ClassificationCollectionDto> deleteResponse = classificationController.delete(saved.getId());
    assertEquals(HttpStatus.OK, deleteResponse.getStatusCode());
    assertNotNull(deleteResponse.getBody());
  }

  @Test
  public void when_findall_is_executed_and_onlyactive_is_false_and_no_data_is_found_then_return_no_data_and_no_error() {
    ResponseEntity<List<ClassificationCollectionDto>> responseEntity = classificationController.findAll(false);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    List<ClassificationCollectionDto> data = responseEntity.getBody();
    assertNotNull(data);
    assertTrue(data.isEmpty());
  }

}
