package com.apschulewitz.resdb.common.utils;

/**
 * Created by adrianschulewitz on 05/01/2017.
 */
public class StringUtils {

    /**
     * <CODE>getTag</CODE> returns the first tag found in the buffer.<br>
     *
     * @param buffer is a String containing the input text to be searched
     * @param startTag is a String containing the identifier denoting the start of a tag
     * @param endTag is a String containing the identifier denoting the end of a tag
     * @return
     */
    public static String getTag(String buffer, String startTag, String endTag)
    {
        return getNextTag(buffer, startTag, endTag, 0);
    }

    /**
     * <CODE>getNextTag</CODE> returns the first tag found after the start position
     * @param buffer is a String containing the input text to be searched
     * @param startTag is a String containing the identifier denoting the start of a tag
     * @param endTag is a String containing the identifier denoting the end of a tag
     * @param startPos is an integer denoting the position from which to search within the buffer
     * @return
     */
    public static String getNextTag(String buffer, String startTag, String endTag, int startPos)
    {
        String tagName = null;

        if (!isEmpty(buffer))
        {
            //int currentPos = startPos;
            int startOfTagPos = 0;
            int endOfTagPos = 0;

            System.out.println("Searching " + buffer +
                    " from starting position " + startPos +
                    " for startTag '" + startTag +
                    "' and endTag '" + endTag + "'");

            startOfTagPos = buffer.indexOf(startTag) + 1;
            endOfTagPos = buffer.indexOf(endTag);

            System.out.println("startOfTagPos = " + startOfTagPos + " endOfTagPos = " + endOfTagPos);

            if (startOfTagPos < 0 || endOfTagPos < 0)
                return tagName;

            tagName = buffer.substring(startOfTagPos, endOfTagPos);
        }

        return tagName;
    }

    public static boolean isEmpty(String input)
    {
        if (input == null || input.trim().length() == 0)
            return true;

        return false;
    }

    public static boolean isEmpty(StringBuilder input)
    {
        if (input == null || input.toString() == null || input.toString().trim().length() == 0)
            return true;

        return false;
    }
}
