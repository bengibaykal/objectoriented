
/**
 * Finds the genes in the sample DNA examples according to the rules.
 *1. Gene should start with the codon "ATG"
 *2. Gene should end with the codon "TAA"
 *3. Gene should contains the number nucleotides between the two codons which gives result zero with mode 3.
 * 
 * @author (Bengi Baykal) 
 * @version (31.01.2018)
 */
import edu.duke.*;
import java.io.*;
import java.util.Scanner;
public class Part2 {
    public String findGeneInDna (String dna, String start, String stop){
            String result = "";
            int startIndex = dna.indexOf(start);
            if (startIndex == -1 ){
                return "";
        }
        int stopIndex = dna.indexOf(stop, startIndex+3);
        if (stopIndex == -1 ){
            return "";
        }
        
        int length = (startIndex + 3 - stopIndex);
        if (length % 3 == 0){
            result = dna.substring(startIndex , stopIndex +3); 
        } else {
            return "";
        }
        char ch = dna.charAt(1);
        if (Character.isUpperCase(ch) == true ) {
            result = result.toUpperCase();
        }    
        if (Character.isLowerCase(ch) == true ) {
            result = result.toLowerCase();
        }
        
        return result ;
    }
    
    public void testGeneInDna (){
        String a = "AAAAATGBBBBBBBBBTAATTTT";
        System.out.println(a + " the gene = " + findSimpleGene(a,"ATG","TAA"));
        
        String b = "ATGATGATGATGATAGATGATAG";
        System.out.println(b + " the gene = " + findSimpleGene(b,"ATG","TAA"));
        
        String c = "GGGGGGGGGGGGGGGTAA";
        System.out.println(c + " the gene = " + findSimpleGene(c,"ATG","TAA"));
        
        String d = "AAAATGTTTTTTTTTTAAGAGAGA";
        System.out.println(d + " the gene = " + findSimpleGene(d,"ATG","TAA"));
        
        String e = "aaatgbbbbbbbbbtaagatgatgatg";
        System.out.println(e + " the gene = " + findSimpleGene(e,"atg","taa"));
        
    }
    
}

