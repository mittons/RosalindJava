package splc;

import lcsm.LcsmSolution;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Axel on 10.9.2016.
 */
public class SplcSolution {
    public static String problemId = "splc";
    public static String inputFileName = "SampleInput.txt";
    public static String outputFileName = "SampleOutput.txt";

    public static void main(String[] args) {
        List<String> inputStrings = new ArrayList<>();

        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream("src/" + problemId + "/" + inputFileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;

            boolean first = true;
            String currentString = "";

            //Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                if (strLine.startsWith(">")){
                    if (first){
                        first = false;
                    }
                    else {
                        inputStrings.add(currentString);
                        currentString = "";
                    }
                }
                else{
                    currentString += strLine;
                }
            }
            inputStrings.add(currentString);

            //Close the input stream
            fstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String dnaString = inputStrings.get(0);
        List<String> intronStrings = inputStrings;
        intronStrings.remove(0);

        //check sample input for locations where the intron strings appear (use the subs solution?)


        String outputString = "";

        System.out.println(outputString);
//
//        try {
//            PrintWriter writer = new PrintWriter("src/" + problemId + "/" + outputFileName, "UTF-8");
//            writer.println(outputString);
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
