package com.apschulewitz.resdb.security.model.converter;

import com.apschulewitz.resdb.refdata.model.entity.AccountStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * Created by adrianschulewitz on 22/10/2016.
 */
@Converter(autoApply = true)
public class AccountStatusPersistenceConverter implements AttributeConverter<AccountStatus, String> {

    @Override
    /**
     * @return a value as a String such as 2014-12-03T10:15:30+01:00
     * @see OffsetDateTime#toString()
     */
    public String convertToDatabaseColumn(AccountStatus entityValue) {
       return entityValue == null ? null : Objects.toString(entityValue.getCode(), null);
    }

    @Override
    public AccountStatus convertToEntityAttribute(String databaseValue) {
        return databaseValue == null ? null : AccountStatus.getStatusFor(databaseValue);
    }
}
