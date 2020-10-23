package com.apschulewitz.resdb.research.service;

import com.apschulewitz.resdb.common.model.converter.ClassificationCollectionConverter;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.ClassificationTestHelper;
import com.apschulewitz.resdb.research.model.dao.ClassificationDao;
import com.apschulewitz.resdb.research.model.dto.ClassificationCollectionDto;
import com.apschulewitz.resdb.research.model.entity.ClassificationCollection;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Adrian.Schulewitz
 * @version $Id: ClassificationServiceTest.java, v 0.1 2020-10-18 20:30 adrianschulewitz.hds Exp $$
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ClassificationServiceTest {

  @MockBean
  private ClassificationDao mockedClassificationDao;

  @Autowired
  private ClassificationCollectionConverter classificationCollectionConverter;

  private ClassificationService classificationService;

  @Before
  public void beforeEachTest() {
    classificationService = new ClassificationService(mockedClassificationDao, classificationCollectionConverter);
  }

  @Test
  public void given_valid_classification_with_no_entries_when_save_is_executed_then_return_saved_entity() {
    // given
    ClassificationCollectionDto classificationCollectionDto = ClassificationTestHelper.constructNewClassificationCollectionDto(null, "Test Collection", Collections.emptyList());
    ClassificationCollection expected = classificationCollectionConverter.toEntity(classificationCollectionDto);
    expected.setId(1L);

    when(mockedClassificationDao.save(any())).thenReturn(expected);

    // when
    classificationService.save(classificationCollectionDto);

    // then
    verify(mockedClassificationDao, times(1)).save(any(ClassificationCollection.class));
  }

  @Test
  public void given_valid_classification_when_delete_is_executed_then_no_error_is_returned() {
    // given
    ClassificationCollectionDto classificationCollectionDto = ClassificationTestHelper.constructNewClassificationCollectionDto(null, "Test Collection", Collections.emptyList());
    ClassificationCollection expected = classificationCollectionConverter.toEntity(classificationCollectionDto);
    expected.setId(1L);

    when(mockedClassificationDao.save(any())).thenReturn(expected);
    ClassificationCollectionDto saved = classificationService.save(classificationCollectionDto);

    // when
    ClassificationCollectionDto dtoToBeDeleted = saved.clone();
    ClassificationCollection entityToBeDeleted = classificationCollectionConverter.toEntity(dtoToBeDeleted);
    ClassificationCollection deletedEntity = classificationCollectionConverter.toEntity(dtoToBeDeleted);
    deletedEntity.setStatus(VersionStatus.Cancel);
    when(mockedClassificationDao.findById(entityToBeDeleted.getId())).thenReturn(Optional.of(entityToBeDeleted));
    when(mockedClassificationDao.save(entityToBeDeleted)).thenReturn(deletedEntity);
    Boolean isDeleted = classificationService.delete(saved.getId());

    // then
    assertTrue(isDeleted);
  }

  @Test
  public void given_null_classification_when_delete_is_executed_then_return_false() {
    Boolean isDeleted = classificationService.delete(null);
    assertFalse(isDeleted);
  }

  @Test
  public void given_nonlive_classification_when_delete_is_executed_then_return_false() {
    // given
    ClassificationCollectionDto classificationCollectionDto = ClassificationTestHelper.constructNewClassificationCollectionDto(null, "Test Collection", Collections.emptyList());
    ClassificationCollection expected = classificationCollectionConverter.toEntity(classificationCollectionDto);
    expected.setId(1L);
    expected.setStatus(VersionStatus.Cancel);

    when(mockedClassificationDao.save(any())).thenReturn(expected);
    ClassificationCollectionDto nonLiveClassificationDto = classificationService.save(classificationCollectionDto);

    // when
    ClassificationCollectionDto dtoToBeDeleted = nonLiveClassificationDto.clone();
    ClassificationCollection entityToBeDeleted = classificationCollectionConverter.toEntity(dtoToBeDeleted);
    ClassificationCollection deletedEntity = classificationCollectionConverter.toEntity(dtoToBeDeleted);

    when(mockedClassificationDao.findById(entityToBeDeleted.getId())).thenReturn(Optional.of(entityToBeDeleted));
    when(mockedClassificationDao.save(entityToBeDeleted)).thenReturn(deletedEntity);
    Boolean isDeleted = classificationService.delete(nonLiveClassificationDto.getId());

    // then
    assertFalse(isDeleted);
  }

  @Test
  public void given_invalid_classification_when_delete_is_executed_then_return_false() {
    // given

    // when
    when(mockedClassificationDao.findById(999L)).thenReturn(Optional.empty());
    Boolean isDeleted = classificationService.delete(999L);

    // then
    assertFalse(isDeleted);
  }

  @Test
  public void when_findAllLiveCollections_is_executed_then_return_data() {
    // given

    ClassificationCollection aztecCollection = ClassificationTestHelper.constructNewClassificationCollection("Aztec Collection");
    aztecCollection.setId(1L);
    ClassificationCollection wariCollection = ClassificationTestHelper.constructNewClassificationCollection("Wari Collection");
    wariCollection.setId(2L);

    ClassificationCollectionDto savedAztecDto = classificationCollectionConverter.toDto(aztecCollection);
    savedAztecDto.setId(1L);

    ClassificationCollectionDto savedWariDto = classificationCollectionConverter.toDto(wariCollection);
    savedWariDto.setId(2L);

    List<ClassificationCollection> allCollections = new ArrayList<>();
    allCollections.add(aztecCollection);
    allCollections.add(wariCollection);

    when(mockedClassificationDao.findByStatusIn(VersionStatus.getLiveStatuses())).thenReturn(allCollections);

    // when
    List<ClassificationCollectionDto> collections = classificationService.findAllLiveCollections();

    // then
    assertNotNull(collections);
    assertEquals(2, collections.size());

    List<ClassificationCollectionDto> aztecs = collections.stream().filter(c -> c.getName().startsWith("Az")).collect(Collectors.toList());
    assertNotNull(aztecs);
    assertEquals(1, aztecs.size());

    List<ClassificationCollectionDto> wari = collections.stream().filter(c -> c.getName().startsWith("Wa")).collect(Collectors.toList());
    assertNotNull(wari);
    assertEquals(1, wari.size());

    collections.removeAll(aztecs);
    collections.removeAll(wari);

    assertEquals(0, collections.size());
  }

  @Test
  public void given_valid_classification_id_when_findById_is_executed_then_return_data() {
    // given
    ClassificationCollectionDto classificationCollectionDto = ClassificationTestHelper.constructNewClassificationCollectionDto(null, "Aztec Collection", Collections.emptyList());
    ClassificationCollection classificationCollection = classificationCollectionConverter.toEntity(classificationCollectionDto);
    Optional<ClassificationCollection> optionalClassificationCollection = Optional.ofNullable(classificationCollection);

    // when
    when(mockedClassificationDao.findById(1L)).thenReturn(optionalClassificationCollection);
    ClassificationCollectionDto collectionDto = classificationService.findById(1L);

    // then
    assertNotNull(collectionDto);
  }

  @Test
  public void given_invalid_classification_id_when_findById_is_executed_then_return_no_data() {
    // given

    // when
    when(mockedClassificationDao.findById(1L)).thenReturn(Optional.empty());
    ClassificationCollectionDto collectionDto = classificationService.findById(1L);

    // then
    assertNull(collectionDto);
  }

  @Test
  public void when_findByStatusInAndNameStartsWith_is_executed_then_return_data() {
    // given
    ClassificationCollectionDto classificationCollectionDto = ClassificationTestHelper.constructNewClassificationCollectionDto(null, "Aztec Collection", Collections.emptyList());
    ClassificationCollectionDto classificationCollectionDto2 = ClassificationTestHelper.constructNewClassificationCollectionDto(null, "Wari Collection", Collections.emptyList());

    ClassificationCollection savedAztec = classificationCollectionConverter.toEntity(classificationCollectionDto);
    savedAztec.setId(1L);

    when(mockedClassificationDao.save(any())).thenReturn(savedAztec);

    classificationService.save(classificationCollectionDto);

    ClassificationCollection savedWari = classificationCollectionConverter.toEntity(classificationCollectionDto2);
    savedWari.setId(2L);

    when(mockedClassificationDao.save(any())).thenReturn(savedWari);

    classificationService.save(classificationCollectionDto2);

    List<ClassificationCollection> aztecSavedCollection = Arrays.asList(savedAztec);
    List<ClassificationCollection> wariSavedCollection = Arrays.asList(savedWari);

    when(mockedClassificationDao.findByStatusInAndNameStartsWith(anyList(), anyString())).thenReturn(aztecSavedCollection);

    // when
    List<ClassificationCollectionDto> aztecCollection = classificationService.findByNameStartsWith("Az");

    when(mockedClassificationDao.findByStatusInAndNameStartsWith(anyList(), eq("Wa"))).thenReturn(wariSavedCollection);

    List<ClassificationCollectionDto> wariCollection = classificationService.findByNameStartsWith("Wa");

    // then
    assertNotNull(aztecCollection);
    assertEquals(1, aztecCollection.size());
    assertTrue(aztecCollection.get(0).getName().startsWith("Az"));

    assertNotNull(wariCollection);
    assertEquals(1, wariCollection.size());
    assertTrue(wariCollection.get(0).getName().startsWith("Wa"));
  }

  @Test
  public void when_constructNullClassificationCollectionDto_is_executed_then_return_null_object() {
    ClassificationCollectionDto dto = classificationService.constructNullClassificationCollectionDto();
    assertNull(dto);
  }

  private ClassificationCollectionDto constructNewCollectionDtoWithEntries(Long collectionId, String collectionName) {
    ClassificationCollectionDto collectionDto = new ClassificationCollectionDto();
    collectionDto.setId(collectionId);
    collectionDto.setName(collectionName);
    collectionDto.setStatus(VersionStatus.New);
    return collectionDto;
  }
}
