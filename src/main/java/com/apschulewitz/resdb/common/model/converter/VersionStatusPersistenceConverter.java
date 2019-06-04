package com.apschulewitz.resdb.common.model.converter;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.OffsetDateTime;
import java.util.Objects;

//import javax.persistence.Converter;

/**
 * Created by adrianschulewitz on 22/10/2016.
 */
@Converter(autoApply = true)
public class VersionStatusPersistenceConverter implements AttributeConverter<VersionStatus, String> {

    @Override
    /**
     * @return a value as a String such as 2014-12-03T10:15:30+01:00
     * @see OffsetDateTime#toString()
     */
    public String convertToDatabaseColumn(VersionStatus entityValue) {
       return entityValue == null ? null : Objects.toString(entityValue.getCode(), null);
    }

    @Override
    public VersionStatus convertToEntityAttribute(String databaseValue) {
        return databaseValue == null ? null : VersionStatus.getInstance(databaseValue);
    }
}
