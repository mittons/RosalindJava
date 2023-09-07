package subs;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Axel on 1.9.2016.
 */
public class SubsSolutionOld {
    public static String problemId = "subs";
    public static String inputFileName = "rosalind_subs n1.txt";
    public static String outputFileName = "rosalind_subs n1_out.txt";


    public static void main(String[] args) {
        char[] inputTextChars = new char[1];
        char[] inputPatternChars = new char[1];

        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream("src/" + problemId + "/" + inputFileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            inputTextChars = br.readLine().toCharArray();
            inputPatternChars = br.readLine().toCharArray();

            //Close the input stream
            fstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Integer> patternMatchLocs = new ArrayList<>();

        int textLen = inputTextChars.length;
        int patLen = inputPatternChars.length;
        int textIdx = 0;
        int patIdx = 0;
        int[] lspArray = getLspArray(inputPatternChars);

        while (textIdx < textLen){
            if (inputTextChars[textIdx] == inputPatternChars[patIdx]){
                textIdx++;
                patIdx++;
                if (patIdx == patLen){
                    //found pattern
                    patternMatchLocs.add(textIdx - patIdx + 1);

                    patIdx = lspArray[patIdx-1];
                }
            }
            //if (inputPatternChars[patIdx] != inputTextChars[textIdx]){
            else{
                if (patIdx == 0){
                    textIdx++;
                }
                else{
                    patIdx = lspArray[patIdx-1];
                }
            }
        }


        String outputString = "";
        outputString += patternMatchLocs.get(0).toString();

        for (int i = 1; i < patternMatchLocs.size(); i++) {
            outputString += " " + patternMatchLocs.get(i).toString();
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

    public static int[] getLspArray(char[] word){
        int i = 1;
        int currPattLen = 0;
        int wLen = word.length;
        int[] lspArray = new int[wLen];
        for (int j = 0; j < lspArray.length; j++) {
            lspArray[j] = 0;
        }

        while (i<wLen){
            if (word[i] == word[currPattLen]){
                currPattLen++;
                lspArray[i] = currPattLen;
                i++;
            }
            else if (currPattLen != 0){
                currPattLen = lspArray[currPattLen-1];
            }
            else {
                i++;
            }
        }
        return lspArray;
    }
}
