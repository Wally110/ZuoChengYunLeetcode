package Graph;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Func {

    /**
     * 将多样的图表示转化为固定模板
     */
    public static Graph createGraph(Integer[][] matrix) {
        Graph graph = new Graph();
        for (Integer[] integers : matrix) {
            Integer from = integers[0];
            Integer to = integers[1];
            Integer weight = integers[2];

            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }

            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge newEdge = new Edge(weight, fromNode, toNode);
            fromNode.nexts.add(toNode);
            fromNode.edges.add(newEdge);
            fromNode.out++;
            toNode.in++;
            graph.edges.add(newEdge);
        }
        return graph;
    }

    /**
     * breadth-first search
     */
    public static void BFS(Graph graph) {
        Queue<Node> queue = new LinkedList<Node>();
        HashSet<Node> visitedSet = new HashSet<Node>();

        Node firstNode = graph.nodes.get(0);
        queue.add(firstNode);
        visitedSet.add(firstNode);

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.value);
            visitedSet.add(cur);
            for (Node next : cur.nexts) {
                if (!visitedSet.contains(next)) {
                    queue.add(next);
                }
            }
        }

    }

    /**
     * recursive depth-first search
     */
    public static HashSet<Node> visitedSet = new HashSet<Node>();

    public static void recursiveDFS(Graph graph, Integer i) {
        Node cur = graph.nodes.get(i);
        if (cur == null) {
            return;
        }
        visitedSet.add(cur);
        System.out.println(cur.value);
        for (Node next : cur.nexts) {
            if (!visitedSet.contains(next)) {
                recursiveDFS(graph, next.value);
            }
        }

    }

    /**
     * iterative depth-first search
     */
    public static void iterativeDFS(Graph graph) {
        Stack<Node> stack = new Stack<Node>();
        HashSet<Node> visitedSet = new HashSet<Node>();

        Node firstNode = graph.nodes.get(0);
        System.out.println(firstNode.value); // 压栈之前访问
        visitedSet.add(firstNode);
        stack.push(firstNode);

        while (!stack.empty()) {
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                if (!visitedSet.contains(next)) {
                    System.out.println(next);
                    stack.push(cur); // 当前节点再次入栈
                    stack.push(next);
                }
            }
        }

    }
}
