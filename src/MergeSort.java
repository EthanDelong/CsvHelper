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

import java.util.HashMap;
import java.util.Map;

/**
 * Implements the MappableSorter interface with the MergeSort algorithm.
 */
public class MergeSort implements MappableSorter
{
    /**
     * Sorts the Mappable object by the index column with the expected type T using MergeSort.
     *
     * @param   input   The Mappable object to sort.
     * @param   index   The index of the column to sort.
     *
     * @return  A new map, where the key is the sort index and the value is the
     *          original Mappable object's Id.
     *
     * @throws ClassCastException           If we failed to cast the column to type T.
     * @throws IndexOutOfBoundsException    If the specified index is not defined for this object.
     */
    public <T> Map<Integer, Mappable> Sort(Mappable input, Integer index)
    {
        return null;
    }
}