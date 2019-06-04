package com.apschulewitz.resdb.common;

public class ResearchDatabaseModelException extends RuntimeException
{

	private static final long serialVersionUID = 7175575295455185386L;

	public ResearchDatabaseModelException(Throwable cause)
    {
        super(cause);
    }

    public ResearchDatabaseModelException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public ResearchDatabaseModelException(String message)
    {
        super(message);
    }

    public ResearchDatabaseModelException()
    {
    }

}
