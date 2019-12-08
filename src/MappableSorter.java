//*******************************************************//
//  Ethan DeLong & Joseph Boehme                         //
//  CIS 112                                              //
//  Professor Wertz                                      //
//  6 December 2019                                      //
//                                                       //
//  MappableSorter.java                                  //
//                                                       //
//  The interface for our map sorting implementations.   //
//                                                       //
//*******************************************************//

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The MappableSorter interface provides a set of standards that all 
 * map sorting implementations for our project must follow.
 */
public abstract class MappableSorter<TMappable extends Mappable>
{
    /**
     * The current result set.
     */
    private Map<Integer, TMappable> current;

    /**
     * Creates a new MappableSorter from the input Map.
     */
    public MappableSorter(Map<Integer, TMappable> input)
    {
        this.current = input;
    }
    
    /**
     * Sorts the current set by the index column with the expected type T, defaulting to ascending order.
     *
     * @param   index   The index of the column to sort.
     *
     * @throws ClassCastException           If we failed to cast the column to type T.
     * @throws IndexOutOfBoundsException    If the specified index is not defined for this object.
     */
    public <T extends Comparable<T>> void sort(int index)
    {
        sort(index, true);
    }
    
    /**
     * Sorts the current set by the index column with the expected type T in the specified order.
     *
     * @param   index   The index of the column to sort.
     * @param   asc     True to sort in ascending order, false to sort in descending order.
     *
     * @throws ClassCastException           If we failed to cast the column to type T.
     * @throws IndexOutOfBoundsException    If the specified index is not defined for this object.
     */
    public <T extends Comparable<T>> void sort(int index, boolean asc)
    {
        current = sort(current, index, asc);
    }
    
    /**
     * Returns the current result set.
     *
     * @return  The Map<Integer, TMappable> result set.
     */
    public Map<Integer, TMappable> getResults()
    {
        return current;
    }

    /**
     * Sorts the Mappable object by the index column with the expected type T.
     *
     * @param   input   The Map of ids and Mappable objects.
     * @param   index   The index of the column to sort.
     * @param   asc     True to sort in ascending order, false to sort in descending order.
     *
     * @return  A new Map, sorted by the specified index column.
     */
    protected abstract <T extends Comparable<T>> LinkedHashMap<Integer, TMappable> sort(Map<Integer, TMappable> input, int index, boolean asc);
    
    /**
     * Gets the values of the current map for the specified column index as a new Map of just the key and column value.
     *
     * @param   input   The Map of ids and Mappable objects.
     * @param   index   The index of the column to get the values for.
     *
     * @return  A new Map with just the key and value of the column at the specified index.
     */
    protected <T> Map<Integer, T> getMappedColumnValues(Map<Integer, TMappable> input, int index)
    {
        Map<Integer, T> columnValues = new LinkedHashMap<Integer, T>();
        for(Mappable item : input.values())
        {
            T value = item.getColumnAsType(index);
            columnValues.put(item.getId(), value);
        }
        return columnValues;
    }
}