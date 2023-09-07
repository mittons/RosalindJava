package fibd;

import java.io.*;
import java.math.BigInteger;

/**
 * Created by Axel on 1.9.2016.
 */
public class FibdSolution {
    public static String problemId = "fibd";
    public static String inputFileName = "rosalind_fibd_n1.txt";
    public static String outputFileName = "rosalind_fibd_n1_out.txt";


    public static void main(String[] args) {
        //months to simulate
        int n = 0;
        //rabbit lifetime
        int m = 0;

        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream("src/" + problemId + "/" + inputFileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            String[] strInts = br.readLine().split(" ");
            n = Integer.parseInt(strInts[0]);
            m = Integer.parseInt(strInts[1]);

            //Close the input stream
            fstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



        BigInteger.valueOf(0);
        BigInteger.valueOf(1);

        BigInteger bornThisMonth = BigInteger.valueOf(1);
        BigInteger bornLastMonth = BigInteger.valueOf(0);
        BigInteger dieAfterThisMonth = BigInteger.valueOf(0);
        BigInteger currBreedingPairs = BigInteger.valueOf(0);

        int deathCycleIdx = 0;
//        int[] deathCycleData = new int[]{1, 0, 0};
        BigInteger[] deathCycleData = new BigInteger[m];
        for (int i = 0; i < m; i++){
            deathCycleData[i] = BigInteger.valueOf(0);
        }
        deathCycleData[0] = BigInteger.valueOf(1);


        for (int i = 1; i < n; i++)
        {
            currBreedingPairs = currBreedingPairs.add(bornLastMonth);
            bornLastMonth = bornThisMonth;
            deathCycleIdx = (deathCycleIdx + 1) % m;
            dieAfterThisMonth = deathCycleData[deathCycleIdx];
            bornThisMonth = currBreedingPairs;

            deathCycleData[deathCycleIdx] = bornThisMonth;
            currBreedingPairs = currBreedingPairs.subtract(dieAfterThisMonth);
        }




        BigInteger totalPop = currBreedingPairs.add(bornLastMonth.add(bornThisMonth));

        String outputString = totalPop.toString();
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
