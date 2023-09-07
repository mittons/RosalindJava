package prtm;

import java.io.*;
import java.util.HashMap;

/**
 * Created by Axel on 10.9.2016.
 */
public class PrtmSolution {
    public static String problemId = "prtm";
    public static String inputFileName = "rosalind_prtm.txt";
    public static String outputFileName = "rosalind_prtm_out.txt";


    public static void main(String[] args) {
        String inputProteinString = "";

        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream("src/" + problemId + "/" + inputFileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            inputProteinString = br.readLine();

            //Close the input stream
            fstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



        double proteinStringWeight = 0;
        HashMap<Character, Double> aaMonoisotopicMassMap = getAminoAcidMonoisotopicMassMap();
        for (int i = 0; i < inputProteinString.length(); i++) {
            char aa = inputProteinString.charAt(i);
            proteinStringWeight += aaMonoisotopicMassMap.get(aa);
        }



        String outputString = Double.toString(proteinStringWeight);
        System.out.println(outputString);
        try {
            PrintWriter writer = new PrintWriter("src/" + problemId + "/" + outputFileName, "UTF-8");
            writer.println(outputString);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HashMap<Character, Double> getAminoAcidMonoisotopicMassMap(){
        HashMap<Character, Double> aaMonoisotopicMassMap = new HashMap<>();
        aaMonoisotopicMassMap.put('A', 71.03711);
        aaMonoisotopicMassMap.put('C', 103.00919);
        aaMonoisotopicMassMap.put('D', 115.02694);
        aaMonoisotopicMassMap.put('E', 129.04259);
        aaMonoisotopicMassMap.put('F', 147.06841);
        aaMonoisotopicMassMap.put('G', 57.02146);
        aaMonoisotopicMassMap.put('H', 137.05891);
        aaMonoisotopicMassMap.put('I', 113.08406);
        aaMonoisotopicMassMap.put('K', 128.09496);
        aaMonoisotopicMassMap.put('L', 113.08406);
        aaMonoisotopicMassMap.put('M', 131.04049);
        aaMonoisotopicMassMap.put('N', 114.04293);
        aaMonoisotopicMassMap.put('P', 97.05276);
        aaMonoisotopicMassMap.put('Q', 128.05858);
        aaMonoisotopicMassMap.put('R', 156.10111);
        aaMonoisotopicMassMap.put('S', 87.03203);
        aaMonoisotopicMassMap.put('T', 101.04768);
        aaMonoisotopicMassMap.put('V', 99.06841);
        aaMonoisotopicMassMap.put('W', 186.07931);
        aaMonoisotopicMassMap.put('Y', 163.06333);

        return aaMonoisotopicMassMap;
    }
}
