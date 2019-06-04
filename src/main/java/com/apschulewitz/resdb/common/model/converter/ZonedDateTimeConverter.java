package com.apschulewitz.resdb.common.model.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Created by adrianschulewitz on 19/07/2015.
 */
@Converter(autoApply = true)
public class ZonedDateTimeConverter implements AttributeConverter<ZonedDateTime, Timestamp>  {

    @Override
    public java.sql.Timestamp convertToDatabaseColumn(ZonedDateTime entityValue) {
        return entityValue == null ? null : Timestamp.valueOf(entityValue.toLocalDateTime());
    }

    @Override
    public ZonedDateTime convertToEntityAttribute(Timestamp timestamp) {
        return timestamp == null ? null : ZonedDateTime.of(timestamp.toLocalDateTime(), ZoneId.systemDefault());
    }
}
