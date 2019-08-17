package com.apschulewitz.resdb.common.model.converter;

import com.apschulewitz.resdb.common.model.entity.TitleType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


/**
 * Created by adrianschulewitz on 28/06/2015.
 */
@Converter(autoApply = true)
public class TitleTypePersistenceConverter implements AttributeConverter<TitleType, String> {

    @Override
    public String convertToDatabaseColumn(TitleType entityValue) {
        return entityValue == null ? null : entityValue.getCode();
    }

    @Override
    public TitleType convertToEntityAttribute(String databaseValue) {
        return databaseValue == null ? null : TitleType.getTypeFor(databaseValue);
    }
}
