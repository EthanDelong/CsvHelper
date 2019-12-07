//*******************************************************//
//  Ethan DeLong & Joseph Boehme                         //
//  CIS 112                                              //
//  Professor Wertz                                      //
//  6 December 2019                                      //
//                                                       //
//  Contact.java                                         //
//                                                       //
//  Contact class that we will use to map the data from  //
//  the csv.                                             //
//                                                       //
//*******************************************************//

import java.lang.IllegalArgumentException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 * Contact object which can be mapped from a set of input columns.
 * <p>
 * This map is based on column index, and the format must be as follows:
 * <p>
 * Columns: <code>id,first_name,last_name,email,gender,ip_address,phone_number,street,city,state,birthday</code>
 * <p>
 * Types: <code>int,String,String,String,String,String,String,String,String,String,Date[MM/dd/yyyy]</code>
 */
public class Contact extends Mappable
{
    /**
     * The unique identifier for this Contact.
     */
    private int id;

    /**
     * The first name of the Contact.
     */
    private String firstName;

    /**
     * The last name of the contact.
     */
    private String lastName;

    /**
     * The email address of the Contact.
     */
    private String emailAddress;

    /**
     * The gender of the Contact.
     */
    private String gender;

    /**
     * The IP Address of the Contact.
     */
    private String ipAddress;

    /**
     * The phone number of the Contact.
     */
    private String phoneNumber;

    /**
     * The street name where the Contact lives.
     */
    private String streetName;

    /**
     * The city the Contact lives in.
     */
    private String city;

    /**
     * The state the Contact lives in.
     */
    private String state;

    /**
     * The Contact's birth date. Input is in MM/dd/yyyy format.
     */
    private Date birthDate;

    /**
     * Instantiates a new Contact with default values.
     */
    public Contact()
    {
        id = 0;
        firstName = null;
        lastName = null;
        emailAddress = null;
        gender = null;
        ipAddress = null;
        phoneNumber = null;
        streetName = null;
        city = null;
        state = null;
        birthDate = null;
    }

    /**
     * Instantiates a new Contact from the given input values.
     *
     * @param input The input String array of values to map.
     */
    public Contact(String[] input)
    {
        super(input);
    }
    
    /**
     * Gets the Id.
     *
     * @return Returns the Id of the Contact.
     */
    public int getId()
    {
        return id;
    }
     
    /**
     * Sets the Id.
     *
     * @param id The Id of the Contact.
     */
    public void setId(int id)
    {
        this.id = id;
    }
    
    /**
     * Gets the FirstName.
     *
     * @return Returns the FirstName of the Contact.
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * Sets the FirstName.
     *
     * @param firstName The FirstName of the Contact.
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /**
     * Gets the LastName.
     *
     * @return Returns the LastName of the Contact.
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * Sets the LastName.
     *
     * @param lastName The LastName of the Contact.
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    /**
     * Gets the EmailAddress.
     *
     * @return Returns the EmailAddress of the Contact.
     */
    public String getEmailAddress()
    {
        return emailAddress;
    }

    /**
     * Sets the EmailAddress.
     *
     * @param emailAddress The EmailAddress of the Contact.
     */
    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    /**
     * Gets the Gender.
     *
     * @return Returns the Gender of the Contact.
     */
    public String getGender()
    {
        return gender;
    }

    /**
     * Sets the Gender.
     *
     * @param gender The Gender of the Contact.
     */
    public void setGender(String gender)
    {
        this.gender = gender;
    }

    /**
     * Gets the IpAddress.
     *
     * @return Returns the IpAddress of the Contact.
     */
    public String getIpAddress()
    {
        return ipAddress;
    }

    /**
     * Sets the IpAddress.
     *
     * @param ipAddress The IpAddress of the Contact.
     */
    public void setIpAddress(String ipAddress)
    {
        this.ipAddress = ipAddress;
    }

    /**
     * Gets the PhoneNumber.
     *
     * @return Returns the PhoneNumber of the Contact.
     */
    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    /**
     * Sets the PhoneNumber.
     *
     * @param phoneNumber The PhoneNumber of the Contact.
     */
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the StreetName.
     *
     * @return Returns the StreetName of the Contact.
     */
    public String getStreetName()
    {
        return streetName;
    }

    /**
     * Sets the StreetName.
     *
     * @param streetName The StreetName of the Contact.
     */
    public void setStreetName(String streetName)
    {
        this.streetName = streetName;
    }

    /**
     * Gets the City.
     *
     * @return Returns the City of the Contact.
     */
    public String getCity()
    {
        return city;
    }

    /**
     * Sets the City.
     *
     * @param city The City of the Contact.
     */
    public void setCity(String city)
    {
        this.city = city;
    }

    /**
     * Gets the State.
     *
     * @return Returns the State of the Contact.
     */
    public String getState()
    {
        return state;
    }

    /**
     * Sets the State.
     *
     * @param state The State of the Contact.
     */
    public void setState(String state)
    {
        this.state = state;
    }

    /**
     * Gets the BirthDate.
     *
     * @return Returns the BirthDate of the Contact.
     */
    public Date getBirthDate()
    {
        return birthDate;
    }

    /**
     * Sets the BirthDate.
     *
     * @param birthDate The BirthDate of the Contact.
     */
    public void setBirthDate(Date birthDate)
    {
        this.birthDate = birthDate;
    }

    /**
     * Gets the details of the Contact as a String.
     *
     * @return Returns the details of the Contact as a String of the Contact.
     */
    @Override
    public String toString()
    {
        return "Contact{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", gender='" + gender + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", streetName='" + streetName + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }

    /**
     * Maps the values of the given input to the Contact.
     *
     * @param  input    A String array of columns to map to the Contact.
     *
     * @throws IllegalArgumentException    If the input was not in the correct format.
     */
    public void mapValues(String[] input) throws IllegalArgumentException
    {
        try
        {
            setId(Integer.parseInt(input[0]));
            setFirstName(input[1]);
            setLastName(input[2]);
            setEmailAddress(input[3]);
            setGender(input[4]);
            setIpAddress(input[5]);
            setPhoneNumber(input[6]);
            setStreetName(input[7]);
            setCity(input[8]);
            setState(input[9]);
            setBirthDate(new SimpleDateFormat("MM/dd/yyyy").parse(input[10]));
        }
        catch(Exception e)
        {
            throw new IllegalArgumentException("Failed to parse input", e);
        }
    }
}
