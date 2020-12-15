package com.apschulewitz.resdb.refdata.model.dao;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.entity.AddressType;
import com.apschulewitz.resdb.refdata.model.entity.CalendarType;
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
public class CalendarTypeDaoTest {

  @Autowired
  private CalendarTypeDao calendarTypeDao;

  @Before
  public void beforeEachTest() {
    calendarTypeDao.deleteAll();
  }

  @Test
  public void given_valid_new_entity_when_save_is_executed_then_save_entity() {
    CalendarType calendarType1 = constructNewEntity("Calendar type1");
    CalendarType saved = calendarTypeDao.save(calendarType1);

    assertNotNull(saved);
    assertNotNull(saved.getId());
    assertEquals(calendarType1.getName(), saved.getName());
    assertEquals(calendarType1.getCreatedBy(), saved.getCreatedBy());
    assertEquals(calendarType1.getStatus(), saved.getStatus());
    assertEquals(calendarType1.getOperation(), saved.getOperation());
    assertNotNull(saved.getVersionNumber());
    assertNull(saved.getUpdatedBy());
    assertNull(saved.getLastUpdated());

    Iterable<CalendarType> liveEntries = calendarTypeDao.findByStatusIn(VersionStatus.getLiveStatuses());
    List<CalendarType> addressTypes = StreamSupport.stream(liveEntries.spliterator(), false).collect(Collectors.toList());
    assertEquals("Active calendar types size", 1, addressTypes.size());
  }

  @Test
  public void given_valid_new_entity_when_save_is_executed_multiple_times_then_version_no_is_updated() {
    CalendarType homePostal = constructNewEntity("Calendar type 1");
    CalendarType saved = calendarTypeDao.save(homePostal);
    saved.setName("Modified name");
    saved.setStatus(VersionStatus.Amend);
    saved = calendarTypeDao.save(saved);

    assertNotNull(saved);
    assertNotNull(saved.getId());
    assertNotNull(saved.getVersionNumber());
    assertNull(saved.getUpdatedBy());
    assertNull(saved.getLastUpdated());

    Iterable<CalendarType> liveEntries = calendarTypeDao.findByStatusIn(VersionStatus.getLiveStatuses());
    List<CalendarType> calendarTypes = StreamSupport.stream(liveEntries.spliterator(), false).collect(Collectors.toList());
    assertEquals(1, calendarTypes.size());
    assertTrue(calendarTypes.get(0).getVersionNumber() > 0L);
  }

  @Test
  public void given_some_live_and_some_entries_when_findByStatusIn_is_executed_then_retrieve_active_entries() {
    CalendarType calendarType1 = constructNewEntity("Calendar type1");
    CalendarType saved = calendarTypeDao.save(calendarType1);
    assertNotNull("Calendar type1 id", saved.getId());

    CalendarType calendarType2 = constructNewEntity("Calendar type2");
    saved = calendarTypeDao.save(calendarType2);
    assertNotNull("Calendar type2 id", saved.getId());
    saved.setStatus(VersionStatus.Cancel);
    saved = calendarTypeDao.save(saved);
    assertEquals("Cancelled Calendar type2 name", calendarType2.getName(), saved.getName());
    assertEquals("Cancelled Calendar type2 status", VersionStatus.Cancel, saved.getStatus());

    Iterable<CalendarType> liveEntries = calendarTypeDao.findByStatusIn(VersionStatus.getLiveStatuses());
    List<CalendarType> addressTypes = StreamSupport.stream(liveEntries.spliterator(), false).collect(Collectors.toList());
    assertEquals("Active calendar types size", 1, addressTypes.size());
  }

  @Test
  public void given_multiple_active_address_types_when_findByStatusInAndNameStartsWithis_executed_then_retrieve_matching_active_entries() {
    CalendarType calendarType1 = constructNewEntity("European Calendar type1");
    CalendarType saved = calendarTypeDao.save(calendarType1);
    assertNotNull("European Calendar type1 id", saved.getId());

    CalendarType calendarType2 = constructNewEntity("European Calendar type2");
    saved = calendarTypeDao.save(calendarType2);
    assertNotNull("European Calendar type2 id", saved.getId());

    CalendarType calendarType3 = constructNewEntity("Asian Calendar type3");
    saved = calendarTypeDao.save(calendarType3);
    assertNotNull("Asian Calendar type3 id", saved.getId());

    CalendarType calendarType4 = constructNewEntity("American Calendar type4");
    saved = calendarTypeDao.save(calendarType4);
    assertNotNull("American Calendar type4 id", saved.getId());

    Iterable<CalendarType> liveEntries = calendarTypeDao.findByStatusIn(VersionStatus.getLiveStatuses());
    List<CalendarType> addressTypes = StreamSupport.stream(liveEntries.spliterator(), false).collect(Collectors.toList());
    assertEquals("Active calendar types size", 4, addressTypes.size());

    Iterable<CalendarType> euroEntries = calendarTypeDao.findByStatusInAndNameStartsWith(VersionStatus.getLiveStatuses(), "Euro");
    List<CalendarType> workAddressTypes = StreamSupport.stream(euroEntries.spliterator(), false).collect(Collectors.toList());
    assertEquals("Euro calendar types size", 2, workAddressTypes.size());

    Iterable<CalendarType> XXXEntries = calendarTypeDao.findByStatusInAndNameStartsWith(VersionStatus.getLiveStatuses(), "XXX");
    List<CalendarType> XXXAddressTypes = StreamSupport.stream(XXXEntries.spliterator(), false).collect(Collectors.toList());
    assertEquals("XXX calendar types size", 0, XXXAddressTypes.size());

  }

  private CalendarType constructNewEntity(@NotBlank String name) {
    return CalendarType.builder()
      .name(name)
      .createdBy("system")
      .status(VersionStatus.New)
      .build();
  }

}
