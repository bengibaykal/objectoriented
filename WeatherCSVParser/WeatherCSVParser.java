
/**
 * Calculates weather information from CSV files.
 * 
 * @author (bengibaykal) 
 * @version (13.02.2018)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;


public class CSVMin {
    public CSVRecord coldestHourInFile(CSVParser parser){
     CSVRecord smallestSoFar = null;
     for(CSVRecord currentRow : parser){
        smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);
     }
     return smallestSoFar;
    }
    //works well
    public void testColdestInDay () {
      FileResource fr = new FileResource();
      CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
      System.out.println("coldest temperature was " + smallest.get("TemperatureF") +
                   " at " + smallest.get("TimeEDT"));
	}
	//works well
     public String fileWithColdestTemperature(){
        String coldestFileName = "";
         CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
         FileResource fr = new FileResource(f);
         CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
         smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);
         coldestFileName = f.getName();
        }
        
        
        return coldestFileName;
    }
     public void testFileWithColdestTemperature() {
		System.out.println(fileWithColdestTemperature());
		FileResource fr = new FileResource();
		CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
		System.out.println("Coldest day was in file "+ fileWithColdestTemperature());
		System.out.println("Coldest temperature on that day was " + smallest.get("TemperatureF"));
		System.out.println("All the Temperature on the coldest day were:");
		
		for (CSVRecord currentRow : fr.getCSVParser()) {
            // use method to compare two records
            System.out.println(currentRow.get("DateUTC") + ": " + currentRow.get("TemperatureF") );;
        }
	}
    
    public CSVRecord getSmallestHumidityOfTwo (CSVRecord currentRow, CSVRecord smallestSoFar) {
		//If smallestSoFar is nothing
		if (smallestSoFar == null) {
			smallestSoFar = currentRow;
		}
		//Otherwise
		else {
		    if (currentRow.get("Humidity").length() != 3){
			    double currentTemp = Double.parseDouble(currentRow.get("Humidity"));
			    double smallestTemp = Double.parseDouble(smallestSoFar.get("Humidity"));
			    //Check if currentRow’s temperature < smallestSoFar’s
			    if (currentTemp < smallestTemp && currentTemp != -9999) {
				    //If so update smallestSoFar to currentRow
				    smallestSoFar = currentRow;
			    }
		    }
        }
		return smallestSoFar;
     }
     
     public CSVRecord lowestHumidityInFile(CSVParser parser) {
		//start with lowestSoFar as nothing
		CSVRecord lowestSoFar = null;
		//For each row (currentRow) in the CSV File
		for (CSVRecord currentRow : parser) {
			// use method to compare two records
			lowestSoFar = getSmallestHumidityOfTwo(currentRow, lowestSoFar);
		}
		//The smallestSoFar is the answer
		return lowestSoFar;
	}
	public double averageTemperatureInFile(CSVParser parser){
		double sum=0;
		double avarage =0;
		int count = 1;
		for (CSVRecord currentRow : parser) {
			double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
			sum += currentTemp;
			avarage =sum/count;
			count++;
		}
		return avarage;
	}
	public void  testAverageTemperatureInFile() {
	    FileResource fr = new FileResource();
            CSVParser parser = fr.getCSVParser();
	    double avarage = averageTemperatureInFile(parser);
	    System.out.println("Average temperature in file is " + avarage);
	}
	
	public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value ){
        
        double sum=0;
	    double avarage =0;
	    int count = 1;
	    for (CSVRecord currentRow : parser) {
            // use method to compare two records
            double currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            if(currentHumidity>=value){
                sum += currentTemp;
                avarage =sum/count;
                count++;
            }
        }
	   
        return avarage;
       }
       
        public  void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
	    double avarage = averageTemperatureWithHighHumidityInFile(parser,80);
	    if(avarage==0){
	    System.out.println("No temperatures with that humidity");
	   }
	    else {
	        System.out.println("Average temperature when high Humidity is " + avarage);
	       }
       }
       
    // demo
	public void main(String[] args) {
			//testColdestHourInFile();
			//testFileWithColdestTemperature();
			//testlowestHumidityInFile();
			//testLowestHumidityInManyFiles();
			//testAverageTemperatureInFile();
			//testAverageTemperatureWithHighHumidityInFile();
		}
       
       
     public void testlowestHumidityInFile() {
		FileResource fr = new FileResource();
		CSVRecord smallest = lowestHumidityInFile(fr.getCSVParser());
		System.out.println("Lowest humidity was " + smallest.get("Humidity") +
				   " at " + smallest.get("DateUTC"));
	}
     public CSVRecord lowestHumidityInManyFiles() {
		CSVRecord lowestSoFar = null;
		DirectoryResource dr = new DirectoryResource();
		// iterate over files
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			// use method to get largest in file.
			CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
			// use method to compare two records
			lowestSoFar = getSmallestHumidityOfTwo(currentRow, lowestSoFar);
		}
		//The largestSoFar is the answer
		return lowestSoFar;
	}
      public void testLowestHumidityInManyFiles() {
		CSVRecord csv = lowestHumidityInManyFiles();
		System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
	 }
     public CSVRecord coldestInManyDays(){
         CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
         FileResource fr = new FileResource(f);
         CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
         smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);
        }
        return smallestSoFar;
    }
    
    public CSVRecord getSmallestOfTwo (CSVRecord currentRow, CSVRecord smallestSoFar ){
        if (smallestSoFar == null){
            smallestSoFar = currentRow;
            }
          else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
            if (currentTemp < smallestTemp && currentTemp != -9999 ){
            smallestSoFar = currentRow;
            }
          }
          return smallestSoFar;
    }
    
    
    
    public void testColdestInManyDays(){
        CSVRecord smallest = coldestInManyDays();
        System.out.println("coldest temperature was " + smallest.get("TemperatureF") +
				   " at " + smallest.get("DateUTC"));
    }
}
