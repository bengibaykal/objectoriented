
/**
 * CSV Parser with 4 methods:
 * listExporters     : This method prints the names of all the countries that have given export items.
 * countryInfo       : Returns export and value info about specific country.
 * numberOfExporters : This method returns the number of countries that export given exportItem.
 * bigExporters      : This method prints the names of countries and their Value amount for
 *                     all countries whose Value (dollars) string is longer than the amount string. 
 * 
 * @bengibaykal
 * @12.02.2018
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesExport {

    public void listExporters(CSVParser parser, String exportOfInterest){
     //for each row in CSV file
     for ( CSVRecord record : parser){
        //look exports
        String export = record.get("Exports");
        if(export.contains(exportOfInterest)){
         String country = record.get("Country");
         System.out.println(country);
        }
        
        }
    
    }
    
    public void whoExportsCoffee(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser, "coffee");
    
    }
    
    public void tester(){
     FileResource fr = new FileResource();
     CSVParser parser = fr.getCSVParser();
     
     System.out.println("start");
     
     System.out.println(countryInfo(parser, "Nauru"));
     listExportersTwoProducts(parser, "gold", "diamonds");
     System.out.println(numberOfExporters(parser, "gold"));
     bigExporters(parser,"$999,999,999,999");
    }
    
    public String countryInfo(CSVParser parser, String country){
        String answer = "";
        for ( CSVRecord record : parser){
        
         String countryName = record.get("Country");
         if(countryName.contains(country)){
          String Exports = record.get("Exports");
          String Value = record.get("Value (dollars)");
          answer = country + ": " + Exports + ":" + Value;
          break;
         }

        }
        return answer;
    }
    
    public void listExportersTwoProducts (CSVParser parser, String exportItem1, String exportItem2){
     String country = "";
        for ( CSVRecord record : parser){
        
         String exportAll = record.get("Exports");
         if(exportAll.contains(exportItem1) && exportAll.contains(exportItem2)){
          country = record.get("Country");
          System.out.println(country);
          
          }
         }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem){
        int country = 0;
         for ( CSVRecord record : parser){
          String exportAll = record.get("Exports");
           if(exportAll.contains(exportItem)){
            country ++;
           }
         }
        return country;
    }
    
    public void bigExporters(CSVParser parser, String amount){
     
     for ( CSVRecord record : parser){
          String value = record.get("Value (dollars)");
          String country = record.get("Country");
           if(value.length() > amount.length()){
            System.out.println(country + " " + value);
           }
         }
     
    }
}

