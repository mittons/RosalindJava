package grph;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Axel on 10.9.2016.
 */
public class GrphSolution {
    public static String problemId = "grph";
    public static String inputFileName = "rosalind_grph.txt";
    public static String outputFileName = "rosalind_grph_out.txt";

    public static void main(String[] args) {
        HashMap<String, String> inputStrings = new HashMap<>();

        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream("src/" + problemId + "/" + inputFileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;

            boolean first = true;
            String currentString = "";
            String currentStringLabel = "";

            //Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                if (strLine.startsWith(">")){
                    if (first){
                        first = false;
                    }
                    else {
                        inputStrings.put(currentStringLabel, currentString);
                        currentString = "";
                    }
                    currentStringLabel = strLine.substring(1);
                }
                else{
                    currentString += strLine;
                }
            }
            inputStrings.put(currentStringLabel, currentString);

            //Close the input stream
            fstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String outputString = "";


        HashMap<String, String> inputStringPrefixes = getInputStringPrefixesMap(inputStrings);

        for (String inputStringLabel : inputStrings.keySet()) {
            String inputString = inputStrings.get(inputStringLabel);
            int len = inputString.length();
            String inputStringSuffix = inputString.substring(len-3, len);

            for (String prefixLabel : inputStringPrefixes.keySet()) {
                if (inputStringLabel.equals(prefixLabel)) continue;

                String stringPrefix = inputStringPrefixes.get(prefixLabel);
                if (inputStringSuffix.equals(stringPrefix)){
                    if (outputString.length() != 0) outputString += "\n";
                    outputString +=inputStringLabel + " " + prefixLabel;
                }
            }
        }



        System.out.println(outputString);

        try {
            PrintWriter writer = new PrintWriter("src/" + problemId + "/" + outputFileName, "UTF-8");
            writer.println(outputString);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HashMap<String, String> getInputStringPrefixesMap(HashMap<String, String> inputStrings){
        HashMap<String, String> inputStringPrefixes = new HashMap<>();

        for (String stringLabel : inputStrings.keySet()) {
            String inputString = inputStrings.get(stringLabel);
            inputStringPrefixes.put(stringLabel, inputString.substring(0, 3));
        }

        return inputStringPrefixes;
    }
}
