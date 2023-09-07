package dna;

import java.io.*;

/**
 * Created by Axel on 1.9.2016.
 */
public class DnaSolutionOld {
    public static String problemId = "dna";
    public static String inputFileName = "rosalind_dna.txt";
    public static String outputFileName = "rosalind_dna_out.txt";


    public static void main(String[] args) {
        int aCount = 0;
        int cCount = 0;
        int gCount = 0;
        int tCount = 0;

        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream("src/" + problemId + "/" + inputFileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));


            char[] strChars = br.readLine().toCharArray();
            for (char strChar : strChars) {
                if (strChar == 'A') {
                    aCount++;
                }
                else if (strChar == 'C') {
                    cCount++;
                }
                else if (strChar == 'G') {
                    gCount++;
                }
                else if (strChar == 'T') {
                    tCount++;
                }
            }

            //Close the input stream
            fstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
}
