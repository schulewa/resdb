package com.apschulewitz.resdb.common.model.converter;

import com.apschulewitz.resdb.common.model.entity.YesNoChoice;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


/**
 * Created by adrianschulewitz on 28/06/2015.
 */
@Converter(autoApply = true)
public class YesNoChoicePersistenceConverter implements AttributeConverter<YesNoChoice, String> {

    @Override
    public String convertToDatabaseColumn(YesNoChoice entityValue) {
        return entityValue == null ? null : entityValue.getCode();
    }

    @Override
    public YesNoChoice convertToEntityAttribute(String databaseValue) {
        return databaseValue == null ? null : YesNoChoice.getChoiceFor(databaseValue);
    }
}
