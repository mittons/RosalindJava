package revp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Axel on 10.9.2016.
 */
public class RevpSolution {
    public static String problemId = "revp";
    public static String inputFileName = "SampleInput.txt";
    public static String outputFileName = "SampleOutput.txt";

    public static void main(String[] args) {
        String inputDnaString = "";

        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream("src/" + problemId + "/" + inputFileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;

            //First line contains the label of the string. We dont need the label so we ignore the first line.
            br.readLine();

            while ((strLine = br.readLine()) != null) {
                inputDnaString += strLine;
            }

            //Close the input stream
            fstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Generate the complement of the dna string (note: not the reverse complement)
        //Iterarate over each pair of adjacent characters of the the dna string
            //If the complement string contains
                //the first character in the pair is in the complementary string at second characters index
                //and the second character in the pair is in the complementary string at first characters index
                    //mark the indices of the two characters in a list of tuples called possibleRevPalindromeCenters
        String outputString = "";

        System.out.println(outputString);

//        try {
//            PrintWriter writer = new PrintWriter("src/" + problemId + "/" + outputFileName, "UTF-8");
//            writer.println(outputString);
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
