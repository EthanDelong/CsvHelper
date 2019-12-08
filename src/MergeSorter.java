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
     */
    public MergeSorter(Map<Integer, TMappable> input)
    {
        super(input);
    }
    
    /**
     * Sorts the Mappable object by the index column with the expected type T using MergeSort.
     *
     * @param   input   The Map of ids and Mappable objects.
     * @param   index   The index of the column to sort.
     * @param   asc     True to sort in ascending order, false to sort in descending order.
     *
     * @return  A new map, where the key is the sort index and the value is the
     *          original Mappable object's Id.
     *
     * @throws ClassCastException           If we failed to cast the column to type T.
     * @throws IndexOutOfBoundsException    If the specified index is not defined for this object.
     */
    protected <T extends Comparable<T>> LinkedHashMap<Integer, TMappable> sort(Map<Integer, TMappable> input, int index, boolean asc)
    {
        return null;
    }
}