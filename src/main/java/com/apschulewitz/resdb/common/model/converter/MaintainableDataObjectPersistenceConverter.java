package com.apschulewitz.resdb.common.model.converter;

import com.apschulewitz.resdb.common.model.entity.MaintainableDataObject;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * Created by adrianschulewitz on 22/10/2016.
 */
@Converter(autoApply = true)
public class MaintainableDataObjectPersistenceConverter implements AttributeConverter<MaintainableDataObject, String> {

    @Override
    /**
     * @return a value as a String such as 2014-12-03T10:15:30+01:00
     * @see OffsetDateTime#toString()
     */
    public String convertToDatabaseColumn(MaintainableDataObject entityValue) {
       return entityValue == null ? null : Objects.toString(entityValue.getDataObjectIdentifier(), null);
    }

    @Override
    public MaintainableDataObject convertToEntityAttribute(String databaseValue) {
        return databaseValue == null ? null : MaintainableDataObject.getFor(databaseValue);
    }
}
