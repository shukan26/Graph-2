package Graph-2;

/**
 * Uses Tarjan’s Algorithm (DFS + low-link values) to find all critical connections/bridges in the graph.
 * During DFS, if the lowest reachable node from a neighbor is greater than the discovery time of the current node, that edge is a bridge.
 * Time Complexity: O(V + E), Space Complexity: O(V + E) for adjacency list, recursion stack, and tracking arrays.
 */
public class CriticalConnections {
        int[] discovery;
    int[] lowest; 
    int time;
    HashMap<Integer, List<Integer>> map;
    List<List<Integer>> result;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        this.map = new HashMap<>();
        this.lowest = new int[n];
        this.discovery = new int[n];
        this.time = 0; 
        this.result = new ArrayList<>();

        Arrays.fill(discovery, -1);

        for(int i = 0; i < n ; i++) {
            map.put(i, new ArrayList<>());
        }

        //create adj map
        for(List<Integer> connection : connections) {
            int u = connection.get(0);
            int v = connection.get(1);

            map.get(u).add(v);
            map.get(v).add(u);
        }

        helper(0, -1);
        return result;
    }

    private void helper(int u, int v) {
        if(discovery[u] != 1) return; 
        discovery[u] = time;
        lowest[u] = time;
        time++;

        for(int ne : map.get(u)) {
            if(ne == v) continue; 

            helper(ne, u);

            if(lowest[ne] > discovery[u]) {
                result.add(Arrays.asList(ne,u));
            }
            lowest[u] = Math.min(lowest[ne], lowest[u]);
        }
    } 
    
}
