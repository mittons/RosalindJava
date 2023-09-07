package revc;

import java.io.*;

/**
 * Created by Axel on 1.9.2016.
 */
public class RevcSolution {
    public static String problemId = "revc";
    public static String inputFileName = "rosalind_revc.txt";
    public static String outputFileName = "rosalind_revc_out.txt";


    public static void main(String[] args) {
        String outputString = "";

        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream("src/" + problemId + "/" + inputFileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            char[] strChars = br.readLine().toCharArray();

            for (int i = strChars.length - 1; i >= 0; i--) {
                char strChar = strChars[i];
                if (strChar == 'A') {
                    outputString += 'T';
                }
                else if (strChar == 'C') {
                    outputString += 'G';
                }
                else if (strChar == 'G') {
                    outputString += 'C';
                }
                else if (strChar == 'T') {
                    outputString += 'A';
                }
            }

            //outputString = strLine.replace('T', 'U');

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
