package lcsm;

import sun.text.normalizer.Trie;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Axel on 10.9.2016.
 */
public class LcsmSolution {
    public static String problemId = "lcsm";
    public static String inputFileName = "rosalind_lcsm.txt";
    public static String outputFileName = "rosalind_lcsm_out.txt";

    public static void main(String[] args) {
        List<String> inputStrings = new ArrayList<>();

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
                        inputStrings.add(currentString);
                        currentString = "";
                    }
                }
                else{
                    currentString += strLine;
                }
            }
            inputStrings.add(currentString);

            //Close the input stream
            fstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        String outputString = findLongestCommonSubstring(Arrays.asList(new String[]{"abcde", "fghie"}));
        String outputString = findLongestCommonSubstring(inputStrings);

        System.out.println(outputString);

        try {
            PrintWriter writer = new PrintWriter("src/" + problemId + "/" + outputFileName, "UTF-8");
            writer.println(outputString);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String findLongestCommonSubstring(List<String> inputStrings){
        TrieNode topCommonSubstringTrieNode = new TrieNode();

        String firstString = inputStrings.get(0);
        int firstStringLen = firstString.length();
        for (int i = 0; i < firstStringLen; i++) {
            trieInsertSuffix(topCommonSubstringTrieNode, firstString, i, firstStringLen);
        }

        for (int i = 1; i < inputStrings.size(); i++) {
            TrieNode newTopCommonSubstringTrieNode = new TrieNode();

            String inputString = inputStrings.get(i);
            int inputStringLen = inputString.length();
            for (int j = 0; j < inputStringLen; j++) {
                commonSubstringTrieInsertSuffix(topCommonSubstringTrieNode, newTopCommonSubstringTrieNode, inputString, j, inputStringLen);
            }
            topCommonSubstringTrieNode = newTopCommonSubstringTrieNode;
        }

        String longestCommonSubstring = findMaxDepthStringInTrie(topCommonSubstringTrieNode);

        return longestCommonSubstring;
    }

    public static void trieInsertSuffix(TrieNode node, String insertStr, int suffixStartIdx, int insertStrLen){
        TrieNode currNode = node;
        for (int i = suffixStartIdx; i < insertStrLen; i++) {
            char ch = insertStr.charAt(i);
            TrieNode nextNode;
            if (currNode.children.containsKey(ch)){
                nextNode = currNode.children.get(ch);
            } else{
                nextNode = new TrieNode();
                currNode.children.put(ch, nextNode);
            }
            currNode = nextNode;
        }
        currNode.isEnd = true;
    }

    public static void commonSubstringTrieInsertSuffix(TrieNode oldNode, TrieNode newNode, String newInsertString, int suffixStartIdx, int insertStrLen){
        TrieNode currOldNode = oldNode;
        TrieNode currNewNode = newNode;
        for (int i = suffixStartIdx; i < insertStrLen; i++) {
            char ch = newInsertString.charAt(i);

            if (currOldNode.children.containsKey(ch)){
                TrieNode nextOldNode = currOldNode.children.get(ch);
                TrieNode nextNewNode;
                if (currNewNode.children.containsKey(ch)){
                    nextNewNode = currNewNode.children.get(ch);
                } else{
                    nextNewNode = new TrieNode();
                    currNewNode.children.put(ch, nextNewNode);
                }
                currNewNode = nextNewNode;
                currOldNode = nextOldNode;
            } else{
                if (currNewNode.children.size() == 0){
                    currNewNode.isEnd = true;
                }
                break;
            }
        }
        currNewNode.isEnd = true;
    }

    public static String findMaxDepthStringInTrie(TrieNode currNode){
        String maxDepthString = "";

        for (Character nextChar : currNode.children.keySet()) {
            TrieNode nextNode = currNode.children.get(nextChar);
            String bestStringForChar = nextChar + findMaxDepthStringInTrie(nextNode);
            if (maxDepthString.length() < bestStringForChar.length()){
                maxDepthString = bestStringForChar;
            }
        }
        return maxDepthString;
    }

    public static class TrieNode{
        public HashMap<Character, TrieNode> children = new HashMap<>();
        public boolean isEnd = false;
    }
}


