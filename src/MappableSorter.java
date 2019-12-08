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

import java.util.Map;

/**
 * The MappableSorter interface provides a set of standards that all 
 * map sorting implementations for our project must follow.
 */
public interface MappableSorter
{
    /**
     * Sorts the Mappable object by the index column with the expected type T.
     *
     * @param   input   The Mappable object to sort.
     * @param   index   The index of the column to sort.
     *
     * @return  A new sorted Map.
     *
     * @throws ClassCastException           If we failed to cast the column to type T.
     * @throws IndexOutOfBoundsException    If the specified index is not defined for this object.
     */
    public abstract <T> Map<Integer, Mappable> Sort(Mappable input, Integer index);
}