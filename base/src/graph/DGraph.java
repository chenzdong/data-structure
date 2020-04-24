package graph;

import java.util.LinkedList;

/**
 * 邻接表实现有向无环图
 *
 * @author czd
 * @create 2019-12-10 20:47
 **/
public class DGraph {
    /**
     * 顶点的个数
     */
    private  int v;
    /**
     * 邻接表
     */
    private LinkedList<Integer>[] adj;
    /**
     * 特殊的图 顶点的值就是i的值
     */
    public DGraph(int v) {
        this.v = v;
        this.adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s, int t) {
        adj[s].add(t);
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    public LinkedList<Integer>[] getAdj() {
        return adj;
    }

    public void setAdj(LinkedList<Integer>[] adj) {
        this.adj = adj;
    }
}

