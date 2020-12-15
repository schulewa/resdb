package com.apschulewitz.resdb.refdata.model.dao;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.entity.AddressType;
import com.apschulewitz.resdb.refdata.model.entity.ArtefactType;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@Slf4j
public class ArtefactTypeDaoTest {

  @Autowired
  private ArtefactTypeDao artefactTypeDao;

  @Before
  public void beforeEachTest() {
    artefactTypeDao.deleteAll();
  }

  @Test
  public void given_valid_new_entity_when_save_is_executed_then_save_entity() {
    ArtefactType artefactType1 = constructNewArtefactType("Artefact type1");
    ArtefactType saved = artefactTypeDao.save(artefactType1);

    assertNotNull(saved);
    assertNotNull(saved.getId());
    assertEquals(artefactType1.getName(), saved.getName());
    assertEquals(artefactType1.getCreatedBy(), saved.getCreatedBy());
    assertEquals(artefactType1.getStatus(), saved.getStatus());
    assertEquals(artefactType1.getOperation(), saved.getOperation());
    assertNotNull(saved.getVersionNumber());
    assertNull(saved.getUpdatedBy());
    assertNull(saved.getLastUpdated());

    Iterable<ArtefactType> liveEntries = artefactTypeDao.findByStatusIn(VersionStatus.getLiveStatuses());
    List<ArtefactType> addressTypes = StreamSupport.stream(liveEntries.spliterator(), false).collect(Collectors.toList());
    assertEquals("Active artefact types size", 1, addressTypes.size());
  }

  @Test
  public void given_valid_new_entity_when_save_is_executed_multiple_times_then_version_no_is_updated() {
    ArtefactType artefactType1 = constructNewArtefactType("Artefact type1");
    ArtefactType saved = artefactTypeDao.save(artefactType1);
    saved.setName("Modified name");
    saved.setStatus(VersionStatus.Amend);
    saved = artefactTypeDao.save(saved);

    assertNotNull(saved);
    assertNotNull(saved.getId());
    assertNotNull(saved.getVersionNumber());
    assertNull(saved.getUpdatedBy());
    assertNull(saved.getLastUpdated());

    Iterable<ArtefactType> liveEntries = artefactTypeDao.findByStatusIn(VersionStatus.getLiveStatuses());
    List<ArtefactType> artefactTypes = StreamSupport.stream(liveEntries.spliterator(), false).collect(Collectors.toList());
    assertEquals(1, artefactTypes.size());
    assertTrue(artefactTypes.get(0).getVersionNumber() > 0L);
  }

  @Test
  public void given_some_live_and_some_entries_when_findByStatusIn_is_executed_then_retrieve_active_entries() {
    ArtefactType artefactType1 = constructNewArtefactType("Artefact type1");
    ArtefactType saved = artefactTypeDao.save(artefactType1);
    assertNotNull(saved.getId());

    ArtefactType artefactType2 = constructNewArtefactType("Artefact type2");
    saved = artefactTypeDao.save(artefactType2);
    assertNotNull(saved.getId());
    saved.setStatus(VersionStatus.Cancel);
    saved = artefactTypeDao.save(saved);
    assertEquals("Cancelled artefact type name", artefactType2.getName(), saved.getName());
    assertEquals("Cancelled artefact type status", VersionStatus.Cancel, saved.getStatus());

    Iterable<ArtefactType> liveEntries = artefactTypeDao.findByStatusIn(VersionStatus.getLiveStatuses());
    List<ArtefactType> addressTypes = StreamSupport.stream(liveEntries.spliterator(), false).collect(Collectors.toList());
    assertEquals("Active artefact types size", 1, addressTypes.size());
  }

  @Test
  public void given_multiple_active_address_types_when_findByStatusInAndNameStartsWithis_executed_then_retrieve_matching_active_entries() {
    ArtefactType artefactType1 = constructNewArtefactType("Ceramic");
    ArtefactType saved = artefactTypeDao.save(artefactType1);
    assertNotNull(saved.getId());

    ArtefactType artefactType2 = constructNewArtefactType("Wood");
    saved = artefactTypeDao.save(artefactType2);
    assertNotNull(saved.getId());

    ArtefactType personalEmailAddress = constructNewArtefactType("Metal Jewelry");
    saved = artefactTypeDao.save(personalEmailAddress);
    assertNotNull(saved.getId());

    ArtefactType workEmailAddress = constructNewArtefactType("Metal Weapons");
    saved = artefactTypeDao.save(workEmailAddress);
    assertNotNull(saved.getId());

    Iterable<ArtefactType> liveEntries = artefactTypeDao.findByStatusIn(VersionStatus.getLiveStatuses());
    List<ArtefactType> artefactTypes = StreamSupport.stream(liveEntries.spliterator(), false).collect(Collectors.toList());
    assertEquals("Active artefact types size", 4, artefactTypes.size());

    Iterable<ArtefactType> metalEntries = artefactTypeDao.findByStatusInAndNameStartsWith(VersionStatus.getLiveStatuses(), "Metal");
    List<ArtefactType> metalArtefactTypes = StreamSupport.stream(metalEntries.spliterator(), false).collect(Collectors.toList());
    assertEquals("Metal artefact types size", 2, metalArtefactTypes.size());

    Iterable<ArtefactType> XXXEntries = artefactTypeDao.findByStatusInAndNameStartsWith(VersionStatus.getLiveStatuses(), "XXX");
    List<ArtefactType> xxxArtefactTypes = StreamSupport.stream(XXXEntries.spliterator(), false).collect(Collectors.toList());
    assertEquals("XXX artefact types size", 0, xxxArtefactTypes.size());

  }

  private ArtefactType constructNewArtefactType(@NotBlank String name) {
    return ArtefactType.builder()
      .name(name)
      .createdBy("testuser")
      .status(VersionStatus.New)
      .build();
  }

}
