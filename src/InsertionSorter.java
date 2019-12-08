//*******************************************************//
//  Ethan DeLong & Joseph Boehme                         //
//  CIS 112                                              //
//  Professor Wertz                                      //
//  6 December 2019                                      //
//                                                       //
//  InsertionSorter.java                                 //
//                                                       //
//  Insertion Sort implementation of the MappableSorter. //
//                                                       //
//*******************************************************//

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Extends the MappableSorter class with the Insertion Sort algorithm.
 */
public class InsertionSorter<TMappable extends Mappable> extends MappableSorter<TMappable>
{
    /**
     * Creates a new InsertionSorter from the input Map.
     */
    public InsertionSorter(Map<Integer, TMappable> input)
    {
        super(input);
    }

    /**
     * Sorts the Mappable object by the index column with the expected type T using InsertionSort.
     *
     * @param   input   The Map of ids and Mappable objects.
     * @param   index   The index of the column to sort.
     * @param   asc     True to sort in ascending order, false to sort in descending order.
     *
     * @return  A new Map, sorted by the specified index column.
     */
    protected <T extends Comparable<T>> LinkedHashMap<Integer, TMappable> sort(Map<Integer, TMappable> input, int index, boolean asc)
    {
        // Use our internal helper method to get a new map with just the specified column value.
        Map<Integer, T> mappedValues = getMappedColumnValues(input, index);

        // Convert mapped values to array of keys for sorting
        Integer[] sortedKeys = mappedValues.keySet().toArray(new Integer[mappedValues.size()]);

        for (int count = 1; count < sortedKeys.length; count++)
        {
            int currentIndex = sortedKeys[count];
            T right = mappedValues.get(currentIndex);
            
            int current = count - 1;
            while ((current >= 0) 
                && (asc ? mappedValues.get(sortedKeys[current]).compareTo(right) > 0 
                        : mappedValues.get(sortedKeys[current]).compareTo(right) < 0))
            {
                sortedKeys[current + 1] = sortedKeys[current];
                current--;
            }
            sortedKeys[current + 1] = currentIndex;
        }

        // Create our result map from the sorted keys.
        LinkedHashMap<Integer, TMappable> result = new LinkedHashMap<Integer, TMappable>(input.size());
        for(Integer key : sortedKeys)
        {
            result.put(key, input.get(key));
        }
        return result;
    }

}