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
import java.lang.IndexOutOfBoundsException;
import java.text.ParseException;
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
     * The zip code the Contact lives in.
     */
    private int zipCode;

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
        phoneNumber = null;
        streetName = null;
        city = null;
        state = null;
        zipCode = 0;
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
     * Gets the Id.
     *
     * @return Returns the Id of the Contact.
     */
    public int getZipCode()
    {
        return zipCode;
    }
     
    /**
     * Sets the Zip Code.
     *
     * @param zipCode The Zip Code of the Contact.
     */
    public void setZipCode(int zipCode)
    {
        this.zipCode = zipCode;
    }

    /**
     * Gets the Birth Date.
     *
     * @return Returns the Birth Date of the Contact.
     */
    public Date getBirthDate()
    {
        return birthDate;
    }

    /**
     * Sets the Birth Date.
     *
     * @param birthDate The Birth Date of the Contact, in MM/dd/yyyy format.
     */
    public void setBirthDate(String birthDate) throws ParseException
    {
        this.birthDate = new SimpleDateFormat("MM/dd/yyyy").parse(birthDate);
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
                ", phoneNumber='" + phoneNumber + '\'' +
                ", streetName='" + streetName + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
    
    /**
     * Gets the column of this Contact by index.
     *
     * @param  index    The index of the column to get.
     *
     * @return The value of the column at the given index.
     *
     * @throws IndexOutOfBoundsException    If the specified index is not defined for this object.
     */
    public Object getColumn(int index) throws IndexOutOfBoundsException
    {
        switch(index)
        {
            case 0: return getId();
            case 1: return getFirstName();
            case 2: return getLastName();
            case 3: return getEmailAddress();
            case 4: return getGender();
            case 5: return getPhoneNumber();
            case 6: return getStreetName();
            case 7: return getCity();
            case 8: return getState();
            case 9: return getZipCode();
            case 10: return getBirthDate();
            default: throw new IndexOutOfBoundsException("Column " + index + " does not exist in Contact.");
        }
    }
    
    /**
     * Sets the column of this Contact by index.
     *
     * @param   index   The index of the column to set.
     * @param   value   The input String value of the column to set.
     *
     * @throws IndexOutOfBoundsException    If the specified index is not defined for this object.
     * @throws ParseException               If there was a problem parsing a value.
     */
    public void setColumn(int index, String value) throws IndexOutOfBoundsException, ParseException
    {
        switch(index)
        {
            case 0: setId(Integer.parseInt(value)); break;
            case 1: setFirstName(value); break;
            case 2: setLastName(value); break;
            case 3: setEmailAddress(value); break;
            case 4: setGender(value); break;
            case 5: setPhoneNumber(value); break;
            case 6: setStreetName(value); break;
            case 7: setCity(value); break;
            case 8: setState(value); break;
            case 9: setZipCode(Integer.parseInt(value)); break;
            case 10: setBirthDate(value); break;
            default: throw new IndexOutOfBoundsException("Column " + index + " does not exist in Contact.");
        }
    }
}
