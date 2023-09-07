package cons;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Axel on 3.9.2016.
 */
public class ConsSolution {
    public static String problemId = "cons";
    public static String inputFileName = "rosalind_cons.txt";
    public static String outputFileName = "rosalind_cons_out.txt";

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

        int[][] profileMatrix = computeProfileMatrix(inputStrings);

        String aString = "A:";
        String cString = "C:";
        String gString = "G:";
        String tString = "T:";
        for (int i = 0; i < profileMatrix.length; i++) {
            int[] baseCounts = profileMatrix[i];
            aString += " " + Integer.toString(baseCounts[0]);
            cString += " " + Integer.toString(baseCounts[1]);
            gString += " " + Integer.toString(baseCounts[2]);
            tString += " " + Integer.toString(baseCounts[3]);
        }

        String outputString = computeConcensusString(profileMatrix)
                + "\n" + aString
                + "\n" + cString
                + "\n" + gString
                + "\n" + tString;

        System.out.println(outputString);

        try {
            PrintWriter writer = new PrintWriter("src/" + problemId + "/" + outputFileName, "UTF-8");
            writer.println(outputString);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Computes the profile matrix, int[i][j], of a list of DNA strings.
    //The elements in the array are integers representing how many times a given base appears at a specific position in the DNA strings.
    //i indexes the position in the strings the element represents(i-th position in the strings)
    //j indexes which base the element is counting (j == 0 for A, j == 1 for C, j == 2 for G and j == 3 for T)
    public static int[][] computeProfileMatrix(List<String> dnaStrings){
        int maxI = dnaStrings.get(0).length();

        int[][] profileMatrix = new int[maxI][4];

        for (String dnaString : dnaStrings) {
            for (int i = 0; i < dnaString.length(); i++) {
                char base = dnaString.charAt(i);
                switch (base){
                    case 'A':
                        profileMatrix[i][0]++;
                        break;
                    case 'C':
                        profileMatrix[i][1]++;
                        break;
                    case 'G':
                        profileMatrix[i][2]++;
                        break;
                    case 'T':
                        profileMatrix[i][3]++;
                        break;
                    default:
                        System.out.println("Unexpected character in the DNA strings while computing the profile matrix." + "\nThe unexpected character was: " + base + "\nASCII value of the unexpected character: " + Integer.toString((int)base));
                        System.exit(1);
                }
            }
        }
        return profileMatrix;
    }

    public static String computeConcensusString(int[][] profileMatrix){
        String concensusString = "";

        for (int i = 0; i < profileMatrix.length; i++) {
            int[] baseCounts = profileMatrix[i];

            char maxOccurBase = 'A';
            int maxOccurBaseCount = baseCounts[0];

            if (maxOccurBaseCount < baseCounts[1]){
                maxOccurBase = 'C';
                maxOccurBaseCount = baseCounts[1];
            }
            if (maxOccurBaseCount < baseCounts[2]){
                maxOccurBase = 'G';
                maxOccurBaseCount = baseCounts[2];
            }
            if (maxOccurBaseCount < baseCounts[3]){
                maxOccurBase = 'T';
            }
            concensusString += maxOccurBase;
        }
        return  concensusString;
    }
}
