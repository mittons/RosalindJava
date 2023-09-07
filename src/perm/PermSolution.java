package perm;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Axel on 2.9.2016.
 */
public class PermSolution {
    public static String problemId = "perm";
    public static String inputFileName = "rosalind_perm.txt";
    public static String outputFileName = "rosalind_perm_out.txt";


    public static void main(String[] args) {
        int n = 0;

        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream("src/" + problemId + "/" + inputFileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            String strLine = br.readLine();
            n = Integer.parseInt(strLine);

            //Close the input stream
            fstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int nFactorial = 1;
        for (int i = 2; i <= n; i++) {
            nFactorial *= i;
        }

        List<int[]> allPermutations = new ArrayList<>();

        int[] basePermutation = new int[n];
        for (int i = 0; i < n; i++) {
            basePermutation[i] = i + 1;
        }

        generatePermutations(basePermutation, 0, n, allPermutations);

        String outputString = Integer.toString(nFactorial);
        for (int[] permutationArray : allPermutations) {
            outputString += "\n" + intArrToString(permutationArray);
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

    //perm is an array that contains the set of integers we are generation permutations,
        //we also use the array perm to swap the elements in it in recursive calls to generate each permutation
    //minSwapIdx is the lowest index in perm that we can swap an element from to generate new permutations (in this recursive call and calls below it)
    //n is the number of elements in perm
    //permutationList is the list we add each new permutation generated to.
        //After the initial call to this recursive function this list should hold all possible permutations of the elemetnts in perm

    public static void generatePermutations(int[] perm, int minSwapIdx, int n, List<int[]> permutationList){
        if (minSwapIdx == (n - 1)){
            int[] currPerm = new int[n];
            for (int i = 0; i < n; i++) {
                currPerm[i] = perm[i];
            }
            permutationList.add(currPerm);
        }
        else {
            for (int i = minSwapIdx; i < n; i++) {
                swapElements(perm, i, minSwapIdx);
                generatePermutations(perm, minSwapIdx + 1, n, permutationList);
                swapElements(perm, i, minSwapIdx);
            }
        }
    }

    public static void swapElements(int[] array, int swapIdx0, int swapIdx1){
        int temp = array[swapIdx0];
        array[swapIdx0] = array[swapIdx1];
        array[swapIdx1] = temp;
    }

    public static String intArrToString(int[] array){
        String out = Integer.toString(array[0]);
        for (int i = 1; i < array.length; i++) {
            out += " " + Integer.toString(array[i]);
        }
        return out;
    }


}
