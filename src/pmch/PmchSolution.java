package pmch;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Axel on 10.9.2016.
 */
public class PmchSolution {
    public static String problemId = "pmch";
    public static String inputFileName = "rosalind_pmch.txt";
    public static String outputFileName = "rosalind_pmch_out.txt";

    public static void main(String[] args) {
        String inputRnaString = "";

        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream("src/" + problemId + "/" + inputFileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;

            //First line contains the label of the string. We dont need the label so we ignore the first line.
            br.readLine();

            while ((strLine = br.readLine()) != null) {
                inputRnaString += strLine;
            }

            //Close the input stream
            fstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int[] baseCounts = getBaseCounts(inputRnaString);
        int aCount = baseCounts[0];
        int cCount = baseCounts[1];
        BigInteger aCountFactorial = getFactorial(aCount);
        BigInteger cCountFactorial = getFactorial(cCount);
        BigInteger totalPossiblePerfectMatchings = aCountFactorial.multiply(cCountFactorial);


        String outputString = totalPossiblePerfectMatchings.toString();
        System.out.println(outputString);

        try {
            PrintWriter writer = new PrintWriter("src/" + problemId + "/" + outputFileName, "UTF-8");
            writer.println(outputString);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Returns an integer array containing the number of occurences in the input rnaString for each base type
    //The first element is the number of occurences of A, the second is for C, the third for G, and the fourth for U
    public static int[] getBaseCounts(String rnaString) {
        int[] baseCounts = new int[4];

        char[] strChars = rnaString.toCharArray();
        for (char strChar : strChars) {
            if (strChar == 'A') {
                baseCounts[0]++;
            }
            else if (strChar == 'C') {
                baseCounts[1]++;
            }
            else if (strChar == 'G') {
                baseCounts[2]++;
            }
            else if (strChar == 'U') {
                baseCounts[3]++;
            }
        }
        return baseCounts;
    }

    private static List<BigInteger> computedFactorials = new ArrayList<>();
    private static int maxNumWithComputedFactorial = 0;

    public static BigInteger getFactorial(int n){
        if (computedFactorials.isEmpty()){
            computedFactorials.add(BigInteger.valueOf(1));
            computedFactorials.add(BigInteger.valueOf(1));
            maxNumWithComputedFactorial = 1;
        }

        if (maxNumWithComputedFactorial >= n){
            return computedFactorials.get(n);
        }
        else {
            BigInteger iFactorial = computedFactorials.get(maxNumWithComputedFactorial);
            for (int i = maxNumWithComputedFactorial + 1; i <= n; i++) {
                iFactorial = iFactorial.multiply(BigInteger.valueOf(i));
                computedFactorials.add(iFactorial);
            }
            maxNumWithComputedFactorial = n;
            return iFactorial;
        }
    }
}
