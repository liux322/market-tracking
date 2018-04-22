package mytest.BFS.busrout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/*
Example:
Input:
routes = [[1, 2, 7], [3, 6, 7]]
S = 1
T = 6
Output: 2
Explanation:
The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
 */
class Solution {
//    public int numBusesToDestination(int[][] routes, int S, int T) {
//        if (S == T) return 0;
//        Map<Integer, Set<Integer>> map = new HashMap<>();
//        Queue<Integer> queue = new LinkedList<>();
//        Set<Integer> visited = new HashSet<>();
//        for (int i = 0; i < routes.length; ++i) {
//            for (int j : routes[i]) {
//                if (!map.containsKey(j)) map.put(j, new HashSet<Integer>());
//                map.get(j).add(i);
//            }
//        }
//        for (int next : map.get(S)) {
//            visited.add(next);
//            queue.add(next);
//        }
//        int res = 1;
//        while (!queue.isEmpty()) {
//            Queue<Integer> tmp = new LinkedList<>();
//            while (!queue.isEmpty()) {
//                int cur = queue.poll();
//                for (int c : routes[cur]) {
//                    if (c == T) return res;
//                    for (int next : map.get(c)) {
//                        if (!visited.contains(next)) {
//                            tmp.add(next);
//                            visited.add(next);
//                        }
//                    }
//                }
//            }
//            ++res;
//            queue = tmp;
//        }
//        return -1;
//    }
    public int numBusesToDestination(int[][] routes, int S, int T) {
        Map<Integer, List<Integer>> stopRoutMap = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> visited = new ArrayList<>();
        for(int rout =0; rout<routes.length; rout++){
            for(int i = 0; i <routes[rout].length; i++){
                if(!stopRoutMap.containsKey(routes[rout][i])){
                    stopRoutMap.put(routes[rout][i], new ArrayList<>());
                }
                stopRoutMap.get(routes[rout][i]).add(rout);
            }
        }

        for(int bus: stopRoutMap.get(S)){
            visited.add(bus);
            queue.add(bus);
        }

        int count =1;
        while(!queue.isEmpty()){
            Queue<Integer> tmp = new LinkedList<>();
            while(!queue.isEmpty()){
                int bus = queue.poll();
                for(int s : routes[bus]){
                    if(s == T){
                        return count;
                    }
                    for(int next : stopRoutMap.get(s)){
                        if(!visited.contains(next)){
                            tmp.add(next);
                            visited.add(next);
                        }
                    }
                }
                queue = tmp;
                count++;
            }

        }

        return 0;
    }
}
public class MainClass {

}
