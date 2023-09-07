package dna;

import java.io.*;

/**
 * Created by Axel on 1.9.2016.
 */
public class DnaSolution {
    public static String problemId = "dna";
    public static String inputFileName = "rosalind_dna.txt";
    public static String outputFileName = "rosalind_dna_out.txt";


    public static void main(String[] args) {
        String inputDnaString = "";

        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream("src/" + problemId + "/" + inputFileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            inputDnaString = br.readLine();

            //Close the input stream
            fstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



        int[] baseCounts = getBaseCounts(inputDnaString);
        int aCount = baseCounts[0];
        int cCount = baseCounts[1];
        int gCount = baseCounts[2];
        int tCount = baseCounts[3];



        String outputString = String.format("%d %d %d %d", aCount, cCount, gCount, tCount);
        System.out.println(outputString);
        try {
            PrintWriter writer = new PrintWriter("src/" + problemId + "/" + outputFileName, "UTF-8");
            writer.println(outputString);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Returns an integer array containing the number of occurences in the input dnaString for each base type
    //The first element is the number of occurences of A, the second is for C, the third for G, and the fourth for T
    public static int[] getBaseCounts(String dnaString) {
        int[] baseCounts = new int[4];

        char[] strChars = dnaString.toCharArray();
        for (char strChar : strChars) {
            if (strChar == 'A') {
                baseCounts[0]++;
            }
            else if (strChar == 'C') {
                baseCounts[1]++;
            }
            else if (strChar == 'G') {
                baseCounts[2]++;
            }
            else if (strChar == 'T') {
                baseCounts[3]++;
            }
        }
        return baseCounts;
    }
}




