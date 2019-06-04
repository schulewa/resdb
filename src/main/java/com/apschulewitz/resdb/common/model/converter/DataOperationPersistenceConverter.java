package com.apschulewitz.resdb.common.model.converter;

import com.apschulewitz.resdb.common.model.entity.DataOperation;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * Created by adrianschulewitz on 22/10/2016.
 */
@Converter(autoApply = true)
public class DataOperationPersistenceConverter implements AttributeConverter<DataOperation, String> {

    @Override
    /**
     * @return a value as a String such as 2014-12-03T10:15:30+01:00
     * @see OffsetDateTime#toString()
     */
    public String convertToDatabaseColumn(DataOperation entityValue) {
       return entityValue == null ? null : Objects.toString(entityValue.getCode(), null);
    }

    @Override
    public DataOperation convertToEntityAttribute(String databaseValue) {
        return databaseValue == null ? null : DataOperation.getOperationFor(databaseValue);
    }
}
