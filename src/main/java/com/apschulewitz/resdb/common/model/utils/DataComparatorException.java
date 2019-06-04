/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apschulewitz.resdb.common.model.utils;


import com.apschulewitz.resdb.common.ResearchDatabaseModelException;

/**
 * <p><CODE>DataComparatorException</CODE> is used to report an exception condition
 * arising to create a DataComparator from an invalid combination of comparison operator,
 * data value and data type arguments.<</p>
 *
 * @author adrian
 */
public class DataComparatorException extends ResearchDatabaseModelException
{
    private String attributeName;
    private DataComparator dataComparator;

    /**<CODE>DataComparatorException</CODE> constructor
     *
     * @param message a String holding the message describing the exception condition
     */
    public DataComparatorException(String message)
    {
        super(message);
    }

    /**
     * <CODE>DataComparatorException</CODE> constructor
     *
     * @param comparator  a DataComparator holding the data comparator
     * @param attrName    a String holding the name of attribute being compared
     * @param message     a String holding the message describing the exception condition
     *
     * @see DataComparator
     */
    public DataComparatorException(Throwable cause, DataComparator comparator, String attrName, String message)
    {
        super(message, cause);
        dataComparator = comparator;
        attributeName = attrName;
    }

    public DataComparator getDataComparator()
    {
        return dataComparator;
    }

    public String getAttributeName()
    {
        return attributeName;
    }
}
