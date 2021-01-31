package com.apschulewitz.resdb.common.model.converter;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by adrianschulewitz on 22/04/2017.
 */
//@Component
public class VersionStatusConverter implements Converter<String, VersionStatus> {

    @Override
    public VersionStatus convert(String code) {
        return VersionStatus.getInstance(code);
    }
}
