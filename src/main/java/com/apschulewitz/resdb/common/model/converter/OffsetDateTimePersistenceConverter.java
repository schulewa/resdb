package com.apschulewitz.resdb.common.model.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * Created by adrianschulewitz on 28/06/2015.
 */
@Converter(autoApply = true)
public class OffsetDateTimePersistenceConverter implements AttributeConverter<OffsetDateTime, String> {

    @Override
    /**
     * @return a value as a String such as 2014-12-03T10:15:30+01:00
     * @see OffsetDateTime#toString()
     */
    public String convertToDatabaseColumn(OffsetDateTime entityValue) {
       return entityValue == null ? null : Objects.toString(entityValue, null);
    }

    @Override
    public OffsetDateTime convertToEntityAttribute(String databaseValue) {
        return databaseValue == null ? null : OffsetDateTime.parse(databaseValue);
    }
}
