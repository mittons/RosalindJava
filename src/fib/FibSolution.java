package fib;

import java.io.*;
import java.util.List;

/**
 * Created by Axel on 1.9.2016.
 */
public class FibSolution {
    public static String problemId = "fib";
    public static String inputFileName = "rosalind_fib.txt";
    public static String outputFileName = "rosalind_fib_out.txt";


    public static void main(String[] args) {
        int n = 0;
        int k = 0;

        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream("src/" + problemId + "/" + inputFileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            String[] strInts = br.readLine().split(" ");
            n = Integer.parseInt(strInts[0]);
            k = Integer.parseInt(strInts[1]);

            //Close the input stream
            fstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        long fibA = 0;
        long fibB = 1;

        for (int i = 1; i < n; i++) {
            long tempFibB = fibB;
            fibB += fibA*k;
            fibA = tempFibB;
        }

        System.out.println(fibB);

        try {
            PrintWriter writer = new PrintWriter("src/" + problemId + "/" + outputFileName, "UTF-8");
            writer.println(fibB);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
