package javainstantleap;
import java.net.*;
import java.io.*;
import java.lang.Object;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Farrukh Azfar
 */


    

public class JavaInstantLeap {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MalformedURLException, IOException {

	URL oracle = new URL("https://www.ietf.org/timezones/data/leap-seconds.list");
	BufferedReader in = new BufferedReader(
	new InputStreamReader(oracle.openStream()));

	String inputLine;
	String lastLine = "";
	String testLine = ""; // always check this line to see that its 36
	int ii = 0;
	while ((inputLine = in.readLine()) != null){
	    if(inputLine.trim().startsWith("#")){
		    //do nothing

	    }
		else{
		    System.out.println(inputLine);
		    lastLine = inputLine;
		    ii++;
		    if(ii==27) {
			testLine = inputLine;
		    }
		}

	}
	
	String [] array1 = lastLine.split("#");
        String [] testArray1 = testLine.split("#");

	// you'll get the first piece of the row in the file on the web ...
        System.out.println("\n");
	System.out.println("Epoch time from 1900 and leap second before array is split again : ");
	System.out.println(array1[0]);
	System.out.println(array1[1]);

	// epoch time in seconds from 1900 (IMPORTANT !!!!!) 
	/// array2 pertains to the lastLine and testarray to the testLine
	String [] array2 = array1[0].split("\\s+");

	Long epochUNIX =0L;
	Long epochUNIXTest = 0L;
	Long offset = 2208988800L;
	epochUNIX = epochUNIX.valueOf(array2[0])-offset;
         System.out.println("\n");
	System.out.println("The epoch time from 1970 (Unix Epoch Time) on January 1 2017:");
	System.out.println(epochUNIX);

	String [] testArray2 = testArray1[0].split("\\s+");
	epochUNIXTest = epochUNIXTest.valueOf(testArray2[0])-offset;
        System.out.println("\n");
	System.out.println("The epoch time from 1970 (Unix Epoch Time) on July 1 2015:");
	System.out.println(epochUNIXTest);

     // now play with the instant class - 
     
     Instant instant = Instant.now();
     
     System.out.println("Print out Epoch seconds of this instant");
     System.out.println(instant.getEpochSecond());

     // need to add nano seconds - need to check if 1 Jul 2015 epoch is correct - 
	in.close();
         
    }
}
    
        
   