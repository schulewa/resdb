package com.apschulewitz.resdb.refdata.model.converter;

import com.apschulewitz.resdb.refdata.model.entity.MathOperator;

import javax.persistence.AttributeConverter;
import java.time.Period;
import java.util.Objects;

/**
 * Created by adrianschulewitz on 28/06/2015.
 */
public class MathOperatorPersistenceConverter implements AttributeConverter<MathOperator, String> {

    @Override
    public String convertToDatabaseColumn(MathOperator entityValue) {
        return entityValue == null ? null : Objects.toString(entityValue, null);
    }

    @Override
    public MathOperator convertToEntityAttribute(String databaseValue) {
        return databaseValue == null ? null : MathOperator.valueOf(databaseValue);
    }
}
