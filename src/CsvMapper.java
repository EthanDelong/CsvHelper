//*******************************************************//
//  Ethan DeLong & Joseph Boehme                         //
//  CIS 112                                              //
//  Professor Wertz                                      //
//  6 December 2019                                      //
//                                                       //
//  CsvHelper.java                                       //
//                                                       //
//  Final project CsvHelper class to process csv data.   //
//                                                       //
//*******************************************************//

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.IllegalAccessException;
import java.lang.InstantiationException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * The CsvMapper class is used for converting csv files to maps.
 */
public class CsvMapper<T extends Mappable>
{
    /**
     * The Constructor used to reflectively instantiate Mappable objects.
     */
    private Constructor<T> classConstructor;

    /**
     * Creates a new instance of CsvMapper with the given Mappable T used for reflective instantiation.
     *
     * @param   classType   Reference to the class that is used to create a Constructor.
     *
     * @throws  NoSuchMethodException   If it fails to get the constructor for the Mappable T object.
     */
    public CsvMapper(Class<T> classType) throws NoSuchMethodException
    {
        classConstructor = classType.getDeclaredConstructor();
    }

    /**
     * Creates a Map from the input csv file containing a collection of the specified Mappable types.
     *
     * @param   fileName    The full path to the csv file.
     * @param   headers     True if the input file contains row headers.
     *
     * @return  A map collection of the current Mappable T type.
     *
     * @throws  IOException If there is an issue processing the file stream.
     */
    public Map<Integer, T> mapCsvFile(String fileName, boolean headers)
        throws FileNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, IOException
    {
        Map<Integer, T> csvMap = new HashMap<>();
        CsvReader csvReader = null;
        try
        {
            csvReader = new CsvReader(fileName, headers);
            String[] nextLine;
            String timerKey = "map:" + fileName;
            Performance.startTimer(timerKey);
            while((nextLine = csvReader.readNext()) != null)
            {
                T instance = classConstructor.newInstance();
                instance.mapValues(nextLine);
                csvMap.put(instance.getId(), instance);
            }
            Performance.stopTimer(timerKey);
            System.out.println("Mapped " + csvMap.size() + " record(s), duration: " + Performance.formatDurationString(timerKey));
        }
        catch (Exception e)
        {
            // Re-throw, we're only catching to make sure we're disposing the stream.
            throw e;
        }
        finally
        {
            if(csvReader != null)
            {
                csvReader.close();
            }
        }
        return csvMap;
    }
}