/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apschulewitz.resdb.common.model.utils;

import lombok.*;

/**
 *
 * @author adrian
 */
@Getter
//@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DataComparator
{
    private DataComparatorOperator operator;
    private Object value;
    private String valueType;

//
//    public DataComparator() {
//
//    }

//    /**
//     * <CODE>DataComparator</CODE> constructor.<br>
//     * @param dataComparisonOperator a DataComparisonOperator controlling how the data value is to be compared
//     * @param dataValue an Object holding the data value to use in the comparison
//     * @param dataType a String representing the class of the data type of the value to compare
//     * @see DataComparator.DataComparatorOperator
//     *
//     */
//    public DataComparator(DataComparatorOperator dataComparisonOperator, Object dataValue, String dataType) {
//        operator = dataComparisonOperator;
//        value = dataValue;
//        valueType = dataType;
//    }

//    public DataComparatorOperator getDataComparatorOperator()
//    {
//        return operator;
//    }

//    public void setDataComparatorOperator(DataComparatorOperator dataComparatorOperator) {
//        operator = dataComparatorOperator;
//    }

//    public Object getValue()
//    {
//        return value;
//    }
//
//    public void setValue(Object dataValue) {
//        value = dataValue;
//    }

//    public String getValueType()
//    {
//        return valueType;
//    }
//
//    public void setValueType(String dataType)
//    {
//        valueType = dataType;
//    }

//    @Override
//    public String toString() {
//        StringBuilder buffer = new StringBuilder();
//        buffer.append("DataComparator: operator=" + operator + " value=" + value + " valueType=" + valueType);
//        return buffer.toString();
//    }

    /*private void convertDataType(Object valueToConvert) throws ResearchDatabaseModelException
    {
        Class cls = null;
        try
        {
            cls = Class.forName(valueType);
            value = cls.cast(valueToConvert);
        }
        catch (ClassNotFoundException cnfe)
        {
            throw new ResearchDatabaseModelException(cnfe);
        }
    }*/

    public enum DataComparatorOperator {
        EQUAL_TO("="),
        LESS_THAN("<"),
        LESS_THAN_OR_EQUAL_TO("<="),
        GREATER_THAN(">"),
        GREATER_THAN_OR_EQUAL_TO(">="),
        CONTAINS("Contains"),
        STARTS_WITH("Starts with"),
        ENDS_WITH("Ends with");

        private String name;

        private DataComparatorOperator(String n)
        {
            name = n;
        }

        /**
         * <CODE>toDataComparatorOperator</CODE> is a helper method to create an operator.
         *
         * @param opString  a string value, one of: '=', '<=', '<', '>', '>=', 'Contains', 'Ends with'
         *
         * @return
         */
        public static DataComparatorOperator toDataComparatorOperator(String opString) {
            if (opString.equals("="))
                return EQUAL_TO;
            else if (opString.equals("<"))
                return LESS_THAN;
            else if (opString.equals("<="))
                return LESS_THAN_OR_EQUAL_TO;
            else if (opString.equals(">"))
                return GREATER_THAN;
            else if (opString.equals(">="))
                return GREATER_THAN_OR_EQUAL_TO;
            else if (opString.equals("Contains"))
                return CONTAINS;
            else if (opString.equals("Starts with"))
                return STARTS_WITH;
            else if (opString.equals("Ends with"))
                return ENDS_WITH;
            else return null;
        }

        @Override
        public String toString()
        {
            return name;
        }
    }

}
