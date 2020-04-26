package graph;

import java.util.LinkedList;

/**
 * 图的两种遍历算法
 * i.  DFS(Depth First Search)   深度优先遍历 回溯（基于栈或递归）二叉树的前序 中序 后序 遍历
 * ii. BFS(Breadth First Search) 广度优先遍历 重放（基于队列）二叉树的层序遍历
 *
 * @author: czd
 * @create: 2019/3/25 9:14
 */
public class FirstSearch {
    /**
     * 图的顶点
     */
    private static class Vertex {
        int data;
        Vertex(int data) {
            this.data = data;
        }
    }

    private static class Graph {
        private int size;
        private Vertex[] vertexes;
        private LinkedList<Integer>[] adj;
        Graph(int size) {
            this.size = size;
            // 初始化顶点和邻接表
            vertexes = new Vertex[size];
            adj = new LinkedList[size];
            for (int i = 0; i < size; i++) {
                vertexes[i] = new Vertex(i);
                adj[i] = new LinkedList<>();
            }
        }

        /**
         * 深度优先
         * 递归
         * @param graph 图
         * @param position 当前节点
         * @param visited 标记是否访问
         */
        public static void dfs(Graph graph, int position, boolean[] visited) {
            System.out.println(graph.vertexes[position].data);
            visited[position] = true;
            for (Integer index : graph.adj[position]) {
                if (!visited[index]) {
                    dfs(graph, index, visited);
                }
            }
        }

        /**
         * 广度优先
         * 使用队列
         * @param graph 图
         * @param position 当前节点
         * @param visited 已访问过的几点
         * @param queue 队列
         */
        public static void bfs(Graph graph, int position, boolean[] visited, LinkedList<Integer> queue) {
            queue.offer(position);
            while (!queue.isEmpty()) {
                int front = queue.poll();
                if (visited[front]) {
                    continue;
                }
                System.out.println(graph.vertexes[front].data);
                visited[front] = true;
                for (int index : graph.adj[front]) {
                    queue.offer(index);
                }
            }
        }

        public static void main(String[] args) {
            Graph graph = new Graph(6);
            graph.adj[0].add(1);
            graph.adj[0].add(2);
            graph.adj[0].add(3);

            graph.adj[1].add(0);
            graph.adj[1].add(3);
            graph.adj[1].add(4);

            graph.adj[2].add(0);

            graph.adj[3].add(0);
            graph.adj[3].add(1);
            graph.adj[3].add(4);
            graph.adj[3].add(5);

            graph.adj[4].add(1);
            graph.adj[4].add(3);
            graph.adj[4].add(5);

            graph.adj[5].add(3);
            graph.adj[5].add(4);

            System.out.println("深度优先遍历:");
            dfs(graph, 0, new boolean[graph.size]);
            System.out.println("广度优先遍历:");
            bfs(graph, 0 , new boolean[graph.size], new LinkedList<Integer>());
        }

    }
}
