package com.apschulewitz.resdb.common.model.entity;

import javax.validation.constraints.NotNull;

public enum DataType
{
	BLOB, CLOB, DATE, DATETIME, DOUBLE, FLOAT, INTEGER, LONG, SHORT, STRING, TIME, TIMESTAMP;

    public static boolean isValidDataType(@NotNull DataType type)
    {
    	boolean valid = false;

    	switch(type)
    	{
    	case BLOB:
    			;
    	case CLOB:
    			;
    	case DATE:
    			;
    	case DATETIME:
    			;
    	case DOUBLE:
    			;
    	case FLOAT:
    			;
    	case INTEGER:
    			;
    	case LONG:
    			;
    	case SHORT:
    			;
    	case STRING:
    			;
    	case TIME:
    			;
    	case TIMESTAMP:
    			valid = true;

    	}

    	return valid;
    }
}
