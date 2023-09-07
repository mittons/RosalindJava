package iev;

import java.io.*;

/**
 * Created by Axel on 10.9.2016.
 */
public class IevSolution {
    public static String problemId = "iev";
    public static String inputFileName = "rosalind_iev.txt";
    public static String outputFileName = "rosalind_iev_out.txt";


    public static void main(String[] args) {
        String[] strInts = new String[6];

        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream("src/" + problemId + "/" + inputFileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            strInts = br.readLine().split(" ");

            //Close the input stream
            fstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        double expectedDominantOffspring = 0;
        expectedDominantOffspring += 2*Integer.parseInt(strInts[0]);
        expectedDominantOffspring += 2*Integer.parseInt(strInts[1]);
        expectedDominantOffspring += 2*Integer.parseInt(strInts[2]);
        expectedDominantOffspring += 2*0.75*Integer.parseInt(strInts[3]);
        expectedDominantOffspring += 2*0.5*Integer.parseInt(strInts[4]);

        String outputString = Double.toString(expectedDominantOffspring);

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
