package com.apschulewitz.resdb.common.model.entity;

import com.apschulewitz.resdb.common.model.DataEntityException;

import javax.validation.constraints.NotNull;
import java.util.GregorianCalendar;

public class DateColumn extends AbstractTableColumn
{
    private GregorianCalendar value;

    /** Creates a new instance of DateColumn */
    public DateColumn()
    throws DataEntityException
    {
        setType(DataType.DATE);
    }

    /**
     * Creates an instance of DateColumn.
     * @param name is the name for the column
     */
    public DateColumn(String name)
    throws DataEntityException
    {
        setType(DataType.DATE);
        setColumnName(name);
    }

    /**
     * <code>setColumnLength</code> is irrelevant for DateColumn.
     */
    public void setColumnLength(int length)
    {
      throw new UnsupportedOperationException("Not allowed to set length on DateColumn");
    }

    /**
     * <code>setValue</code> sets the value of DateColumn.
     * @param value sets the value of the column
     * @see GregorianCalendar
     */
    public void setValue(@NotNull GregorianCalendar value)
    {
        this.value = value;
        nullifyTimeElements();
    }

    /**
     * <code>vsetValue</code> sets the value of DateTimeColumn.
     * @param year sets the year part of the value
     * @param month sets the month part of the value
     * @param day sets the day of the month for the value
     */
    public void setValue(int year, int month, int day)
    {
        if (value == null)
            value = new GregorianCalendar(year, month, day);
        else
        {
            value.set(GregorianCalendar.YEAR, year);
            value.set(GregorianCalendar.MONTH, month);
            value.set(GregorianCalendar.DAY_OF_MONTH, day);
        }
        nullifyTimeElements();
    }

    /**
     * <code>nullifyTimeElements</code> sets the hour and minute elements to zero
     */
    private void nullifyTimeElements()
    {
        value.set(GregorianCalendar.HOUR_OF_DAY, 0);
        value.set(GregorianCalendar.MINUTE, 0);
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
	public void setValue(@NotNull Object value) throws DataEntityException
	{
		if (!(value instanceof GregorianCalendar))
			throw new DataEntityException(DataEntityException.TYPE.INVALIDDATATYPE);

		this.value = (GregorianCalendar)value;
	}
}
