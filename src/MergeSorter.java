//*******************************************************//
//  Ethan DeLong & Joseph Boehme                         //
//  CIS 112                                              //
//  Professor Wertz                                      //
//  6 December 2019                                      //
//                                                       //
//  MergeSort.java                                       //
//                                                       //
//  MergeSort implementation of the MappableSorter.      //
//                                                       //
//*******************************************************//

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Extends the MappableSorter class with the Merge Sort algorithm.
 */
public class MergeSorter<TMappable extends Mappable> extends MappableSorter<TMappable>
{
    /**
     * Creates a new MergeSorter from the input Map.
     *
     * @param input The input Map.
     */
    public MergeSorter(Map<Integer, TMappable> input)
    {
        super(input);
    }
    
    /**
     * Sorts the Mappable object by the index column with the expected type T using MergeSort.
     *
     * @param   <T>     The Comparable type to sort.
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
        
        // Sort!
        sortedKeys = mergeSort(mappedValues, sortedKeys, asc);
        
        // Create our result map from the sorted keys.
        LinkedHashMap<Integer, TMappable> result = new LinkedHashMap<Integer, TMappable>(input.size());
        for(Integer key : sortedKeys)
        {
            result.put(key, input.get(key));
        }
        return result;
    }
    
    /**
     * Splits the input keys into two arrays and individually sorts and merges them together using the values in the given Map.
     *
     * @param   <T>     The Comparable type to sort.
     * @param   values  The Map of ids and values.
     * @param   keys    The array of keys.
     * @param   asc     True to sort in ascending order, false to sort in descending order.
     *
     * @return  A sorted array of keys.
     */
    private <T extends Comparable<T>> Integer[] mergeSort(Map<Integer, T> values, Integer[] keys, boolean asc)
    {
        // If we have a single item in the set, just return here
        if(keys.length <= 1)
        {
            return keys;
        }
        
        // Get the mid value and split the incoming array into two equal sides (as close as possible)
        int mid = keys.length / 2;
        Integer[] leftKeys = new Integer[mid];
        Integer[] rightKeys = new Integer[keys.length - mid];
        for(int i = 0; i < leftKeys.length; i++)
        {
            leftKeys[i] = keys[i];
        }
        for(int i = mid, j = 0; i < keys.length; i++)
        {
            rightKeys[j++] = keys[i];
        }
        
        // Finally, we want to recursively mergeSort left and right indexes, and call merge on the two sides.
        // At the inner-most depth, our merge will be comparing single left/right arrays.
        // The results of these merges will be sorted, which means subsequent merges up the chain will only
        // need to iterate once through both sets as they merge up. This helps prevent performance gain
        // over time by dividing the work, leaving us with a cost of O(n log n)
        return merge(values, mergeSort(values, leftKeys, asc), mergeSort(values, rightKeys, asc), asc);
    }
    
    /**
     * Merges two sorted arrays into one result array, sorting the final array in the process.
     *
     * @param   <T>     The Comparable type to sort.
     * @param   values  The Map of ids and values.
     * @param   keys    The array of keys.
     * @param   asc     True to sort in ascending order, false to sort in descending order.
     *
     * @return  A sorted array of keys.
     */
    private <T extends Comparable<T>> Integer[] merge(Map<Integer, T> values, Integer[] leftKeys, Integer[] rightKeys, boolean asc)
    {
        Integer[] result = new Integer[leftKeys.length + rightKeys.length];
        for(int resultIndex = 0, leftIndex = 0, rightIndex = 0; resultIndex < result.length; resultIndex++)
        {
            if(leftIndex >= leftKeys.length)
            {
                // If we've already added all of the left keys, add the remaining right.
                result[resultIndex] = rightKeys[rightIndex++];
            }
            else if(rightIndex >= rightKeys.length)
            {
                // On the other hand, if all of the right keys are used, add the remaining left.
                result[resultIndex] = leftKeys[leftIndex++];
            }
            else
            {
                // We have available values in both left and right arrays, get the values and return the next one in the sort.
                T left = values.get(leftKeys[leftIndex]);
                T right = values.get(rightKeys[rightIndex]);
                
                if(asc ? left.compareTo(right) < 0 : left.compareTo(right) > 0)
                {
                    // Left should be pushed next (ascending, left < right / descending, left > right)
                    result[resultIndex] = leftKeys[leftIndex++];
                }
                else
                {
                    // Otherwise, right should be pushed next. If they're equal, it won't matter which order.
                    result[resultIndex] = rightKeys[rightIndex++];
                }
            }
        }
        // And now we return our merged array.
        return result;
    }
}