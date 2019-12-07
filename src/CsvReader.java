//*******************************************************//
//  Ethan DeLong & Joseph Boehme                         //
//  CIS 112                                              //
//  Professor Wertz                                      //
//  6 December 2019                                      //
//                                                       //
//  CsvReader.java                                       //
//                                                       //
//  Final project CsvReader class to read csv data.      //
//                                                       //
//*******************************************************//

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;   
import java.util.ArrayList;

/**
 * CsvReader class used to help process Csv files.
 *
 * @author Ethan DeLong
 * @author Joseph Boehme
 */
public class CsvReader implements Closeable
{
    /**
     * The qualifier character for encapsulating columns.
     */
    final char qualifier = '"';
    
    /**
     * The delimiter character for separating columns.
     */
    final char delimiter = ',';

    /**
     * The full path to the csv file.
     */
    private BufferedReader reader;
    
    /**
     * True if the file has headers.
     */
    private boolean headers;
    
    /**
     * Whether we started processing yet.
     */
    private boolean started = false;
    
    /**
     * Whether we reached the end of stream.
     */
    private boolean complete = false;

    /**
     * Constructs a new CsvReader object from the given file path.
     *
     * @param   fileName    The full path to the input csv file.
     *
     * @throws FileNotFoundException        If the file does not exist.
     * @throws UnsupportedEncodingException If the specified encoding is not supported.
     */
    public CsvReader(String fileName) throws FileNotFoundException, UnsupportedEncodingException
    {
        this(fileName, false);
    }
    
    /**
     * Constructs a new CsvReader object from the given file path.
     *
     * @param   fileName    The full path to the input csv file.
     * @param   headers     True if the file contains a header row.
     *
     * @throws FileNotFoundException If the file does not exist.
     * @throws UnsupportedEncodingException If the specified encoding is not supported.
     */
    public CsvReader(String fileName, boolean headers) throws FileNotFoundException, UnsupportedEncodingException
    {
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
        this.headers = headers;
    }

    /**
     * Reads the next set of lines as a string array.
     *
     * @return  The next row converted to a String array of columns.
     *
     * @throws  IOException If there is a problem processing the stream.
     */
    public String[] readNext() throws IOException
    {
        String[] parsedColumns = new String[0];
    
        if(!started)
        {
            if(headers)
            {
                // Skip header row on startup.
                reader.readLine();
            }
            started = true;
        }
        else if(complete)
        {
            return null;
        }
    
        String line = reader.readLine();
        if(line == null)
        {
            // End of stream, return null.
            complete = true;
            return null;
        }
        
        if(line.length() == 0)
        {
            // Empty line, return empty array
            return parsedColumns;
        }
        
        // Build our columns from the input line using the specified delimiter and qualifier
        ArrayList<String> columns = new ArrayList<String>();
        StringBuilder builder = new StringBuilder(line.length());
        boolean inQualifier = false;
        boolean inColumn = false;
        char current = ' ';
        for(int i = 0; i < line.length(); i++)
        {
            char next = line.charAt(i);
            switch(next)
            {
                case qualifier:
                    // If we are not in a column, then this column is qualified.
                    if(!inColumn)
                    {
                        inQualifier = true;
                        inColumn = true;
                    }
                    // If the last character was a qualifier, then we are printing (double-char-escape).
                    else if(current == qualifier)
                    {
                        builder.append(next);
                        // Reset the current char
                        current = ' ';
                    }
                    else
                    {
                        current = next;
                    }
                    // Qualifying characters are never printed unless they are escaped.
                    continue;
                    
                case delimiter:
                    // If we are not in a qualifier, OR if our last character
                    // ended the qualifier, then this is a new column to add.
                    if(!inQualifier || current == qualifier)
                    {
                        columns.add(builder.toString());
                        builder.setLength(0);
                        inColumn = false;
                        continue;
                    }
                    break;
            }
            builder.append(next);
            current = next;
        }
        // Add the last column to the ArrayList and return it.
        columns.add(builder.toString());
        return columns.toArray(parsedColumns);
    }

    /**
     * Close any open resources.
     *
     * @throws  IOException  If there is a problem closing the stream.
     */
    public void close() throws IOException
    {
        try
        {
            reader.close();
        }
        catch(Exception e)
        {
            throw new IOException("Failed to close stream.", e);
        }
    }
}