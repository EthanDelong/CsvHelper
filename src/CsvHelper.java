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

import com.opencsv.*;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class CsvHelper
{
   private String fileName = "C:\\Users\\joeyb\\OneDrive\\Documents\\CsvHelper\\MOCK_DATA.csv";
   public Map<Integer,Contact> csvMapper(){
       Map<Integer,Contact> csvMap = new HashMap<>();
       try {
           CSVReader csvReader = new CSVReader(new FileReader(fileName));
           csvReader.readNext();//to burn off top line
           String[] nextLine;
           while((nextLine = csvReader.readNext())!= null){
               Contact newContact = new Contact();
               newContact.setFirstName(nextLine[1]);
               newContact.setLastName(nextLine[2]);
               newContact.setEmailAddress(nextLine[3]);
               newContact.setGender(nextLine[4]);
               newContact.setIpAddress(nextLine[5]);
               newContact.setPhoneNumber(nextLine[6]);
               newContact.setStreetName(nextLine[7]);
               newContact.setCity(nextLine[8]);
               newContact.setState(nextLine[9]);
               newContact.setBirthDate(new SimpleDateFormat("MM/dd/yyyy").parse(nextLine[10]));
               csvMap.put(Integer.parseInt(nextLine[0]),newContact);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       finally {
           return csvMap;
       }
   }
}