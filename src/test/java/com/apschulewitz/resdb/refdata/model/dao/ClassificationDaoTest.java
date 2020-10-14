package com.apschulewitz.resdb.refdata.model.dao;

import com.apschulewitz.resdb.refdata.ClassificationTestHelper;
import com.apschulewitz.resdb.refdata.model.entity.ClassificationCollection;
import com.apschulewitz.resdb.refdata.model.entity.ClassificationEntry;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.apschulewitz.resdb.refdata.ClassificationTestHelper.ADORNMENT_NAME;
import static com.apschulewitz.resdb.refdata.ClassificationTestHelper.ANKLET_NAME;
import static com.apschulewitz.resdb.refdata.ClassificationTestHelper.ARMLET_NAME;
import static com.apschulewitz.resdb.refdata.ClassificationTestHelper.JEWELLERY_NAME;
import static com.apschulewitz.resdb.refdata.ClassificationTestHelper.TATTOO_NAME;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@Slf4j
public class ClassificationDaoTest {

    @Autowired
    private ClassificationDao classificationDao;

    @Test
    public void given_valid_classification_when_save_is_executed_then_save_classification() {
        // first delete all entries
        classificationDao.deleteAll();

        ClassificationCollection unsavedCollection = ClassificationTestHelper.constructNewClassificationCollection("Artefact Classifications");

        // level 1

        ClassificationEntry adornmentEntry = ClassificationTestHelper.constructNewClassificationEntry(ADORNMENT_NAME, unsavedCollection);

        // level 2

        ClassificationEntry jewelleryEntry = ClassificationTestHelper.constructNewClassificationEntry(JEWELLERY_NAME, unsavedCollection);
        jewelleryEntry.setParentEntry(adornmentEntry);

        ClassificationEntry tattooEntry = ClassificationTestHelper.constructNewClassificationEntry(TATTOO_NAME, unsavedCollection);
        tattooEntry.setParentEntry(adornmentEntry);

        // level 3

        ClassificationEntry ankletEntry = ClassificationTestHelper.constructNewClassificationEntry(ANKLET_NAME, unsavedCollection);
        ankletEntry.setParentEntry(jewelleryEntry);

        ClassificationEntry armletEntry = ClassificationTestHelper.constructNewClassificationEntry(ARMLET_NAME, unsavedCollection);
        armletEntry.setParentEntry(jewelleryEntry);

        unsavedCollection.getEntries().add(adornmentEntry); // add level 1 to collection

        ClassificationCollection savedCollection = classificationDao.save(unsavedCollection);

        // then

        assertNotNull("Classification collection saved", savedCollection);
        assertNotNull("Classification collection id", savedCollection.getId());

        assertNotNull("Classification collection level1 entries", savedCollection.getEntries());
        assertEquals("Classification collection entries count", 1, savedCollection.getEntries().size());

        assertNotNull("Classification collection level2 entries", savedCollection.getEntries());
        assertEquals("Classification collection entries count", 1, savedCollection.getEntries().size());

        ClassificationEntry level1Entry = savedCollection.getEntries().get(0);
        assertNotNull(level1Entry);
        assertEquals(ADORNMENT_NAME, level1Entry.getName());
        assertEquals(2, level1Entry.getEntries().size());
        assertEquals(JEWELLERY_NAME, level1Entry.getEntries().get(0).getName());

        ClassificationEntry level2JewelleryEntry = level1Entry.getEntries().get(0);
        assertEquals(JEWELLERY_NAME, level2JewelleryEntry.getName());

        ClassificationEntry level2TattooEntry = level1Entry.getEntries().get(1);
        assertEquals(TATTOO_NAME, level2TattooEntry.getName());

        ClassificationEntry level3AnkletEntry = level2JewelleryEntry.getEntries().get(0);
        assertEquals(ANKLET_NAME, level3AnkletEntry.getName());

        ClassificationEntry level3ArmletEntry = level2JewelleryEntry.getEntries().get(1);
        assertEquals(ARMLET_NAME, level3ArmletEntry.getName());
    }

    //    @Test
    public void given_some_live_and_some_entries_when_findByStatusIn_is_executed_then_retrieve_active_entries() {
        // first delete all entries
//        addressTypeDao.deleteAll();
//
//        AddressType saved;
//
//        AddressType homePostal = constructNewClassificationEntry("Home postal address");
//        saved = addressTypeDao.save(homePostal);
//        assertNotNull("Home postal address id", saved.getId());
//
//        AddressType workPostalAddress = constructNewClassificationEntry("Work postal address");
//        saved = addressTypeDao.save(workPostalAddress);
//        assertNotNull("Work postal address id", saved.getId());
//        saved.setStatus(VersionStatus.Cancel);
//        saved = addressTypeDao.save(saved);
//        assertEquals("Cancelled address type name", workPostalAddress.getName(), saved.getName());
//        assertEquals("Cancelled address type status", VersionStatus.Cancel, saved.getStatus());
//
//        Iterable<AddressType> liveEntries = addressTypeDao.findByStatusIn(VersionStatus.getLiveStatuses());
//        List<AddressType> addressTypes = StreamSupport.stream(liveEntries.spliterator(), false).collect(Collectors.toList());
//        assertEquals("Active address types size", 1, addressTypes.size());
    }

    //    @Test
    public void given_multiple_active_address_types_when_findByStatusInAndNameStartsWithis_executed_then_retrieve_matching_active_entries() {
        // first delete all entries
//        addressTypeDao.deleteAll();
//
//        AddressType saved;
//
//        AddressType homePostal = constructNewClassificationEntry("Home postal address");
//        saved = addressTypeDao.save(homePostal);
//        assertNotNull("Home postal address id", saved.getId());
//
//        AddressType workPostalAddress = constructNewClassificationEntry("Work postal address");
//        saved = addressTypeDao.save(workPostalAddress);
//        assertNotNull("Work postal address id", saved.getId());
//
//        AddressType personalEmailAddress = constructNewClassificationEntry("Personal email address");
//        saved = addressTypeDao.save(personalEmailAddress);
//        assertNotNull("Personal email address id", saved.getId());
//
//        AddressType workEmailAddress = constructNewClassificationEntry("Work email address");
//        saved = addressTypeDao.save(workEmailAddress);
//        assertNotNull("Work email address id", saved.getId());
//
//        Iterable<AddressType> liveEntries = addressTypeDao.findByStatusIn(VersionStatus.getLiveStatuses());
//        List<AddressType> addressTypes = StreamSupport.stream(liveEntries.spliterator(), false).collect(Collectors.toList());
//        assertEquals("Active address types size", 4, addressTypes.size());
//
//        Iterable<AddressType> workEntries = addressTypeDao.findByStatusInAndNameStartsWith(VersionStatus.getLiveStatuses(), "Work");
//        List<AddressType> workAddressTypes = StreamSupport.stream(workEntries.spliterator(), false).collect(Collectors.toList());
//        assertEquals("Work address types size", 2, workAddressTypes.size());
//
//        Iterable<AddressType> XXXEntries = addressTypeDao.findByStatusInAndNameStartsWith(VersionStatus.getLiveStatuses(), "XXX");
//        List<AddressType> XXXAddressTypes = StreamSupport.stream(XXXEntries.spliterator(), false).collect(Collectors.toList());
//        assertEquals("XXX address types size", 0, XXXAddressTypes.size());

    }

}
