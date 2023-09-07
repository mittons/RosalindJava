package hamm;

import java.io.*;

/**
 * Created by Axel on 1.9.2016.
 */
public class HammSolution {
    public static String problemId = "hamm";
    public static String inputFileName = "rosalind_hamm.txt";
    public static String outputFileName = "rosalind_hamm_out.txt";


    public static void main(String[] args) {
        char[] inputChars0 = new char[1];
        char[] inputChars1 = new char[1];

        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream("src/" + problemId + "/" + inputFileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            inputChars0 = br.readLine().toCharArray();
            inputChars1 = br.readLine().toCharArray();

            //Close the input stream
            fstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int hammingDist = 0;

        for (int i = 0; i < inputChars0.length; i++) {
            if(inputChars0[i] != inputChars1[i]){
                hammingDist++;
            }
        }

        System.out.println(hammingDist);

        try {
            PrintWriter writer = new PrintWriter("src/" + problemId + "/" + outputFileName, "UTF-8");
            writer.println(hammingDist);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
