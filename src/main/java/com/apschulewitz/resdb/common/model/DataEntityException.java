package com.apschulewitz.resdb.common.model;

public class DataEntityException extends RuntimeException
{

	private static final long serialVersionUID = 7792982884154520149L;

	private TYPE exceptionCode;

    public enum TYPE {	INVALIDDATATYPE,
    					INVALIDCOLUMNLENGTH,
    					INVALIDTABLENAME,
    					NULLTABLECOLUMN,
    					DUPLICATECOLUMNNAME,
    					NULLCOLUMNNAME,
    					INVALIDPRECISION
    				};

    /**
     * Creates a new instance of <code>DataentityException</code> with
     * no arguments.
     */
    public DataEntityException()
    {
        super();
    }

    /**
     * Creates a new instance of <code>DataEntityException</code> with
     * just an exception code.
     * @param code the exception code.
     */
    public DataEntityException(TYPE code)
    {
        super();
        exceptionCode = code;
    }

    /**
     * Constructs an instance of <code>DataEntityException</code> with
     * the specified detail message.
     * @param msg the detail message.
     */
    public DataEntityException(String msg)
    {
        super(msg);
    }

    /**
     * Constructs an instance of <code>DataEntityException</code> with
     * the specified exception code and message.
     * @param code the exception code
     * @param msg the detail message
     */
    public DataEntityException(TYPE code, String msg)
    {
        super(msg);
        exceptionCode = code;
    }

    /**
     * Returns the exception code associated with the <code>DataEntityException</code>
     */
    public TYPE getExceptionCode()
    {
        return exceptionCode;
    }

    /**
     * Sets the exception code in the <code>DataEntityException</code>.
     */
    public void setExceptionCode(TYPE code)
    {
        exceptionCode = code;
    }

}
