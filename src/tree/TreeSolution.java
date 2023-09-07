package tree;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Axel on 10.9.2016.
 */
public class TreeSolution {
    public static String problemId = "tree";
    public static String inputFileName = "rosalind_tree.txt";
    public static String outputFileName = "rosalind_tree_out.txt";


    public static void main(String[] args) {
        int n = 0;
        List<Integer[]> adjacencyList = new ArrayList<>();

        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream("src/" + problemId + "/" + inputFileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            String strLine = br.readLine();
            n = Integer.parseInt(strLine);

            while ((strLine = br.readLine()) != null) {
                Integer[] edgeNodes = new Integer[2];
                String[] strInts = strLine.split(" ");
                edgeNodes[0] = Integer.parseInt(strInts[0]);
                edgeNodes[1] = Integer.parseInt(strInts[1]);
                adjacencyList.add(edgeNodes);
            }

            //Close the input stream
            fstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }




        List<Integer> graphNodes = new ArrayList<>();
        for (int i = 1; i <= n; i++){
            graphNodes.add(i);
        }
        //A list of the graphs components. Each component in the list is represented by the nodes that form that component.
        //A component within a graph is a set of connected nodes in the graphs.
        List<List<Integer>> graphComponents = computeGraphComponents(graphNodes, adjacencyList);
        String outputString = Integer.toString(graphComponents.size()-1);




        System.out.println(outputString);
        try {
            PrintWriter writer = new PrintWriter("src/" + problemId + "/" + outputFileName, "UTF-8");
            writer.println(outputString);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<List<Integer>> computeGraphComponents(List<Integer> graphNodes, List<Integer[]> adjacencyList){
        List<List<Integer>> graphComponents = new ArrayList<>();

        //List of nodes we havent yet examined. Initially its contains every node in the graph.
        List<Integer> unexploredGraphNodes = new ArrayList<>();
        for (Integer graphNode : graphNodes) {
            unexploredGraphNodes.add(graphNode);
        }

        List<Integer[]> unprocessedEdges = new ArrayList<>();
        for (Integer[] edge : adjacencyList) {
            unprocessedEdges.add(edge);
        }

        //While the list of node is not empty
        while (!unexploredGraphNodes.isEmpty()) {
            List<Integer> currentComponentNodes = new ArrayList<>();
            List<Integer> unexpandedNodesInCurrentComponent = new ArrayList<>();

            //Remove first node from the list
            int firstComponentNode = unexploredGraphNodes.remove(0);
            currentComponentNodes.add(firstComponentNode);
            unexpandedNodesInCurrentComponent.add(firstComponentNode);

            //while the unexpanded connected nodes list is not empty
            while (!unexpandedNodesInCurrentComponent.isEmpty()) {
                int currentNode = unexpandedNodesInCurrentComponent.remove(0);

                //List of the indices in the unprocessedEdges list where we find edges currNode is part of
                List<Integer> currNodeEdgeIndices = new ArrayList<>();
                for (int i = 0; i < adjacencyList.size(); i++) {
                    Integer[] edgeNodes = adjacencyList.get(i);
                    int connectedNode = -1;
                    if (edgeNodes[0].equals(currentNode)){
                        currNodeEdgeIndices.add(i);
                        connectedNode = edgeNodes[1];
                    }
                    else if (edgeNodes[1].equals(currentNode)){
                        currNodeEdgeIndices.add(i);
                        connectedNode = edgeNodes[0];
                    }
                    if (connectedNode != -1 && !currentComponentNodes.contains(connectedNode)){
                        currentComponentNodes.add(connectedNode);
                        unexpandedNodesInCurrentComponent.add(connectedNode);
                        int idx = unexploredGraphNodes.indexOf(connectedNode);
                        unexploredGraphNodes.remove(idx);
                    }
                }
                //Reverse the edge index list so the inddices are in decending order.
                // The following loop can then remove each edge we processed from the unprocessedEdges list given its index in the list without altering the indices of the edges it has yet to remove.
                Collections.reverse(currNodeEdgeIndices);
                for (Integer edgeIndex : currNodeEdgeIndices) {
                    adjacencyList.remove(edgeIndex);
                }
            }
            graphComponents.add(currentComponentNodes);
        }
        return graphComponents;
    }
}
