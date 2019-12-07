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

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
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
    public Map<Integer, T> mapCsvFile(String fileName, boolean headers) throws IOException
    {
        Map<Integer, T> csvMap = new HashMap<>();
        CsvReader csvReader = null;
        try
        {
            csvReader = new CsvReader(fileName, headers);
            String[] nextLine;
            while((nextLine = csvReader.readNext()) != null)
            {
                T instance = classConstructor.newInstance();
                instance.mapValues(nextLine);
                csvMap.put(instance.getId(), instance);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(csvReader != null)
            {
                csvReader.close();
            }
            return csvMap;
        }
    }
}