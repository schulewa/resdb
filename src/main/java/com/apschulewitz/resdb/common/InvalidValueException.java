/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apschulewitz.resdb.common;


/**
 *
 * @author adrian
 */
public class InvalidValueException extends ResearchDatabaseModelException
{
	private static final long serialVersionUID = -8912874838788584068L;

	private Object invalidValue;

    public InvalidValueException()
    {
        super();
    }

    public InvalidValueException(Object value)
    {
        super();
        invalidValue = value;
    }

    public InvalidValueException(Throwable cause)
    {
        super(cause);
    }

    public InvalidValueException(Throwable cause, Object value)
    {
        this(cause);
        invalidValue = value;
    }

    public InvalidValueException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public InvalidValueException(String message, Throwable cause, Object value)
    {
        this(message, cause);
        invalidValue = value;
    }

    public InvalidValueException(String message)
    {
        super(message);
    }

    public InvalidValueException(String message, Object value)
    {
        this(message);
        invalidValue = value;
    }

    public Object getInvalidValue()
    {
        return invalidValue;
    }
}
