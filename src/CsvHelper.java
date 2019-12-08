//*******************************************************//
//  Ethan DeLong & Joseph Boehme                         //
//  CIS 112                                              //
//  Professor Wertz                                      //
//  6 December 2019                                      //
//                                                       //
//  CsvHelper.java                                       //
//                                                       //
//  The main driver for our csv helper application.      //
//                                                       //
//*******************************************************//

import java.util.Map;
import java.util.Scanner;

/**
 * CsvHelper is the main driver for our application. It contains the
 * entry point for our code and handles parsing all user input and 
 * outputting feedback to the console.
 * <p>
 * Upon running the program, the user is presented with a prompt, 
 * which will request the path to a csv file, followed by the return 
 * key. The file must be in the appropriate format (comma separated 
 * values). The standard qualifying character, quote ("), is also 
 * supported with this format. The following input:
 * <p>
 * <code>Column A, "Column, B", Column C</code>
 * <p>
 * Would register the columns:
 * <ul>
 * <li>Column A</li>
 * <li>Column, B</li>
 * <li>Column C</li>
 * </ul>
 * <p>
 * Additionally, csv files must be a fixed width. This means that all 
 * lines must have the same number of input rows. The program assumes 
 * the first line to be the "header" row, and will not count it as 
 * actual data. This means that there must be at least 2 lines to 
 * process data.
 * <p>
 * If anything is wrong with the input file, an exception will be thrown 
 * during the CsvHelper parsing, and the message will be presented to 
 * the user describing the issue. From there, the user will be brought 
 * back to the original prompt for an input file.
 * <p>
 * After a valid file is presented, the user will be presented with a list 
 * of operations to perform. The operations available and their descriptions 
 * are as follows:
 * <ul>
 * <li>
 * <strong>sort</strong> <em>n</em> [asc|desc] -- 
 * Sorts the <em>n</em><sup>th</sup> column in <strong>asc</strong>ending 
 * or <strong>desc</strong>ending order.
 * </li>
 * </ul>
 * <p>
 * Example Usage<p>
 * <code><strong>Please enter the contact file path, followed by the return key:</strong> C:\Path\To\File.csv</code><p>
 * <code>Mapped 1000 record(s).</code><p>
 * <code>You may now enter from one of the following commands</code><p>
 * <code>sort n [asc|desc]</code><p>
 * <code>Found 3 column(s), please enter from the following commands:</code><p>
 * <code><strong>Please enter a command:</strong> sort 1 asc</code><p>
 * 
 *    TODO: Finish javadocs
 *
 * @author Ethan DeLong
 * @author Joseph Boehme
 */
public class CsvHelper
{
    /**
     * The main entry point to our application.
     * @param args  The arguments passed into the command line.
     */
    public static void main(String[] args)
    {
    
        try
        {
            // First, let's get a valid input file and create the map
            Scanner scanner = new Scanner(System.in);
            Class<Contact> classType = (Class<Contact>)(new Contact()).getClass();
            CsvMapper<Contact> csvMapper = new CsvMapper<Contact>(classType);
            Map<Integer, Contact> contacts = null;
            while(contacts == null)
            {
                String inputFile = "";
                try
                {
                    System.out.print("Please enter the contact file path, followed by the return key: ");
                    inputFile = scanner.nextLine();
                    contacts = csvMapper.mapCsvFile(inputFile, true);
                }
                catch(Exception e)
                {
                    System.out.println("Could not read input file: '" + inputFile + "'");
                    contacts = null;
                }
            }
        
            // Map the input csv
            
            // Print out the input in the order it came
            /*
            for(Contact contact : contacts.values())
            {
                System.out.println(contact.getId() + ": " + contact.toString());
            }
            */
            
            BubbleSorter<Contact> bubbleSorter = new BubbleSorter<Contact>(contacts);
            bubbleSorter.sort(1, false);
            
            // Sort the contacts by name using merge sort algorithms
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}