package graph;

import java.util.LinkedList;

/**
 * 图拓扑排序算法
 *
 * @author czd
 * @create 2019-12-10 20:54
 **/
public class Sort {
    /**
     * Kahn算法
     * 贪心算法
     * 我们先从图中，找出一个入度为 0 的顶点，将其输出到拓扑排序的结果序列中（对应代码中就是把它打印出来）
     * 并且把这个顶点从图中删除（也就是把这个顶点可达的顶点的入度都减 1）。
     * 我们循环执行上面的过程，直到所有的顶点都被输出
     * 时间复杂度O(V+E)（V 表示顶点个数，E 表示边的个数）
     */
    public void topoSortByKahn(DGraph graph) {
        int v = graph.getV();
        LinkedList<Integer>[] adj = graph.getAdj();
        int[] inDegree = new int[v];
        // 遍历图统计入度
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < adj[i].size(); j++) {
                int w = adj[i].get(j);
                inDegree[w]++;
            }
        }
        // 放入入度为0的队列
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < v; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        if (!queue.isEmpty()) {
            // 输出并将可达的其他顶点入度减掉1
            int i = queue.remove();
            System.out.println("->" + i);
            for (int j = 0; j < adj[i].size(); j++) {
                int k = adj[i].get(j);
                inDegree[k]--;
                if (inDegree[k] == 0) {
                    queue.add(k);
                }
            }
        }
    }

    /**
     * 深度优先遍历，遍历图中的所有顶点，而非只是搜索一个顶点到另一个顶点的路径
     * 每个顶点被访问两次，每条边都被访问一次，所以时间复杂度也是 O(V+E)。
     * @param graph
     */
    public void topoSortByDFS(DGraph graph) {
        int v = graph.getV();
        LinkedList<Integer>[] adj = graph.getAdj();
        // 构造逆邻接表，边s->t表示，s依赖于t,t先于s
        LinkedList<Integer>[] inverseAdj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            inverseAdj[i] = new LinkedList<>();
        }
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < adj[i].size(); j++) {
                int w = adj[i].get(j);
                inverseAdj[w].add(i);
            }
        }
        boolean[] visited = new boolean[v];
        // 深度优先遍历图
        for (int i = 0; i < v; i++) {
            if (visited[i] == false) {
                visited[i] = true;
                dfs(i, inverseAdj, visited);
            }
        }
    }

    private void dfs(int vertex, LinkedList<Integer>[] inverseAdj, boolean[] visited) {
        // 先把vertex这个顶点可达的所有顶点都打印出来之后，再打印它自己
        for (int i = 0; i < inverseAdj[vertex].size(); i++) {
            int w = inverseAdj[vertex].get(i);
            if (visited[w] == true) {
                continue;
            }
            visited[w] = true;
            dfs(w, inverseAdj, visited);
        }
        System.out.println("->" + vertex);
    }
}
