package gc;

import com.sun.deploy.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Axel on 31.8.2016.
 */
public class GcSolution {
    public static String problemId = "gc";
    public static String inputFileName = "rosalind_gc.txt";
    public static String outputFileName = "rosalind_gc_out.txt";

    public static void main(String[] args) {
        String bestStringLabel = "";
        Double bestStringGcRatio = 0.0;

        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream("src/" + problemId + "/" + inputFileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;

            String currentStringLabel = "";
            int currentCharCount = 0;
            int currentGcCharCount = 0;

            //Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                if (strLine.startsWith(">")){
                    //update best
                    Double currentStringGcRatio = (double)currentGcCharCount/(double)currentCharCount;
                    if (currentStringGcRatio > bestStringGcRatio){
                        bestStringLabel = currentStringLabel;
                        bestStringGcRatio = currentStringGcRatio;
                    }
                    //reset
                    currentStringLabel = strLine.substring(1);
                    currentCharCount = 0;
                    currentGcCharCount = 0;
                }
                else{
                    char[] strChars = strLine.toCharArray();
                    currentCharCount += strChars.length;
                    for (char strChar : strChars) {
                        if (strChar == 'C' || strChar == 'G'){
                            currentGcCharCount += 1;
                        }
                    }
                }
            }
            Double currentStringGcRatio = (double)currentGcCharCount/(double)currentCharCount;
            if (currentStringGcRatio > bestStringGcRatio){
                bestStringLabel = currentStringLabel;
                bestStringGcRatio = currentStringGcRatio;
            }
            //Close the input stream
            fstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String outputString = bestStringLabel + "\n" + String.format(Locale.ROOT, "%.6f", bestStringGcRatio*100);
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

