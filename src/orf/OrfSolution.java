package orf;

import com.sun.deploy.util.ArrayUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Axel on 10.9.2016.
 */
public class OrfSolution {
    public static String problemId = "orf";
    public static String inputFileName = "rosalind_orf.txt";
    public static String outputFileName = "rosalind_orf_out.txt";

    public static void main(String[] args) {
        String inputDnaString = "";

        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream("src/" + problemId + "/" + inputFileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;

            //First line contains the label of the string. We dont need the label so we ignore the first line.
            br.readLine();

            while ((strLine = br.readLine()) != null) {
                inputDnaString += strLine;
            }

            //Close the input stream
            fstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }




        List<String> possibleProteinStrings = new ArrayList<>();

        //Transcribe the input DNA string to a RNA string.
        String inputRnaString = inputDnaString.replace('T', 'U');

        //Generate the reverse complement RNA string of the input RNA string
        String revcInputRnaString = getRnaStringReverseComplement(inputRnaString);

        List<String> rnaStrings = new ArrayList<>();
        rnaStrings.add(inputRnaString);
        rnaStrings.add(revcInputRnaString);

        int rnaStringLen = inputRnaString.length();
        HashMap<String, String> rnaCodonTable = getRnaCodonTableMap();

        for (String rnaString : rnaStrings) {
            HashMap<Integer, List<Integer>> stopCodonToValidStartCodonsMap = new HashMap<>();
            //Read through the codons of the rna string three times, first reading nt triplets from idx 0, then from idx 1 and finally from idx 2.
            //Keep track of every start codon since last stop codon.
            //Once a stop codon is hit and we've seen one or more start codon since last stop codon we store the locations of those start codons along with the location of the stop codon
            for (int i = 0; i < 3; i++) {
                List<Integer> startCodons = new ArrayList<>();
                int codonCount = (rnaStringLen-i)/3;
                for (int j = 0; j < codonCount; j++) {
                    String codon = rnaString.substring(j*3 + i, j*3 + 3 + i);
                    if (codon.equals("AUG")){
                        startCodons.add(j*3 + i);
                    }
                    else if (rnaCodonTable.get(codon).equals("Stop") && !startCodons.isEmpty()){
                        Collections.reverse(startCodons);
                        stopCodonToValidStartCodonsMap.put(j*3 + i, startCodons);
                        startCodons = new ArrayList<>();
                    }
                }
            }
            //For each group stop codon + start codons we stored, generate the strings from the start codons to the stop oodons
            for (Integer stopCodonIdx : stopCodonToValidStartCodonsMap.keySet()) {
                String translatedProteinSuffix = "";
                int prefixStopIdx = stopCodonIdx;
                for (Integer startCodonIdx : stopCodonToValidStartCodonsMap.get(stopCodonIdx)) {
                    String proteinPrefix = "";
                    int currCodonIdx = startCodonIdx;
                    int nextCodonIdx = startCodonIdx + 3;
                    while (currCodonIdx != prefixStopIdx) {
                        String currCodon = rnaString.substring(currCodonIdx, nextCodonIdx);
                        proteinPrefix += rnaCodonTable.get(currCodon);
                        currCodonIdx += 3;
                        nextCodonIdx += 3;
                    }
                    String proteinString = proteinPrefix + translatedProteinSuffix;
                    if (!possibleProteinStrings.contains(proteinString)) {
                        possibleProteinStrings.add(proteinString);
                    }

                    translatedProteinSuffix = proteinString;
                    prefixStopIdx = startCodonIdx;
                }
            }
        }

        String outputString = "";
        for (String possibleProteinString : possibleProteinStrings) {
            if (outputString.length() != 0) outputString += "\n";
            outputString += possibleProteinString;
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

    public static String getRnaStringReverseComplement(String rnaString){
        String revcRnaString = "";
        char[] rnaStringCharArr = rnaString.toCharArray();
        for (int i = rnaStringCharArr.length - 1; i >= 0; i--) {
            char rnaBase = rnaStringCharArr[i];
            switch (rnaBase){
                case 'A':
                    revcRnaString += 'U';
                    break;
                case 'C':
                    revcRnaString += 'G';
                    break;
                case 'G':
                    revcRnaString += 'C';
                    break;
                case 'U':
                    revcRnaString += 'A';
                    break;
                default:
                    System.out.println("Unexpected character in the RNA string while generating the reverse complement." + "\nThe unexpected character was: " + rnaBase + "\nASCII value of the unexpected character: " + Integer.toString((int)rnaBase));
                    System.exit(1);
            }
        }
        return revcRnaString;
    }

    public static HashMap<String, String> getRnaCodonTableMap(){
        HashMap<String, String> rnaCodonTable = new HashMap();
        rnaCodonTable.put("UUU", "F");
        rnaCodonTable.put("CUU", "L");
        rnaCodonTable.put("AUU", "I");
        rnaCodonTable.put("GUU", "V");
        rnaCodonTable.put("UUC", "F");
        rnaCodonTable.put("CUC", "L");
        rnaCodonTable.put("AUC", "I");
        rnaCodonTable.put("GUC", "V");
        rnaCodonTable.put("UUA", "L");
        rnaCodonTable.put("CUA", "L");
        rnaCodonTable.put("AUA", "I");
        rnaCodonTable.put("GUA", "V");
        rnaCodonTable.put("UUG", "L");
        rnaCodonTable.put("CUG", "L");
        rnaCodonTable.put("AUG", "M");
        rnaCodonTable.put("GUG", "V");
        rnaCodonTable.put("UCU", "S");
        rnaCodonTable.put("CCU", "P");
        rnaCodonTable.put("ACU", "T");
        rnaCodonTable.put("GCU", "A");
        rnaCodonTable.put("UCC", "S");
        rnaCodonTable.put("CCC", "P");
        rnaCodonTable.put("ACC", "T");
        rnaCodonTable.put("GCC", "A");
        rnaCodonTable.put("UCA", "S");
        rnaCodonTable.put("CCA", "P");
        rnaCodonTable.put("ACA", "T");
        rnaCodonTable.put("GCA", "A");
        rnaCodonTable.put("UCG", "S");
        rnaCodonTable.put("CCG", "P");
        rnaCodonTable.put("ACG", "T");
        rnaCodonTable.put("GCG", "A");
        rnaCodonTable.put("UAU", "Y");
        rnaCodonTable.put("CAU", "H");
        rnaCodonTable.put("AAU", "N");
        rnaCodonTable.put("GAU", "D");
        rnaCodonTable.put("UAC", "Y");
        rnaCodonTable.put("CAC", "H");
        rnaCodonTable.put("AAC", "N");
        rnaCodonTable.put("GAC", "D");
        rnaCodonTable.put("UAA", "Stop");
        rnaCodonTable.put("CAA", "Q");
        rnaCodonTable.put("AAA", "K");
        rnaCodonTable.put("GAA", "E");
        rnaCodonTable.put("UAG", "Stop");
        rnaCodonTable.put("CAG", "Q");
        rnaCodonTable.put("AAG", "K");
        rnaCodonTable.put("GAG", "E");
        rnaCodonTable.put("UGU", "C");
        rnaCodonTable.put("CGU", "R");
        rnaCodonTable.put("AGU", "S");
        rnaCodonTable.put("GGU", "G");
        rnaCodonTable.put("UGC", "C");
        rnaCodonTable.put("CGC", "R");
        rnaCodonTable.put("AGC", "S");
        rnaCodonTable.put("GGC", "G");
        rnaCodonTable.put("UGA", "Stop");
        rnaCodonTable.put("CGA", "R");
        rnaCodonTable.put("AGA", "R");
        rnaCodonTable.put("GGA", "G");
        rnaCodonTable.put("UGG", "W");
        rnaCodonTable.put("CGG", "R");
        rnaCodonTable.put("AGG", "R");
        rnaCodonTable.put("GGG", "G");

        return rnaCodonTable;
    }
}
