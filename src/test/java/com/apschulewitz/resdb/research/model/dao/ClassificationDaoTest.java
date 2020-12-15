package com.apschulewitz.resdb.research.model.dao;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.research.model.ClassificationTestHelper;
import com.apschulewitz.resdb.research.model.entity.ClassificationCollection;
import com.apschulewitz.resdb.research.model.entity.ClassificationEntry;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.apschulewitz.resdb.research.model.ClassificationTestHelper.ADORNMENT_NAME;
import static com.apschulewitz.resdb.research.model.ClassificationTestHelper.ANKLET_NAME;
import static com.apschulewitz.resdb.research.model.ClassificationTestHelper.ARMLET_NAME;
import static com.apschulewitz.resdb.research.model.ClassificationTestHelper.JEWELLERY_NAME;
import static com.apschulewitz.resdb.research.model.ClassificationTestHelper.TATTOO_NAME;
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

        ClassificationCollection unsavedCollection = constructCollection();

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

    @Test
    public void given_some_live_and_some_collections_when_findByStatusIn_is_executed_then_retrieve_active_collections() {
        // first delete all entries
      classificationDao.deleteAll();

      ClassificationCollection unsavedCollection = constructCollection();
      ClassificationCollection unsavedCollection2 = constructCollection();

      ClassificationCollection savedCollection = classificationDao.save(unsavedCollection);

      ClassificationCollection savedCollection2 = classificationDao.save(unsavedCollection2);

      assertNotNull(savedCollection);
      assertNotNull(savedCollection2);

      savedCollection2.setStatus(VersionStatus.Cancel);
      ClassificationCollection updatedCollection2 = classificationDao.save(savedCollection2);
      assertNotNull(updatedCollection2);

      List<ClassificationCollection> allCollections = classificationDao.findByStatusIn(VersionStatus.getLiveStatuses());

      assertNotNull(allCollections);
      assertEquals(1, allCollections.size());
    }

    private ClassificationCollection constructCollection() {
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

      return unsavedCollection;
    }
}
