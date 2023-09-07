package fibd;

import java.io.*;

/**
 * Created by Axel on 1.9.2016.
 */
public class FibdSolutionLong {
    public static String problemId = "fibd";
    public static String inputFileName = "SampleInput.txt";
    public static String outputFileName = "SampleOutput.txt";


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

        long bornThisMonth = 1;
        long bornLastMonth = 0;
        long dieAfterThisMonth = 0;
        long currBreedingPairs = 0;

        int deathCycleIdx = 0;
//        int[] deathCycleData = new int[]{1, 0, 0};
        long[] deathCycleData = new long[m];
        for (int i = 0; i < m; i++){
            deathCycleData[i] = 0;
        }
        deathCycleData[0] = 1;


        for (int i = 1; i < n; i++)
        {
            currBreedingPairs += bornLastMonth;
            bornLastMonth = bornThisMonth;
            deathCycleIdx = (deathCycleIdx + 1) % m;
            dieAfterThisMonth = deathCycleData[deathCycleIdx];
            bornThisMonth = currBreedingPairs;

            deathCycleData[deathCycleIdx] = bornThisMonth;
            currBreedingPairs -= dieAfterThisMonth;
        }


        String outputString = Long.toString(bornThisMonth + bornLastMonth + currBreedingPairs);
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
