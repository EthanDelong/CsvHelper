//*******************************************************//
//  Ethan DeLong & Joseph Boehme                         //
//  CIS 112                                              //
//  Professor Wertz                                      //
//  6 December 2019                                      //
//                                                       //
//  BubbleSort.java                                      //
//                                                       //
//  BubbleSort implementation of the MappableSorter.     //
//                                                       //
//*******************************************************//

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Extends the MappableSorter class with the Bubble Sort algorithm.
 */
public class BubbleSorter<TMappable extends Mappable> extends MappableSorter<TMappable>
{
    /**
     * Creates a new BubbleSorter from the input Map.
     */
    public BubbleSorter(Map<Integer, TMappable> input)
    {
        super(input);
    }
    
    /**
     * Sorts the Mappable object by the index column with the expected type T using BubbleSort.
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
        
        // Bubble sort is simple, but not efficient. It costs O(N^2) to process.
        for(int i = 0; i < sortedKeys.length; i++)
        {
            for(int j = 1; j < sortedKeys.length - i; j++)
            {
                // Get left and right values from initial mappedValues Map with the current key index
                int leftIndex = sortedKeys[j-1];
                int rightIndex = sortedKeys[j];
                T left = mappedValues.get(leftIndex);
                T right = mappedValues.get(rightIndex);
                
                // This way we can sort any Comparable object
                if(asc ? left.compareTo(right) > 0 : left.compareTo(right) < 0)
                {
                    // Swap LR
                    sortedKeys[j] = leftIndex;
                    sortedKeys[j-1] = rightIndex;
                }
            }
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