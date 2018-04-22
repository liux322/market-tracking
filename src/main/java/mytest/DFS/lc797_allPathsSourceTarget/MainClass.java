package mytest.DFS.lc797_allPathsSourceTarget;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//
//class Solution {
//    public void DFS_helper(int start, int end, List<Integer> interimPath, int[][] graph, List<List<Integer>> allPaths) {
//        interimPath.add(start);
//        for(int node : graph[start]) {
//            DFS_helper(node,end,interimPath,graph,allPaths);
//        }
//        if(start == end)    // That means we have found one path.
//            allPaths.add(new ArrayList<>(interimPath));
//        interimPath.remove(interimPath.size()-1); // Remove the last added element;
//    }
//
//    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
//        List<List<Integer>> allPaths = new ArrayList<>();
//        List<Integer> interimPaths = new ArrayList<>();
//        DFS_helper(0,graph.length-1,interimPaths,graph,allPaths);
//        return allPaths;
//    }
//}

class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> rout = new ArrayList<>();
        DFS_helper(0, graph.length-1, graph, rout, result );
        return result;
    }

    void DFS_helper(int node, int end, int[][] graph, List<Integer> rout,  List<List<Integer>> allrouts){
        rout.add(node);
        for(int n: graph[node]){
         //   rout.add(n);
            DFS_helper(n, end, graph, rout, allrouts);
        }
        if(node == end){
//            allPaths.add(new ArrayList<>(interimPath));
//            interimPath.remove(interimPath.size()-1); // Remove the last added element;
            allrouts.add(new ArrayList<>(rout));
         //   allrouts.add(rout);
            //rout.remove(rout.size()-1);
           // rout = new ArrayList<>();
        }
        rout.remove(rout.size()-1);
    }
}

public class MainClass {




    public static void main(String[] args) throws IOException {
        int[][] graph = new int[][]{{1,2}, {3}, {3} ,{}};
        List<List<Integer>> ret = new Solution().allPathsSourceTarget(graph);

    }
}


//    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
//        Map<Integer, List<Integer>> nodeEdge = new HashMap<>();
//        for(int node=0; node< grapth.length ; node ++){
//            for(int edge =0; edge< graph[node].length, edge++){
//                List<Integer> edges = new ArrayList<>();
//                edges.add(grapth[node][edge]);
//            }
//            nodeEdge.put(node, edges);
//        }
//        int end = graph.length-1;
//
//        for(int node=0; node< grapth.length ; node ++){
//            List<Integer> start = noeEdge.get(node);
//            List<List<Integer>> results = new ArrayList<>();
//            for(int node =0; node< start.size(); node++){
//                List<Integer> rout = new ArrayList<>();
//                rout.add(0);
//                List<Integer> nextRout = nodeEdge.get(start(node));
//
//            }
//        }
//
//    }
