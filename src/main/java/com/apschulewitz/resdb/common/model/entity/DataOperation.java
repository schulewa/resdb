package com.apschulewitz.resdb.common.model.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by adrianschulewitz on 15/08/2016.
 */
public enum DataOperation {

    DELETE("D"), CREATE("C"),READ("R"),UPDATE("U"),ALL("CRUD");

    private String code;

    private DataOperation(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static DataOperation getOperationFor(String code) {

        switch (code) {

            case "D":
                return DELETE;
            case "I":
                return CREATE;
            case "Q":
                return READ;
            case "R":
                return UPDATE;
            default:
                return null;
        }
    }

    public static final Set<DataOperation> VALID_DATA_OPERATIONS = new HashSet<>();

    static {
        VALID_DATA_OPERATIONS.add(DataOperation.ALL);
        VALID_DATA_OPERATIONS.add(DataOperation.DELETE);
        VALID_DATA_OPERATIONS.add(DataOperation.CREATE);
        VALID_DATA_OPERATIONS.add(DataOperation.READ);
        VALID_DATA_OPERATIONS.add(DataOperation.UPDATE);
    }
}
