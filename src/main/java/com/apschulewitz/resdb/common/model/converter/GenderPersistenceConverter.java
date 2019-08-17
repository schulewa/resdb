package com.apschulewitz.resdb.common.model.converter;

import com.apschulewitz.resdb.common.model.entity.Gender;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


/**
 * Created by adrianschulewitz on 28/06/2015.
 */
@Converter(autoApply = true)
public class GenderPersistenceConverter implements AttributeConverter<Gender, String> {

    @Override
    public String convertToDatabaseColumn(Gender entityValue) {
        return entityValue == null ? null : entityValue.getCode();
    }

    @Override
    public Gender convertToEntityAttribute(String databaseValue) {
        return databaseValue == null ? null : Gender.getGenderFor(databaseValue);
    }
}
