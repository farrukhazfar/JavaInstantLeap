package javainstantleap;

import java.net.*;
import java.io.*;
import java.lang.Object;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author Farrukh Azfar
 */
public class JavaInstantLeap {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static BufferedReader readFromLocalFile() throws FileNotFoundException {
        FileReader fr = new FileReader("/home/azfarl/NetBeansProjects/JavaInstantLeap/src/javainstantleap/textleapsecond.list");
        BufferedReader buffer = new BufferedReader(fr);
        return buffer;
    }

    public static BufferedReader readFromWebFile() throws MalformedURLException, IOException {
        URL oracle = new URL("https://www.ietf.org/timezones/data/leap-seconds.list");
        BufferedReader buffer = new BufferedReader(
                new InputStreamReader(oracle.openStream()));
        return buffer;
    }

    public static void main(String[] args) throws MalformedURLException, IOException {

        /*
        URL oracle = new URL("https://www.ietf.org/timezones/data/leap-seconds.list");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(oracle.openStream()));
*/
        //BufferedReader in = readFromWebFile();
        BufferedReader in = readFromLocalFile();
        
        List<String> stringList = new ArrayList<String>();

        String inputLine;
        String lastLine = "";
        
        int ii = 0;

        while ((inputLine = in.readLine()) != null) {
            if (inputLine.trim().startsWith("#")) {
                //do nothing

            } else {
                System.out.println(inputLine);
                lastLine = inputLine;
                stringList.add(inputLine);

            }

        }

        String[] breakLast = stringList.get(stringList.size() - 1).split("#");
        String[] testString = stringList.get(26).split("#");

        // you'll get the first piece of the row in the file on the web ...
        System.out.println("\n");
        System.out.println("Epoch time from 1900 and leap second before array is split again : ");
        System.out.println(breakLast[0]);
        System.out.println();

        // epoch time in seconds from 1900 (IMPORTANT !!!!!) 
        /// array2 pertains to the lastLine and testarray to the testLine
        String[] breakLastAgain = breakLast[0].split("\\s+");

        Long epochUNIX = 0L;
        Long epochUNIXTest = 0L;
        Long offset = 2208988800L;

        epochUNIX = epochUNIX.valueOf(breakLastAgain[0]) - offset;
        System.out.println("\n");
        System.out.println("The epoch time from 1970 (Unix Epoch Time) on January 1 2017 should be 1483228800:");
        System.out.println(epochUNIX);

        String[] brokenTestString = testString[0].split("\\s+");
        epochUNIXTest = epochUNIXTest.valueOf(brokenTestString[0]) - offset;
        System.out.println("\n");
        System.out.println("The epoch time from 1970 (Unix Epoch Time) on July 1 2015 array element 27 should be  1435708800 :");
        System.out.println(epochUNIXTest);

        // now play with the instant class - 
        Instant instant = Instant.now();
        System.out.println("\n");

        System.out.println("Print out Epoch seconds of this instant");
        System.out.println(instant.getEpochSecond());
        System.out.println("\n");

        // need to add nano seconds - need to check if 1 Jul 2015 epoch is correct - 
        in.close();

    }
}
