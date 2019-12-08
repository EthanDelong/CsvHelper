//*******************************************************//
//  Ethan DeLong & Joseph Boehme                         //
//  CIS 112                                              //
//  Professor Wertz                                      //
//  6 December 2019                                      //
//                                                       //
//  MappableSorterTest.java                              //
//                                                       //
//  Test class to validate our sorting methods.          //
//                                                       //
//*******************************************************//

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Testing for implementations of MappableSorter. Just need to make sure they work.
 */
public class MappableSorterTest
{
    // Contacts map that will be used for testing.
    Map<Integer, Contact> contacts;

    /**
     * Initialize test by reading in data.
     */
    @Before public void setUp()
    {
        try
        {
            // Create the base mapper.
            CsvMapper<Contact> csvMapper = new CsvMapper<Contact>((Class<Contact>)(new Contact()).getClass());
            
            // Map the 1k contacts file.
            final String testFile = "..\\data\\1k_Contacts.csv";
            contacts = csvMapper.mapCsvFile(testFile, true);
        }
        catch(Exception e)
        {
            Assert.fail(e.toString());
        }
    }
    
    /**
     * Tests the BubbleSorter implementation.
     */
    @Test public void bubbleSorterTest()
    {
        BubbleSorter<Contact> bubbleSorter = new BubbleSorter<Contact>(contacts);
        sortAndValidate(bubbleSorter);
    }
    
    /**
     * Tests the MergeSorter implementation.
     */
    @Test public void mergeSorterTest()
    {
        MergeSorter<Contact> mergeSorter = new MergeSorter<Contact>(contacts);
        sortAndValidate(mergeSorter);
    }
    
     @Test public void selectionSorterTest()
    {
        SelectionSorter<Contact> selectionSorter = new SelectionSorter<Contact>(contacts);
        sortAndValidate(selectionSorter);
    }
    
    @Test public void insertionSorterTest()
    {
        InsertionSorter<Contact> insertionSorter = new InsertionSorter<Contact>(contacts);
        sortAndValidate(insertionSorter);
    }
    
    /**
     * Validates the results from the given sorter;
     *
     * @param   sorter  The MappableSorter instance.
     */
    private void sortAndValidate(MappableSorter sorter)
    {
        for(int i = 0; i < 10; i++)
        {
            sorter.sort(i, false);
            validateResults(sorter, i, false);
            sorter.sort(i, true);
            validateResults(sorter, i, true);
        }
    }
    
    /**
     * Validates the results from the given sorter, index, and direction.
     *
     * @param   sorter  The MappableSorter instance.
     * @param   index   The column index to validate.
     * @param   asc     True if sorted in ascending order, otherwise false.
     */
    private <T extends Comparable<T>> void validateResults(MappableSorter sorter, int index, boolean asc)
    {
        try
        {
            T previous = null;
            Map<Integer, Mappable> results = sorter.getResults();
            for(Mappable item : results.values())
            {
                T current = item.getColumnAsType(index);
                if(previous != null)
                {
                    if(asc ? current.compareTo(previous) < 0 : current.compareTo(previous) > 0)
                    {
                        String className = sorter.getClass().getName();
                        Assert.fail("Failed during " + className + " validation of sort implementation.");
                    }
                }
                previous = current;
            }
        }
        catch(Exception e)
        {
            Assert.fail(e.toString());
        }
    }
}
