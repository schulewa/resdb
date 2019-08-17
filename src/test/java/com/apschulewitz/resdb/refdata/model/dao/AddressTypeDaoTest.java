package com.apschulewitz.resdb.refdata.model.dao;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.entity.AddressType;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@Slf4j
public class AddressTypeDaoTest {

  @Autowired
  private TestEntityManager testEntityManager;

  @Autowired
  private AddressTypeDao addressTypeDao;

  @Test
  public void given_valid_addresstype_entity_when_save_is_executed_then_save_entity() {
    // first delete all entries
    addressTypeDao.deleteAll();

    AddressType homePostal = constructNewAddressType("Home postal address");
    AddressType saved = addressTypeDao.save(homePostal);

    assertNotNull("Address type saved", saved);
    assertNotNull("Address type id", saved.getId());
    assertEquals("Address type name", homePostal.getName(), saved.getName());
    assertEquals("Address type createdBy", homePostal.getCreatedBy(), saved.getCreatedBy());
    assertEquals("Address type updatedBy", homePostal.getUpdatedBy(), saved.getUpdatedBy());
    assertEquals("Address type lastUpdated", homePostal.getLastUpdated(), saved.getLastUpdated());
    assertEquals("Address type status", homePostal.getStatus(), saved.getStatus());
    assertEquals("Address type ", homePostal.getOperation(), saved.getOperation());

    Iterable<AddressType> liveEntries = addressTypeDao.findByStatusIn(VersionStatus.getLiveStatuses());
    List<AddressType> addressTypes = StreamSupport.stream(liveEntries.spliterator(), false).collect(Collectors.toList());
    assertEquals("Active address types size", 1, addressTypes.size());
  }

  @Test
  public void given_some_live_and_some_entries_when_findByStatusIn_is_executed_then_retrieve_active_entries() {
    // first delete all entries
    addressTypeDao.deleteAll();

    AddressType saved;

    AddressType homePostal = constructNewAddressType("Home postal address");
    saved = addressTypeDao.save(homePostal);
    assertNotNull("Home postal address id", saved.getId());

    AddressType workPostalAddress = constructNewAddressType("Work postal address");
    saved = addressTypeDao.save(workPostalAddress);
    assertNotNull("Work postal address id", saved.getId());
    saved.setStatus(VersionStatus.Cancel);
    saved = addressTypeDao.save(saved);
    assertEquals("Cancelled address type name", workPostalAddress.getName(), saved.getName());
    assertEquals("Cancelled address type status", VersionStatus.Cancel, saved.getStatus());

    Iterable<AddressType> liveEntries = addressTypeDao.findByStatusIn(VersionStatus.getLiveStatuses());
    List<AddressType> addressTypes = StreamSupport.stream(liveEntries.spliterator(), false).collect(Collectors.toList());
    assertEquals("Active address types size", 1, addressTypes.size());
  }

  @Test
  public void given_multiple_active_address_types_when_findByStatusInAndNameStartsWithis_executed_then_retrieve_matching_active_entries() {
    // first delete all entries
    addressTypeDao.deleteAll();

    AddressType saved;

    AddressType homePostal = constructNewAddressType("Home postal address");
    saved = addressTypeDao.save(homePostal);
    assertNotNull("Home postal address id", saved.getId());

    AddressType workPostalAddress = constructNewAddressType("Work postal address");
    saved = addressTypeDao.save(workPostalAddress);
    assertNotNull("Work postal address id", saved.getId());

    AddressType personalEmailAddress = constructNewAddressType("Personal email address");
    saved = addressTypeDao.save(personalEmailAddress);
    assertNotNull("Personal email address id", saved.getId());

    AddressType workEmailAddress = constructNewAddressType("Work email address");
    saved = addressTypeDao.save(workEmailAddress);
    assertNotNull("Work email address id", saved.getId());

    Iterable<AddressType> liveEntries = addressTypeDao.findByStatusIn(VersionStatus.getLiveStatuses());
    List<AddressType> addressTypes = StreamSupport.stream(liveEntries.spliterator(), false).collect(Collectors.toList());
    assertEquals("Active address types size", 4, addressTypes.size());

    Iterable<AddressType> workEntries = addressTypeDao.findByStatusInAndNameStartsWith(VersionStatus.getLiveStatuses(), "Work");
    List<AddressType> workAddressTypes = StreamSupport.stream(workEntries.spliterator(), false).collect(Collectors.toList());
    assertEquals("Work address types size", 2, workAddressTypes.size());

    Iterable<AddressType> XXXEntries = addressTypeDao.findByStatusInAndNameStartsWith(VersionStatus.getLiveStatuses(), "XXX");
    List<AddressType> XXXAddressTypes = StreamSupport.stream(XXXEntries.spliterator(), false).collect(Collectors.toList());
    assertEquals("XXX address types size", 0, XXXAddressTypes.size());

  }

  private AddressType constructNewAddressType(@NotBlank String name) {
    return AddressType.builder()
      .name(name)
      .createdBy("system")
      .status(VersionStatus.New)
      .updatedBy("system")
      .build();
  }

}
