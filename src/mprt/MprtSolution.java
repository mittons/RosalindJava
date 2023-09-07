package mprt;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Axel on 10.9.2016.
 */
public class MprtSolution {
    public static String problemId = "mprt";
    public static String inputFileName = "rosalind_mprt.txt";
    public static String outputFileName = "rosalind_mprt_out.txt";

    public static void main(String[] args) {
        HashMap<String, String> proteinStringByProteinId = new HashMap<>();

        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream("src/" + problemId + "/" + inputFileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            List<String> proteinIds = new ArrayList<>();

            String strLine;
            //Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                proteinIds.add(strLine);
            }
            //Close the input stream
            fstream.close();

            for (String proteinId : proteinIds) {
                URL url = new URL("http://www.uniprot.org/uniprot/" + proteinId + ".fasta");
                Scanner s = new Scanner(url.openStream());

                if (s.hasNextLine()){
                    strLine = s.nextLine();
                    if (!strLine.startsWith(">")){
                        System.out.println("Invalid fasta file for protein with id: " + proteinId);
                        System.exit(1);
                    }
                } else {
                    System.out.println("Unable to fetch fasta file for protein with id: " + proteinId);
                    System.exit(1);
                }

                String proteinString = "";
                while (s.hasNextLine()){
                    strLine = s.nextLine();
                    proteinString += strLine;
                }
                proteinStringByProteinId.put(proteinId, proteinString);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }



        String outputString = "";

        //regex for the N-glycosylation motif
        String nGlycoPattern = "N(?=([^P][ST][^P]))";
        Pattern p = Pattern.compile(nGlycoPattern);

        for (String proteinId : proteinStringByProteinId.keySet()) {
            List<Integer> nGlycoLocations = new ArrayList<>();

            String proteinString = proteinStringByProteinId.get(proteinId);
            Matcher m = p.matcher(proteinString);
            while (m.find()){
                nGlycoLocations.add(m.start() + 1);
            }

            //If nGlyco was found in the protein: write protein id and locations to the outputString
            if (!nGlycoLocations.isEmpty()){
                if (outputString.length() != 0) outputString += "\n";
                outputString += proteinId + "\n";
                outputString += nGlycoLocations.get(0);
                for (int i = 1; i < nGlycoLocations.size(); i++) {
                    int nGlycoLocation = nGlycoLocations.get(i);
                    outputString += " " + Integer.toString(nGlycoLocation);
                }
            }
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
}
