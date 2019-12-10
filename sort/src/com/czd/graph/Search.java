package com.czd.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 图的搜索算法
 *
 * @author: czd
 * @create: 2019-12-04 11:05
 */
public class Search {

    // 全局变量记录是否找到终止节点
    boolean found = false;
    /**
     * 从s-->t的广度搜索算法
     * @param s 初始顶点
     * @param t 待找寻的顶点
     * 空间复杂度O(V)
     * 时间复杂度O(V+E) 因为 E>V-1 -->O(E)
     */
    public void bfs(Graph graph, int s, int t) {
        if (s == t) {
            return;
        }
        LinkedList<Integer>[] adj = graph.getAdj();
        // 存储访问记录，visited[i] = true 代表i顶点已经被访问过，避免重复访问
        boolean[] visited = new boolean[graph.getV()];
        // 置为被访问
        visited[s] = true;
        // 存储被访问 未被获取下一节点的顶点
        Queue<Integer> queue = new LinkedBlockingQueue<>();
        queue.add(s);
        // 存储节点i的前置节点
        int[] prev = new int[graph.getV()];
        for (int i = 0; i < prev.length; i++) {
            prev[i] = -1;
        }
        // queue中有值，需要进一步遍历，当找到t顶点时，跳出循环
        while (queue.size() != 0) {
            int w = queue.poll();
            // 取出w节点的相邻节点
            for (int i = 0; i < adj[w].size(); i++) {
                int q = adj[w].get(i);
                // 没被访问过
                if (!visited[q]) {
                    prev[q] = w;
                }
                // q == t找到了退出循环，打印路径
                if (q == t) {
                    print(prev, s ,t);
                    return;
                }
                visited[q] = true;
                queue.add(q);
            }
        }
    }

    /**
     * 从s-->t深度优先搜索
     * @param graph
     * @param s
     * @param t
     * 空间复杂度O(V)
     * 时间复杂度O(V+E) 因为 E>V-1 -->O(E)
     */
    public void dfs(Graph graph, int s, int t) {
        found = false;
        LinkedList<Integer>[] adj = graph.getAdj();
        // 该数组记录访问过的节点
        boolean[] visited = new boolean[graph.getV()];
        // 记录前置节点 便于打印路径
        int[] prev = new int[graph.getV()];
        for (int i = 0; i < prev.length; i++) {
            prev[i] = -1;
        }
        recurDfs(adj, s, t, visited, prev);
        print(prev, s, t);

    }

    private void recurDfs(LinkedList<Integer>[] adj, int w, int t, boolean[] visited, int[] prev) {
        if (found == true) {
            return;
        }
        visited[w] = true;
        if (w == t) {
            found = true;
            return;
        }
        for (int i = 0; i < adj[w].size(); i++) {
            if (found == true) {
                break;
            }
            int q = adj[w].get(i);
            if (!visited[q]) {
                prev[q] = w;
                recurDfs(adj, q, t, visited, prev);
            }
        }
    }


    private void print(int[] prev, int s, int t) {
        if (prev[t] != -1 && t != s) {
            print(prev, s, prev[t]);
        }
        System.out.println(t + "");
    }



    public static void main(String[] args) {
        Graph graph = new Graph(8);
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(3, 4);
        graph.addEdge(2, 5);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);
        graph.addEdge(5, 7);
        graph.addEdge(6, 7);
        Search search = new Search();
//        search.bfs(graph,7, 4);
        search.dfs(graph,1, 4);
    }
}
