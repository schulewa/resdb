package com.apschulewitz.resdb.refdata.model.converter;

import javax.persistence.AttributeConverter;
import java.time.Period;
import java.util.Objects;

/**
 * Created by adrianschulewitz on 28/06/2015.
 */
public class PeriodPersistenceConverter implements AttributeConverter<Period, String> {

    @Override
    public String convertToDatabaseColumn(Period entityValue) {
        return entityValue == null ? null : Objects.toString(entityValue, null);
    }

    @Override
    public Period convertToEntityAttribute(String databaseValue) {
        return databaseValue == null ? null : Period.parse(databaseValue);
    }
}
