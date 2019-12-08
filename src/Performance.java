//*******************************************************//
//  Ethan DeLong & Joseph Boehme                         //
//  CIS 112                                              //
//  Professor Wertz                                      //
//  6 December 2019                                      //
//                                                       //
//  Performance.java                                     //
//                                                       //
//  Keeps track of performance statistics in our         //
//  application.                                         //
//                                                       //
//*******************************************************//

import java.lang.IllegalArgumentException;
import java.lang.IllegalStateException;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;

/**
 * Performance class for tracking perf metrics across our program.
 */
public class Performance
{
    /**
     * We will use a static HashMap to store timers by their Id. Allows us to multi-thread if we wanted to 
     * in the future and track various timers with different ids. Need to ensure we synchronize every time
     * we access this for concurrency.
     */
    private static HashMap<String, Timer> timers = new HashMap<String, Timer>();
    
    /**
     * Creates a new timer or starts an existing timer with the current system clock time.
     *
     * @param   id  The unique identifier for the timer.
     *
     * @throws  IllegalStateException   If the specified timer already exists and is running.
     */
    public static void startTimer(String id) throws IllegalStateException
    {
        Instant start = Instant.now();
        synchronized(timers)
        {
            if(timers.containsKey(id))
            {
                timers.get(id).run(start);
            }
            else
            {
                timers.put(id, new Timer(start));
            }
        }
    }
    
    /**
     * Stops an existing timer by the given id.
     *
     * @param   id  The unique identifier for the timer.
     *
     * @throws  IllegalStateException   If the specified timer already exists and is stopped.
     * @throws  InvalidKeyException     If the specified id does not exist.
     */
    public static void stopTimer(String id) throws IllegalStateException, IllegalArgumentException
    {
        Instant end = Instant.now();
        synchronized(timers)
        {
            if(timers.containsKey(id))
            {
                timers.get(id).stop(end);
            }
            else
            {
                // The timer with the given id doesn't exist, we can't stop an invalid timer -- throw an exception.
                throw new IllegalArgumentException("Could not find timer with id: '" + id + "'");
            }
        }
    }
    
    /**
     * Gets the Duration of the given timer as a String with hh:mm:ss.ms format.
     *
     * @param   id  The unique identifier for the timer.
     *
     * @throws  IllegalArgumentException    If the specified id does not exist.
     *
     * @return  The Duration of the elapsed time for the given Timer represented as a String.
     */
    public static String formatDurationString(String id) throws IllegalArgumentException
    {
        synchronized(timers)
        {
            if(timers.containsKey(id))
            {
                return timers.get(id).toString();
            }
            else
            {
                // The timer with the given id doesn't exist, we can't stop an invalid timer -- throw an exception.
                throw new IllegalArgumentException("Could not find timer with id: '" + id + "'");
            }
        }
    }
}
