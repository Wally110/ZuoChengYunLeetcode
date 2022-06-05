package Graph;

import java.util.HashMap;
import java.util.HashSet;

// 采用邻接表作为图的模板
public class Graph {
    public HashMap<Integer, Node> nodes; // 节点
    public HashSet<Edge> edges; // 边

    public Graph() {
        this.nodes = new HashMap<Integer, Node>();
        this.edges = new HashSet<Edge>();
    }
}
