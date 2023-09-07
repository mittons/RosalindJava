package mrna;

import java.io.*;
import java.util.HashMap;

/**
 * Created by Axel on 10.9.2016.
 */
public class MrnaSolution {
    public static String problemId = "mrna";
    public static String inputFileName = "rosalind_mrna.txt";
    public static String outputFileName = "rosalind_mrna_out.txt";


    public static void main(String[] args) {
        String inputProteinString = "";

        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream("src/" + problemId + "/" + inputFileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;

            while ((strLine = br.readLine()) != null) {
                inputProteinString += strLine;
            }

            //Close the input stream
            fstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int uniqueRnaStrCountForProteinStr = computeNrOfRnaStrMappingToProt(inputProteinString);

        String outputString = Integer.toString(uniqueRnaStrCountForProteinStr);
        System.out.println(outputString);

        try {
            PrintWriter writer = new PrintWriter("src/" + problemId + "/" + outputFileName, "UTF-8");
            writer.println(outputString);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Outputs the number of unique RNA strings the protein string supplied as input could have been translated from.
    public static int computeNrOfRnaStrMappingToProt(String proteinString){
        char[] proteinStringAminoAcids = proteinString.toCharArray();

        HashMap<Character, Integer> aaCodonCountMap = getAminoAcidCodonCountMap();
        //Number of unique RNA strings from which the protein described by the input string could have been translated.
        //Init to 3 to take into account the choice between the 3 stop codons for the stop codon at the end of the RNA string.
        int uniqueRnaStrCountForProteinStr = 3;

        int modulus = (int)Math.pow(10,6);
        for (char aminoAcid : proteinStringAminoAcids) {
            int aaCodonCount = aaCodonCountMap.get(aminoAcid);
            uniqueRnaStrCountForProteinStr = (uniqueRnaStrCountForProteinStr*aaCodonCount)%modulus;
        }

        return uniqueRnaStrCountForProteinStr;
    }

    public static HashMap<Character, Integer> getAminoAcidCodonCountMap(){
        HashMap<Character, Integer> aaCodonCountMap = new HashMap<>();
        aaCodonCountMap.put('A', 4);
        aaCodonCountMap.put('C', 2);
        aaCodonCountMap.put('E', 2);
        aaCodonCountMap.put('D', 2);
        aaCodonCountMap.put('G', 4);
        aaCodonCountMap.put('F', 2);
        aaCodonCountMap.put('I', 3);
        aaCodonCountMap.put('H', 2);
        aaCodonCountMap.put('K', 2);
        aaCodonCountMap.put('M', 1);
        aaCodonCountMap.put('L', 6);
        aaCodonCountMap.put('N', 2);
        aaCodonCountMap.put('Q', 2);
        aaCodonCountMap.put('P', 4);
        aaCodonCountMap.put('S', 6);
        aaCodonCountMap.put('R', 6);
        aaCodonCountMap.put('T', 4);
        aaCodonCountMap.put('W', 1);
        aaCodonCountMap.put('V', 4);
        aaCodonCountMap.put('Y', 2);
        return aaCodonCountMap;
    }
}
