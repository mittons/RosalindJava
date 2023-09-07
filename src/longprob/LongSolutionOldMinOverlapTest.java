package longprob;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Axel on 10.9.2016.
 */
public class LongSolutionOldMinOverlapTest {
    public static String problemId = "longprob";
    public static String inputFileName = "SampleInput.txt";
    public static String outputFileName = "SampleOutput.txt";

    public static void main(String[] args) {
        List<String> dnaStrings = new ArrayList<>();

        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream("src/" + problemId + "/" + inputFileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;

            boolean first = true;
            String currentString = "";

            //Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                if (strLine.startsWith(">")){
                    if (first){
                        first = false;
                    }
                    else {
                        dnaStrings.add(currentString);
                        currentString = "";
                    }
                }
                else{
                    currentString += strLine;
                }
            }
            dnaStrings.add(currentString);

            //Close the input stream
            fstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (dnaStrings.size() > 1){
            int bestSuffixStringIdx = 0;
            int bestPrefixStringIdx = 1;
            int bestOverlapLength = 0;

            for (int i = 0; i < dnaStrings.size(); i++) {
                String candidateSuffixString = dnaStrings.get(i);
                int candidateSuffixStringLen = candidateSuffixString.length();
                if (candidateSuffixStringLen < bestOverlapLength + 1) continue;
                int minOverlapToTest = (candidateSuffixStringLen/2) + 1;
                minOverlapToTest = Math.max(minOverlapToTest, bestOverlapLength + 1);
                int maxSuffixStartIdx = candidateSuffixStringLen - minOverlapToTest;

                boolean skipToNextPrefixString = false;

                for (int j = 0; j < dnaStrings.size(); j++) {
                    if (i == j) continue;
                    String candidatePrefixString = dnaStrings.get(j);
                    int candidatePrefixStringLen = candidatePrefixString.length();
                    if (candidatePrefixStringLen < minOverlapToTest) continue;

                    for (int suffixStartIdx = maxSuffixStartIdx; suffixStartIdx >= 0; suffixStartIdx--) {
                        boolean match = suffixMatchesPrefix(candidateSuffixString, suffixStartIdx, candidateSuffixStringLen, candidatePrefixString);
                        if (match){
                            int matchLen = candidatePrefixStringLen - suffixStartIdx;
                            bestOverlapLength = matchLen;
                            bestSuffixStringIdx = i;
                            bestPrefixStringIdx = j;
                            if (suffixStartIdx == 0){
                                skipToNextPrefixString = true;
                                break;
                            }
                            minOverlapToTest = matchLen + 1;
                            maxSuffixStartIdx = candidateSuffixStringLen - minOverlapToTest;
                        }
                    }
                    if (skipToNextPrefixString) break;
                }
            }

            String bestSuffixString = dnaStrings.get(bestSuffixStringIdx);
            String bestPrefixString = dnaStrings.get(bestPrefixStringIdx);
            int removeFirst = Math.max(bestSuffixStringIdx, bestPrefixStringIdx);
            int removeSecond = Math.min(bestSuffixStringIdx, bestPrefixStringIdx);
            dnaStrings.remove(removeFirst);
            dnaStrings.remove(removeSecond);

            String joinedString = bestSuffixString + bestPrefixString.substring(bestOverlapLength);
            dnaStrings.add(joinedString);
        }

        String outputString = "";

        System.out.println(outputString);
//
//        try {
//            PrintWriter writer = new PrintWriter("src/" + problemId + "/" + outputFileName, "UTF-8");
//            writer.println(outputString);
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public static boolean suffixMatchesPrefix(String suffixString, int suffixStartIdx, int suffixStrLen, String prefixString){
        int suffixLen = suffixStrLen - suffixStartIdx;
        boolean match = true;
        for (int i = 0; i < suffixLen; i++) {
            if (suffixString.charAt(suffixStartIdx + i) != prefixString.charAt(i)){
                match = false;
                break;
            }
        }
        return match;
    }

    public static String joinOverlappingStrings(String suffixString, String prefixString, int overlapLength){
        String joinedString = suffixString + prefixString.substring(overlapLength);
        return joinedString;
    }
}
