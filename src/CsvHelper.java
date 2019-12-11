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

import java.io.File;
import java.io.FileWriter;
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
 * supported with this format.
 * <p>
 * Currently, the csv file must have columns matching the Contact type.
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
 * <code><strong>Please enter the contact file path, followed by the return key:</strong> C:\Path\To\File.csv</code><br>
 * <code>Mapped 1000 record(s).</code><br>
 * <code>You may now enter a sort command using the following pattern:</code><br>
 * <code>sort [[b]ubble]|[i]nsert|[m]erge|[s]election] columnIndex [asc|desc]</code><br><br>
 * <code>Example:</code><br>
 * <code>sort m 2 desc -- merge sort column with index 2 in descending order</code><br><br>
 * <code><strong>Please enter a command, or enter to continue:</strong> sort bubble 1 a</code><br>
 * <code>Sorted 1000 row(s) using BubbleSorter, duration: 00:00:00.47</code><br>
 * <code><strong>Please enter a command, or enter to continue:</strong> sort select 1 a</code><br>
 * <code>Sorted 1000 row(s) using SelectionSorter, duration: 00:00:00.32</code><br>
 * <code><strong>Please enter a command, or enter to continue:</strong> sort insert 1 a</code><br>
 * <code>Sorted 1000 row(s) using InsertionSorter, duration: 00:00:00.0</code><br>
 * <code><strong>Please enter a command, or enter to continue:</strong> sort merge 1 a</code><br>
 * <code>Sorted 1000 row(s) using MergeSorter, duration: 00:00:00.1</code><br>
 * <code><strong>Please enter a command, or enter to continue:</strong> </code><br>
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

            // Now let's process sorting commands!
            System.out.println("You may now enter a sort command using the following pattern:");
            System.out.println("sort [[b]ubble]|[i]nsert|[m]erge|[s]election] columnIndex [asc|desc]");
            System.out.println();
            System.out.println("Example:");
            System.out.println("sort m 2 desc -- merge sort column with index 2 in descending order");
            System.out.println();

            MappableSorter<Contact> sorter = null;

            boolean sortComplete = false;
            while(!sortComplete)
            {
                String command = "";
                try
                {
                    System.out.print("Please enter a command, or enter to continue: ");
                    command = scanner.nextLine();

                    // If this is blank, then they are done (enter to continue)
                    if(command.isEmpty())
                    {
                        sortComplete = true;
                        break;
                    }
                    // They entered something, begin parsing command
                    String[] parts = command.split(" ");
                    if(parts.length != 4)
                    {
                        System.out.println("Command must have 4 parts. Please see example usage.");
                        continue;
                    }
                    // Validate this is a sort command
                    String baseCommand = parts[0].trim();
                    if(!baseCommand.equalsIgnoreCase("sort"))
                    {
                        System.out.println("Unrecognized comand: '" + baseCommand + "'");
                        continue;
                    }
                    // Get the sorter
                    sorter = getSorter(parts[1], contacts);
                    if(sorter == null)
                    {
                        System.out.println("Unrecognized sort type: " + parts[1]);
                        continue;
                    }
                    // Get the column
                    int columnIndex = -1;
                    try
                    {
                        columnIndex = Integer.parseInt(parts[2].trim());
                        contacts.entrySet().iterator().next().getValue().getColumn(columnIndex);
                    }
                    catch(Exception e)
                    {
                        System.out.println("Failed to parse column index: " + parts[2]);
                        continue;
                    }
                    // Get the order (asc or desc)
                    boolean asc;
                    String ordString = parts[3].toLowerCase().trim();
                    if(ordString.startsWith("a"))
                    {
                        asc = true;
                    }
                    else if(ordString.startsWith("d"))
                    {
                        asc = false;
                    }
                    else
                    {
                        System.out.println("Failed to parse order string: '" + parts[3] + "' only [a]sc/[d]esc accepted");
                        continue;
                    }
                    try
                    {
                        // Sort and assign results to current variable
                        sorter.sort(columnIndex, asc);
                        contacts = sorter.getResults();
                    }
                    catch(Exception e)
                    {
                        System.out.println("Failed trying to run command: " + command);
                        e.printStackTrace();
                    }
                }
                catch(Exception e)
                {
                    System.out.println("Could not interpret command: '" + command + "'");
                    e.printStackTrace();
                }
            }

            String defaultPath = new File(".").getAbsolutePath();
            System.out.println("Relative directory: " + defaultPath);
            boolean exit = false;
            while(!exit)
            {
                FileWriter writer = null;
                try
                {
                    System.out.print("Sorting complete, enter a filename to save to, or press enter to quit: ");
                    String outputFilepath = scanner.nextLine();
                    
                    writer = new FileWriter(outputFilepath);
                    
                    writer.write(contacts.values().stream().findFirst().get().getHeaderCsvLine() + "\r\n");
                    for (Map.Entry<Integer, Contact> entry : contacts.entrySet())
                    {
                        writer.write(entry.getValue().toCsvLine() + "\r\n");
                    }
                    exit = true;
                }
                catch(Exception e)
                {
                    System.out.println("Exception encountered while trying to save file.");
                    e.printStackTrace();
                }
                finally
                {
                    if(writer != null)
                    {
                        try
                        {
                            writer.close();
                        }
                        catch(Exception e)
                        {
                            System.out.println("Failed to close writer.");
                            e.printStackTrace();
                        }
                    }
                }
            }

            

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Gets the MappableSorter from the input type.
     *
     * @param   type    The type of sort (bubble/insert/merge/select).
     *
     * @return  The MappableSorter, or null if it couldn't interpret the input.
     */
    private static <T extends Mappable> MappableSorter<T> getSorter(String type, Map<Integer, T> input)
    {
        switch(type.trim().toLowerCase())
        {
            case "b":
            case "bubble":
            case "bubblesort":
            case "bubblesorter":
                return new BubbleSorter(input);

            case "i":
            case "insert":
            case "insertsort":
            case "insertsorter":
            case "insertion":
            case "insertionsort":
            case "insertionsorter":
                return new InsertionSorter(input);

            case "m":
            case "merge":
            case "mergesort":
            case "mergesorter":
                return new MergeSorter(input);

            case "s":
            case "select":
            case "selectsort":
            case "selectsorter":
            case "selection":
            case "selectionsort":
            case "selectionsorter":
                return new SelectionSorter(input);

            default:
                return null;
        }
    }
}