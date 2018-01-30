
/**
 * Finds and lists the Youtube links in the URL Source below:
 * "http://www.dukelearntoprogram.com/course2/data/manylinks.html"
 * 
 * @author (Bengi Baykal) 
 * @version (31.01.2018)
 */
import edu.duke.*;
import java.io.*;
import java.util.Scanner;

public class Part4 {
    public String findUrl(URLResource url){
     String result ="";
     int counter = 0;
     String youtube = "youtube.com";
     int Y = youtube.length();
     
     for (String s : url.words()) {
        int S = s.length();
        int first = s.indexOf("youtube.com");
        int quot1 = s.indexOf("\"");
        
        if(first > 0 ){
            result = s.substring(quot1,S);
            counter ++ ;
            System.out.println(counter + ". link : " + result);
        }
            
        } 
        return result;
    }
    public void Test(){
        URLResource url = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        findUrl(url);
    }
  } 
  

