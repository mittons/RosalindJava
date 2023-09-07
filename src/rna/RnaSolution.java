package rna;

import java.io.*;

/**
 * Created by Axel on 1.9.2016.
 */
public class RnaSolution {
    public static String problemId = "rna";
    public static String inputFileName = "rosalind_rna.txt";
    public static String outputFileName = "rosalind_rna_out.txt";


    public static void main(String[] args) {
        String outputString = "";

        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream("src/" + problemId + "/" + inputFileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            String strLine = br.readLine();
            outputString = strLine.replace('T', 'U');

            //Close the input stream
            fstream.close();
        } catch (IOException e) {
            e.printStackTrace();
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
}
