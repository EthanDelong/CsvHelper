//*******************************************************//
//  Ethan DeLong & Joseph Boehme                         //
//  CIS 112                                              //
//  Professor Wertz                                      //
//  6 December 2019                                      //
//                                                       //
//  Timer.java                                           //
//                                                       //
//  Basic timer implementation.                          //
//                                                       //
//*******************************************************//

import java.lang.IllegalStateException;
import java.time.Duration;
import java.time.Instant;

/**
 * Inner timer for performance tracking.
 */
public class Timer
{
    /**
     * The start instant from the system clock.
     */
    Instant start = null;
    
    /**
     * The end instant from the system clock.
     */
    Instant end = null;
    
    /**
     * True if the timer is currently running.
     *
     * @seealso #run(), #stop()
     */
    boolean running = false;
    
    /**
     * Creates a new timer with the given start instant from the system clock.
     *
     * @param   The start instant from the system clock.
     */
    public Timer(Instant start)
    {
        run(start);
    }
    
    /**
     * Runs the timer, setting the specified start instance.
     *
     * @param   start   The start instant from the system clock.
     *
     * @throws  IllegalStateException   If the timer is already running.
     */
    public void run(Instant start) throws IllegalStateException
    {
        if(running)
        {
            throw new IllegalStateException("Cannot run timer when it is already running.");
        }
        this.start = start;
        running = true;
    }
    
    /**
     * Runs the timer, setting the specified start instance.
     *
     * @param   end The end instant from the system clock.
     *
     * @throws  IllegalStateException   If the timer is already stopped.
     */
    public void stop(Instant end) throws IllegalStateException
    {
        if(!running)
        {
            throw new IllegalStateException("Cannot stop timer when it is already stopped.");
        }
        this.end = end;
        running = false;
    }
    
    /**
     * Gets the Duration of the elapsed time as a String.
     *
     * @return  The Duration of the elapsed time as a String.
     */
    public String toString()
    {
        Duration duration;
        if(running)
        {
            // If the timer is currently running, get the current time for the duration comparison.
            duration = Duration.between(start, Instant.now());
        }
        else
        {
            duration = Duration.between(start, end);
        }
        // Should translate to- hh:mm:ss.ms
        long seconds = duration.getSeconds();
        long millis = duration.getNano() / 1000000; // from what I gather, ms is already pretty innaccurate, let alone nanos.
        return String.format("%02d:%02d:%02d.%d", seconds / 3600, (seconds % 3600) / 60, (seconds % 60), millis);
    }
}