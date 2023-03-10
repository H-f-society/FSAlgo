package com.fsalgo.core.graph.strongconn;

import com.fsalgo.core.struct.Edge;
import com.fsalgo.core.struct.Graph;
import com.fsalgo.core.struct.Graphs;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author: root
 * @Date: 2023/2/26 22:25
 * @Description:
 */
public class TarjanCutPointAndBridge<N> {

    private final Graph<N> graph;

    private int[] dfn;
    private int[] low;
    private boolean[] visited;
    private int searchSort = 0;
    private Map<N, Integer> indexs;

    private Set<N> cutPoints = new HashSet<>();

    private Set<Edge<N>> bridges = new HashSet<>();

    public TarjanCutPointAndBridge(Graph<N> graph) {
        this.graph = graph;
        initContainer();
    }

    private void initContainer() {
        this.indexs = Graphs.getNodeToIndexMapping(graph).getNodeMap();
        this.dfn = new int[indexs.size()];
        this.low = new int[indexs.size()];
        this.visited = new boolean[indexs.size()];
    }

    public void search(N root) {
        if (root == null) {
            throw new IllegalArgumentException("the access node cannot be empty!");
        }
        dfs(root);
    }

    public void search() {
        for (N node : indexs.keySet()) {
            if (visited[indexs.get(node)] || graph.outgoingEdges(node).size() == 0) {
                continue;
            }
            searchSort = 1;
            search(node);
        }
    }

    private void dfs(N root) {
        if (root == null) {
            return;
        }
        int index = indexs.get(root);
        if (visited[index]) {
            return;
        }
        visited[index] = true;
        dfn[index] = searchSort;
        low[index] = searchSort++;

        if (graph.outgoingEdges(root).size() == 0) {
            return;
        }

        if (dfn[index] == 1 && graph.outgoingEdges(root).size() >= 2) {
            cutPoints.add(root);
        }

        for (Edge<N> edge : graph.outgoingEdges(root)) {
            N child = edge.getTarget();

            dfs(child);

            int childIndex = indexs.get(child);

            low[index] = Math.min(low[index], low[childIndex]);

            if (dfn[index] > 1 && dfn[index] <= low[childIndex]) {
                cutPoints.add(root);
            }

            if (dfn[index] < low[childIndex]) {
                bridges.add(edge);
            }
        }
    }

    public Set<N> getCutPoints() {
        return cutPoints;
    }

    public Set<Edge<N>> getBridges() {
        return bridges;
    }
}
