//*******************************************************//
//  Ethan DeLong & Joseph Boehme                         //
//  CIS 112                                              //
//  Professor Wertz                                      //
//  6 December 2019                                      //
//                                                       //
//  Mappable.java                                        //
//                                                       //
//  Abstract class used to define Mappable objects.      //
//                                                       //
//*******************************************************//

/**
 * Abstract class for mappable objects to inherit.
 */
public abstract class Mappable
{
    /**
     * Empty initializer since objects don't have to be instantiated with an input array.
     */
    public Mappable()
    {
    }

    /**
     * Constructs the object with an input array of values to map.
     *
     * @param  input    A String array of columns to map to this object.
     *
     * @see #mapValues(String[])
     */
    public Mappable(String[] input)
    {
        mapValues(input);
    }
    
    /**
     * Gets the Id of the Mappable object.
     *
     * @return The Id of the object.
     */
    public abstract int getId();
    
    /**
     * Maps the input String array to fields on this object.
     *
     * @param  input    A String array of columns to map to this object.
     */
    public abstract void mapValues(String[] input);
}