//*******************************************************//
//  Ethan DeLong & Joseph Boehme                         //
//  CIS 112                                              //
//  Professor Wertz                                      //
//  6 December 2019                                      //
//                                                       //
//  Mappable.java                                        //
//                                                       //
//  Abstract class used to define Mappable objects.      //
//                                                       //
//*******************************************************//

import java.lang.StringBuilder;
import java.text.ParseException;

/**
 * Abstract class for mappable objects to inherit.
 */
public abstract class Mappable
{
    /**
     * The number of columns mapped from the input.
     */
    private int mappedColumns = 0;

    /**
     * Empty initializer since objects don't have to be instantiated with an input array.
     */
    public Mappable()
    {
    }

    /**
     * Constructs the object with an input array of values to map.
     *
     * @param  input    A String array of columns to map to this object.
     *
     * @see #mapValues(String[])
     */
    public Mappable(String[] input)
    {
        mapValues(input);
    }
    
    /**
     * Gets the Id of the Mappable object.
     *
     * @return The Id of the object.
     */
    public abstract int getId();
    
    /**
     * Gets the column of this Mappable object by index.
     *
     * @param  index    The index of the column to get.
     *
     * @return The value of the column at the given index.
     *
     * @throws IndexOutOfBoundsException    If the specified index is not defined for this object.
     */
    public abstract Object getColumn(int index) throws IndexOutOfBoundsException;
    
    /**
     * Gets the comma-delimited header line for the Mappable object.
     *
     * @return  The comma-delimited header row for the Mappable object, for outputing to csv files.
     */
    public abstract String getHeaderCsvLine();
    
    /**
     * Gets the column of this Mappable object by index and converts it to type T.
     *
     * @param  index    The index of the column to get.
     *
     * @return The value of the column at the given index, converted to type T.
     *
     * @throws ClassCastException           If we failed to cast to type T.
     * @throws IndexOutOfBoundsException    If the specified index is not defined for this object.
     */
    public <T> T getColumnAsType(int index) throws ClassCastException, IndexOutOfBoundsException
    {
        return (T) getColumn(index);
    }
    
    /**
     * Sets the column of this Mappable object by index.
     *
     * @param   index   The index of the column to set.
     * @param   value   The input String value of the column to set.
     *
     * @throws IndexOutOfBoundsException    If the specified index is not defined for this object.
     * @throws ParseException               If there was a problem parsing a value.
     */
    public abstract void setColumn(int index, String value) throws IndexOutOfBoundsException, ParseException;
    
    /**
     * Maps the values of the given input to the Mappable object.
     *
     * @param  input    A String array of columns to map to the Contact.
     *
     * @throws IllegalArgumentException    If the input was not in the correct format.
     */
    public void mapValues(String[] input) throws IllegalArgumentException
    {
        mappedColumns = input.length;
        int columnIndex = 0;
        try
        {
            for(; columnIndex < mappedColumns; columnIndex++)
            {
                setColumn(columnIndex, input[columnIndex]);
            }
        }
        catch(ParseException e)
        {
            throw new IllegalArgumentException("Failed to parse value: '" + input[columnIndex] + "' index " + columnIndex, e);
        }
    }
    
    /**
     * Gets the Mappable object as a comma-delimited String of values.
     *
     * @return  The Mappable object as a comma-delimited String of values.
     *
     * @throws IndexOutOfBoundsException    If there was a problem getting the mapped index values.
     */
    public String toCsvLine() throws IndexOutOfBoundsException
    {
        String[] columns = new String[mappedColumns];
        for(int i = 0; i < mappedColumns; i++)
        {
            columns[i] = getColumn(i).toString();
        }
        return "\"" + String.join("\",\"", columns) + "\"";
    }
}