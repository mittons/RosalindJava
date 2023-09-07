package prot;

import java.io.*;
import java.util.HashMap;

/**
 * Created by Axel on 6.9.2016.
 */
public class ProtSolution {
    public static String problemId = "prot";
    public static String inputFileName = "rosalind_prot.txt";
    public static String outputFileName = "rosalind_prot_out.txt";


    public static void main(String[] args) {
        String strLine = "";

        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream("src/" + problemId + "/" + inputFileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            strLine = br.readLine();

            //Close the input stream
            fstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String outputString = "";

        HashMap<String, String> rnaCodonTable = getRnaCodonTableMap();

        int codonCount = strLine.length()/3;
        for (int i = 0; i < codonCount; i++) {
            String codon = strLine.substring(i*3, i*3+3);
            String aminoAcid = rnaCodonTable.get(codon);
            if (aminoAcid.equals("Stop")){
                break;
            }
            outputString += aminoAcid;
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
