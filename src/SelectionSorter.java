//*******************************************************//
//  Ethan DeLong & Joseph Boehme                         //
//  CIS 112                                              //
//  Professor Wertz                                      //
//  6 December 2019                                      //
//                                                       //
//  SelectionSorter.java                                 //
//                                                       //
//  Selection Sort implementation of the MappableSorter. //
//                                                       //
//*******************************************************//

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Extends the MappableSorter class with the Selection Sort algorithm.
 */
public class SelectionSorter<TMappable extends Mappable> extends MappableSorter<TMappable>
{
    /**
     * Creates a new SelectionSorter from the input Map.
     */
    public SelectionSorter(Map<Integer, TMappable> input)
    {
        super(input);
    }

    /**
     * Sorts the Mappable object by the index column with the expected type T using SelectionSort.
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

        for(int current = 0; current < sortedKeys.length - 1; current++)
        {
            int indexOfMinOrMax = current;
            for (int i = current + 1; i < sortedKeys.length; i++)
            {
                int leftIndex = sortedKeys[i];
                T left = mappedValues.get(leftIndex);
                
                int rightIndex = sortedKeys[indexOfMinOrMax];
                T right = mappedValues.get(rightIndex);
                
                if (asc ? left.compareTo(right) < 0 : left.compareTo(right) > 0)
                {
                    indexOfMinOrMax = i;
                }
            }
            
            // Swap with min
            int temp = sortedKeys[current];
            sortedKeys[current] = sortedKeys[indexOfMinOrMax];
            sortedKeys[indexOfMinOrMax] = temp;
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