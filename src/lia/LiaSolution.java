package lia;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

/**
 * Created by Axel on 10.9.2016.
 */
public class LiaSolution {
    public static String problemId = "lia";
    public static String inputFileName = "rosalind_lia.txt";
    public static String outputFileName = "rosalind_lia_out.txt";


    public static void main(String[] args) {
        int k = 0;
        int N = 0;

        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream("src/" + problemId + "/" + inputFileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            String[] strInts = br.readLine().split(" ");
            k = Integer.parseInt(strInts[0]);
            N = Integer.parseInt(strInts[1]);

            //Close the input stream
            fstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        double answer = 0;

        int n = (int)Math.pow(2,k);
        //These if/else branches should (in theory) do the same thing and the code from either could replace the whole if/else block.
        //However, each branch is only taken if it will make less or equal number of calls to rDecedantsDomProbability(r,n) than the other.
        //This should usually (if not always) result in fewer computations required for the final result.
        //More importantly, the more output values from rDecedantsDomProbability(r,n) that the answer is composed of, the less precise the answer becomes
        //Although each return value from rDecedantsDomProbability(r,n) should not add much to the inprecision there are cases where it matters, for example:
        //If N = 1 and n = 128 then the "if branch" will sum the output values from 128 calls to rDecedantsDomProbability(r,n) to form
        // the answer of 1.0000000000000004 (clearly wrong, probablility is never greater than 1),
        // while the else branch will form the answer from the output value of a single call to rDecedantsDomProbability(r,n) with the final answer of 0.9999999999999999 (more correct)
        if (N >= n - N){
          for (int r = N; r <= n; r++) {
                double rDecendantsDomProbability = rDecedantsDomProbability(r, n);
                answer += rDecendantsDomProbability;
            }
        }
        else{
            for (int r = 0; r < N; r++) {
                double rDecendantsDomProbability = rDecedantsDomProbability(r, n);
                answer += rDecendantsDomProbability;
            }
            answer = 1 - answer;
        }

        String outputString = Double.toString(answer);
        System.out.println(outputString);

        try {
            PrintWriter writer = new PrintWriter("src/" + problemId + "/" + outputFileName, "UTF-8");
            writer.println(outputString);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Calculates the probability of exactly r decendants being domininant given n total decendants
    //n is the total number of decendants
    //r is the exact number of dominant decendants
    public static double rDecedantsDomProbability(int r, int n){
        BigInteger big3 = BigInteger.valueOf(3);
        BigInteger big4 = BigInteger.valueOf(4);
        //rDecendantsProbability = (nChooseR*(3**(n-r))/((4**r)*(4**(n-r)) = (nChooseR*(3**(n-r))/(4**(r+(n-r))) = (nChooseR*(3**(n-r))/(4**n)
        BigInteger nChooseR = nChooseK(n, r);
        BigInteger big3powNr = big3.pow(n-r);
        BigInteger numerator = nChooseR.multiply(big3powNr);
        BigInteger denominator = big4.pow(n);
        double rDecendantsProbability = bigIntergerDivision(numerator, denominator);
        return rDecendantsProbability;
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

    public static BigInteger nChooseK(int n, int k){
        BigInteger nFactorial = getFactorial(n);
        BigInteger kFactorial = getFactorial(k);
        BigInteger nkFactorial = getFactorial(n-k);
        BigInteger nDivK = nFactorial.divide(kFactorial);
        BigInteger nChooseK = nDivK.divide(nkFactorial);
        return nChooseK;
    }

    public static double bigIntergerDivision(BigInteger numerator, BigInteger denominator){
        double result = 0.0;

        BigInteger big10 = BigInteger.valueOf(10);
        boolean bestApproxValueFound = false;
        int decimalDivPower = 0;
        while (!bestApproxValueFound) {
            while (numerator.compareTo(denominator) == -1){
                if (decimalDivPower == 40){
                    bestApproxValueFound = true;
                    break;
                }
                decimalDivPower += 1;
                numerator = numerator.multiply(big10);
            }
            if (bestApproxValueFound){
                break;
            }
            //Current code works so its best not to change it and use the time for something else, but I'll leave this note here anyway:
            //Could probably skip the if clause, change the else to "if (numerator.compareTo(denominator) >= 0)" and
            //trigger the stop condition if the remainder (i.e. the updated numerator value) is 0.
            if (numerator.compareTo(denominator) == 0){
                result += 1.0/Math.pow(10,decimalDivPower);
                bestApproxValueFound = true;
            }
            else {
                BigInteger[] divResults = numerator.divideAndRemainder(denominator);
                BigInteger quotient = divResults[0];
                numerator = divResults[1]; //numerator = remainder
                result += quotient.doubleValue()/Math.pow(10,decimalDivPower);
            }
        }
        return result;
    }
}
