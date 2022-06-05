package Graph;

import java.util.ArrayList;

public class Node {
    public int value; // 节点值
    public int in; // 入度
    public int out; // 出度
    public ArrayList<Node> nexts; // 直接邻居节点
    public ArrayList<Edge> edges; // 从节点射出的边

    public Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<Node>();
        edges = new ArrayList<Edge>();
    }
}
