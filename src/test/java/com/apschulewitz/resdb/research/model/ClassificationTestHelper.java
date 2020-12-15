package com.apschulewitz.resdb.research.model;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.research.model.entity.ClassificationCollection;
import com.apschulewitz.resdb.research.model.entity.ClassificationEntry;
import com.apschulewitz.resdb.research.model.dto.ClassificationCollectionDto;
import com.apschulewitz.resdb.research.model.dto.ClassificationEntryDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class ClassificationTestHelper extends AbstractTestHelper {

    public static final String ADORNMENT_NAME = "Adornment"; // level 1

    public static final String JEWELLERY_NAME = "Jewellery"; // level 2
    public static final String TATTOO_NAME = "Tattoo";

    public static final String ANKLET_NAME = "Anklet"; // level 3
    public static final String ARMLET_NAME = "Armlet";

    public static ClassificationCollection constructNewClassificationCollection(@NotBlank String name) {
        ClassificationCollection collection = new ClassificationCollection();
        collection.setName(name);
        collection.setCreatedBy(USER_NAME);
        collection.setStatus(VersionStatus.New);
        return collection;
    }

    public static ClassificationEntry constructNewClassificationEntry(@NotBlank String name, ClassificationCollection collection) {
        ClassificationEntry entry = new ClassificationEntry();
        entry.setName(name);
        entry.setOwningCollection(collection);
        entry.setCreatedBy(USER_NAME);
        entry.setStatus(VersionStatus.New);
        return entry;
    }

  public static ClassificationCollectionDto constructNewClassificationCollectionDto(Long id,
                                                                                    @NotBlank String name,
                                                                                    List<ClassificationEntryDto> entries) {
    ClassificationCollectionDto dto = new ClassificationCollectionDto();
    dto.setId(id);
    dto.setName(name);
    dto.setEntries(entries);
    dto.setStatus(VersionStatus.New);
    dto.setCreatedBy(USER_NAME);
    return dto;
  }

  public static ClassificationEntryDto constructNewClassificationEntryDto(Long id,
                                                                          @NotBlank String name,
                                                                          @NotNull ClassificationCollectionDto owningCollection,
                                                                          ClassificationEntryDto parentEntry,
                                                                          @NotNull List<ClassificationEntryDto> entries) {
    ClassificationEntryDto dto = new ClassificationEntryDto();
      dto.setId(id);
      dto.setName(name);
      dto.setOwningCollection(owningCollection);
      dto.setParentEntry(parentEntry);
      dto.setEntries(entries);
      return dto;
  }

  public static ClassificationCollectionDto constructNewArtefactClassificationCollectionDto() {
    ClassificationCollectionDto newClassificationCollection =
      ClassificationTestHelper.constructNewClassificationCollectionDto(1L, "Artefact Classifications", new ArrayList<>());

    List<ClassificationEntryDto> entries = new ArrayList<>();

    // level 1

    ClassificationEntryDto adornmentEntry =
      ClassificationTestHelper.constructNewClassificationEntryDto(1L, ADORNMENT_NAME, newClassificationCollection, null, entries);

    // level 2

    ClassificationEntryDto jewelleryEntry = ClassificationTestHelper.constructNewClassificationEntryDto(2L, JEWELLERY_NAME, newClassificationCollection, adornmentEntry, new ArrayList<>());

    ClassificationEntryDto tattooEntry = ClassificationTestHelper.constructNewClassificationEntryDto(3L, TATTOO_NAME, newClassificationCollection, adornmentEntry, new ArrayList<>());

    // level 3

    ClassificationEntryDto ankletEntry = ClassificationTestHelper.constructNewClassificationEntryDto(4L, ANKLET_NAME, newClassificationCollection, jewelleryEntry, new ArrayList<>());

    ClassificationEntryDto armletEntry = ClassificationTestHelper.constructNewClassificationEntryDto(5L, ARMLET_NAME, newClassificationCollection, jewelleryEntry, new ArrayList<>());

    adornmentEntry.addEntry(jewelleryEntry.getName());
    adornmentEntry.addEntry(tattooEntry.getName());

    jewelleryEntry.addEntry(ankletEntry.getName());
    jewelleryEntry.addEntry(armletEntry.getName());

    newClassificationCollection.getEntries().add(adornmentEntry); // add level 1 to collection
    return newClassificationCollection;
  }
}
