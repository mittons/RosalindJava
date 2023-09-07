package prob;

import java.io.*;
import java.util.HashMap;

/**
 * Created by Axel on 10.9.2016.
 */
public class ProbSolution {
    public static String problemId = "prob";
    public static String inputFileName = "SampleInput.txt";
    public static String outputFileName = "SampleOutput.txt";


    public static void main(String[] args) {
        String inputDnaString = "";
        String[] inputArrayOfDoubles = new String[1];
        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream("src/" + problemId + "/" + inputFileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            inputDnaString = br.readLine();
            inputArrayOfDoubles = br.readLine().split(" ");

            //Close the input stream
            fstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String outputString = "";

        System.out.println(outputString);

//        try {
//            PrintWriter writer = new PrintWriter("src/" + problemId + "/" + outputFileName, "UTF-8");
//            writer.println(outputString);
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
