package iprb;

import java.io.*;
import java.util.Locale;

/**
 * Created by Axel on 1.9.2016.
 */
public class IprbSolution {
    public static String problemId = "iprb";
    public static String inputFileName = "rosalind_iprb_n2.txt";
    public static String outputFileName = "rosalind_iprb_n2_out.txt";


    public static void main(String[] args) {
        double k = 0;
        double m = 0;
        double n = 0;

        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream("src/" + problemId + "/" + inputFileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            String[] intputInts = br.readLine().split(" ");
            k = (double)Integer.parseInt(intputInts[0]);
            m = (double)Integer.parseInt(intputInts[1]);
            n = (double)Integer.parseInt(intputInts[2]);

            //Close the input stream
            fstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        double answer = 0;

        double total = k + m + n;

        //k + (any)
        double kFirstProb = k/total;
        answer += kFirstProb;

        //m + k
        double mkProb = m/total * k/(total - 1);
        answer += mkProb;

        //n + k
        double nkProb = n/total * k/(total - 1);
        answer += nkProb;

        //m + m
        if (m > 1) {
            double mmProb = m/total * (m - 1)/(total - 1);
            double mmDomProb = mmProb*0.75;
            answer += mmDomProb;
        }

        //m + n
        double mnProb = m/total * n/(total - 1);
        double mnDomProb = mnProb*0.50;
        answer += mnDomProb;

        //n + m
        double nmProb = n/total * m/(total - 1);
        double nmDomProb = nmProb*0.50;
        answer += nmDomProb;

        String outputString = String.format(Locale.ROOT, "%.15f", answer);

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
