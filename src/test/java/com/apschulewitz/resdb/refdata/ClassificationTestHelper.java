package com.apschulewitz.resdb.refdata;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.entity.ClassificationCollection;
import com.apschulewitz.resdb.refdata.model.entity.ClassificationEntry;
import org.opengis.metadata.constraint.Classification;

import javax.validation.constraints.NotBlank;

public class ClassificationTestHelper {


    public static final String ADORNMENT_NAME = "Adornment"; // level 1

    public static final String JEWELLERY_NAME = "Jewellery"; // level 2
    public static final String TATTOO_NAME = "Tattoo";

    public static final String ANKLET_NAME = "Anklet"; // level 3
    public static final String ARMLET_NAME = "Armlet";

    public static ClassificationCollection constructNewClassificationCollection(@NotBlank String name) {
        ClassificationCollection collection = new ClassificationCollection();
        collection.setName(name);
        collection.setCreatedBy("system");
        collection.setStatus(VersionStatus.New);
        return collection;
    }

    public static ClassificationEntry constructNewClassificationEntry(@NotBlank String name, ClassificationCollection collection) {
        ClassificationEntry entry = new ClassificationEntry();
        entry.setName(name);
        entry.setOwningCollection(collection);
        entry.setCreatedBy("system");
        entry.setStatus(VersionStatus.New);
        return entry;
    }

}
