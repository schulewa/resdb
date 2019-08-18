package com.apschulewitz.resdb.common.model.entity;

import com.apschulewitz.resdb.common.model.DataEntityException;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class StringColumn extends AbstractTableColumn
{
	String value;

    /** Creates a new instance of StringColumn */
    public StringColumn()
    {
    	setType(DataType.STRING);
    }

    /**
     * Creates a new instance of StringColumn.
     * @param name is the name of the column
     */
    public StringColumn(String name)
    {
        setType(DataType.STRING);
        setColumnName(name);
    }

    /**
     * Creates a new instance of StringColumn.
     * @param name is the name of the column
     * @param length is the name of the column
     */
    public StringColumn(@NotBlank String name, int length)
    throws DataEntityException
    {
        setType(DataType.STRING);
        setColumnName(name);
        setLength(length);
    }

	@Override
	public boolean isNull()
	{
		return (value == null);
	}

	@Override
	public void setNull()
	{
		value = null;
	}

	@Override
	public Object getValue()
	{
		return value;
	}

	@Override
	public void setValue(@NotNull Object value)
	{
		if (!(value instanceof String))
			throw new DataEntityException(DataEntityException.TYPE.INVALIDDATATYPE);

		this.value = (String)value;
	}

}
