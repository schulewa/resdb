package com.apschulewitz.resdb.common.model.entity;


import com.apschulewitz.resdb.common.ResearchDatabaseModelException;
import com.apschulewitz.resdb.common.utils.ObjectUtils;
import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * <CODE>DataEntityPrimaryKey</CODE> is used to represent a data entity primary key
 * and is used when updating an entity;
 */
public class DataEntityPrimaryKey {

    private ImmutableMap<String, Object> key;

    public DataEntityPrimaryKey(Map<String, Object> originalKey) {
        if (null == originalKey)
            throw new ResearchDatabaseModelException("Null original key provided for data entity");

        ImmutableMap.Builder<String, Object> builder = ImmutableMap.builder();

        for (String origKeyEntry : originalKey.keySet()) {
            Object value = ObjectUtils.deepClone(originalKey.get(origKeyEntry));
            builder.put(origKeyEntry, value);
        }

        key = builder.build();
    }

    public ImmutableMap<String, Object> getKey() {
        return key;
    }
}
