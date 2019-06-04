package com.apschulewitz.resdb.common.model.entity;

import com.apschulewitz.resdb.common.model.DataEntityException;

public abstract class AbstractTableColumn implements ColumnDataType
{
    DataType type = null;
    private String columnName;
    private int length;
    private int decimalPlaces;
    private boolean allowNull;


	public DataType getType()
	{
		return type;
	}

	public void setType(DataType type) throws DataEntityException
	{
		if (!DataType.isValidDataType(type))
			throw new DataEntityException(DataEntityException.TYPE.INVALIDDATATYPE, "Invalid data type supplied: " + type);

		this.type = type;
	}

	public String getColumnName()
	{
		return columnName;
	}

	public void setColumnName(String columnName)
	{
		this.columnName = columnName;
	}

	public int getLength()
	{
		return length;
	}

	public void setLength(int length)
	{
		this.length = length;
	}

	public int getDecimalPlaces()
	{
		return decimalPlaces;
	}

	public void setDecimalPlaces(int decimalPlaces)
	{
		this.decimalPlaces = decimalPlaces;
	}

	public boolean isAllowNull()
	{
		return allowNull;
	}

	public void setAllowNull(boolean allowNull)
	{
		this.allowNull = allowNull;
	}

	public abstract Object getValue();

	public abstract void setValue(Object value) throws DataEntityException;


}
