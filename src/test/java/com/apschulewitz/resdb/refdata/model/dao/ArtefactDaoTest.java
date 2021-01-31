package com.apschulewitz.resdb.refdata.model.dao;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.entity.Artefact;
import com.apschulewitz.resdb.refdata.model.entity.ArtefactType;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@Slf4j
@Ignore
public class ArtefactDaoTest {

  @Autowired
  private ArtefactDao dao;

  @Autowired
  private ArtefactTypeDao artefactTypeDao;

  private ArtefactType artefactType;

  private Lock lock = new ReentrantLock();

  @Before
  public void beforeEachTest() {
    dao.deleteAll();

    if (artefactType == null) {
      artefactTypeDao.deleteAll();
      lock.lock();
      try {
        artefactType = constructNewArtefactType();
        artefactType = artefactTypeDao.save(artefactType);
      } finally {
        lock.unlock();
      }

    }
  }

  @Test
  public void given_valid_new_entity_when_save_is_executed_then_save_entity() {
    Artefact artefact = constructNewMinimalArtefact("Test artefact");
    Artefact saved = dao.save(artefact);

    assertNotNull(saved);
    assertNotNull(saved.getId());
    assertNotNull(saved.getVersionNumber());
    assertNull(saved.getUpdatedBy());
    assertNull(saved.getLastUpdated());

    Iterable<Artefact> liveEntries = dao.findByStatusIn(VersionStatus.getLiveStatuses());
    List<Artefact> artefacts = StreamSupport.stream(liveEntries.spliterator(), false).collect(Collectors.toList());
    assertEquals("Active address types size", 1, artefacts.size());
  }

  @Test
  public void given_valid_new_entity_when_save_is_executed_multiple_times_then_version_no_is_updated() {
    Artefact artefact = constructNewMinimalArtefact("Test artefact2");
    Artefact saved = dao.save(artefact);
    saved.setName("Modified name");
    saved.setStatus(VersionStatus.Amend);
    saved = dao.save(saved);

    assertNotNull(saved);
    assertNotNull(saved.getId());
    assertNotNull(saved.getVersionNumber());
    assertNull(saved.getUpdatedBy());
    assertNull(saved.getLastUpdated());

    Iterable<Artefact> allEntries = dao.findAll();

    Iterable<Artefact> liveEntries = dao.findByStatusIn(VersionStatus.getLiveStatuses());
    List<Artefact> artefacts = StreamSupport.stream(liveEntries.spliterator(), false).collect(Collectors.toList());
    assertEquals(1, artefacts.size());
    assertTrue(artefacts.get(0).getVersionNumber() > 0L);
  }

  @Test
  public void given_some_live_and_some_entries_when_findByStatusIn_is_executed_then_retrieve_active_entries() {
    Artefact artefact = constructNewMinimalArtefact("Another artefact");
    Artefact saved = dao.save(artefact);
    assertNotNull(saved.getId());

    Artefact artefact2 = constructNewMinimalArtefact("Artefact2");
    saved = dao.save(artefact2);
    assertNotNull(saved.getId());
    saved.setStatus(VersionStatus.Cancel);
    saved = dao.save(saved);
    assertEquals("Cancelled artefact", artefact2.getName(), saved.getName());
    assertEquals("Cancelled address type status", VersionStatus.Cancel, saved.getStatus());

    Iterable<Artefact> allEntries = dao.findAll();

    Iterable<Artefact> liveEntries = dao.findByStatusIn(VersionStatus.getLiveStatuses());
    List<Artefact> artefacts = StreamSupport.stream(liveEntries.spliterator(), false).collect(Collectors.toList());
    assertEquals("Active address types size", 1, artefacts.size());
  }

  @Test
  public void given_multiple_active_address_types_when_findByStatusInAndNameStartsWithis_executed_then_retrieve_matching_active_entries() {
    Artefact artefact1 = constructNewMinimalArtefact("Moche Artefact1");
    Artefact saved = dao.save(artefact1);
    assertNotNull("Saved Moche Artefact1", saved.getId());

    Artefact artefact2 = constructNewMinimalArtefact("Wari Artefact2");
    saved = dao.save(artefact2);
    assertNotNull("Saved Wari Artefact2 ID", saved.getId());

    Artefact artefact3 = constructNewMinimalArtefact("Wari Artefact3");
    saved = dao.save(artefact3);
    assertNotNull("Saved Wari Artefact3 ID", saved.getId());

    Artefact artefact4 = constructNewMinimalArtefact("Olmec Artefact4");
    saved = dao.save(artefact4);
    assertNotNull("Saved Olmec Artefact4 ID", saved.getId());

    Iterable<Artefact> allEntries = dao.findAll();

    Iterable<Artefact> liveEntries = dao.findByStatusIn(VersionStatus.getLiveStatuses());
    List<Artefact> addressTypes = StreamSupport.stream(liveEntries.spliterator(), false).collect(Collectors.toList());
    assertEquals("Active artefacts size", 4, addressTypes.size());

    Iterable<Artefact> mocheEntries = dao.findByStatusInAndNameStartsWith(VersionStatus.getLiveStatuses(), "Moche");
    List<Artefact> mocheArtefacts = StreamSupport.stream(mocheEntries.spliterator(), false).collect(Collectors.toList());
    assertEquals("Moche artefacts size", 1, mocheArtefacts.size());

    Iterable<Artefact> wariEntries = dao.findByStatusInAndNameStartsWith(VersionStatus.getLiveStatuses(), "Wari");
    List<Artefact> wariArtefacts = StreamSupport.stream(wariEntries.spliterator(), false).collect(Collectors.toList());
    assertEquals("Wari artefacts size", 2, wariArtefacts.size());

  }

  private Artefact constructNewMinimalArtefact(@NotBlank String name) {
    return Artefact.builder()
      .artefactType(artefactType)
      .createdBy("testuser")
      .name(name)
      .status(VersionStatus.New)
      .build();
  }

  private ArtefactType constructNewArtefactType() {
    return ArtefactType.builder()
      .createdBy("testuser")
      .name("Test artefact type")
      .status(VersionStatus.New)
      .build();
  }
}
