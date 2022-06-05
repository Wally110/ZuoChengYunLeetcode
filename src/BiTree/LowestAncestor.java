package BiTree;

import java.util.HashMap;
import java.util.HashSet;

public class LowestAncestor {
    public static HashMap<Node, Node> parentMap = new HashMap<>();

    public static void traverse(Node head) {
        if (head == null) {
            return;
        }
        if (head.left != null) {
            parentMap.put(head.left, head);
            traverse(head.left);
        }
        if (head.right != null) {
            parentMap.put(head.right, head);
            traverse(head.right);
        }
    }

    public static Node lca1(Node head, Node o1, Node o2) {
        traverse(head);
        parentMap.put(head, head);

        HashSet<Node> parentSet = new HashSet<>(); // 记录o1的祖先们
        parentSet.add(o1); // o1也是自身的祖先
        Node parent = parentMap.get(o1);
        while (parent != null) {
            parentSet.add(parent);
            parent = parentMap.get(parent);
        }

        Node o2Parent = parentMap.get(o2);
        while (!parentSet.contains(o2Parent)) { // 跳出条件：o2的祖先出现在o1的祖先集合中
            o2Parent = parentMap.get(o2Parent);
        }
        return o2Parent;
    }

    public static Node lca2(Node head, Node o1, Node o2) {
        if (head == null || head == o1 || head == o2) { // 当前节点如果为空，就返回空。o1就返回o1。o2就返回o2
            return head;
        }
        Node left = lca2(head.left, o1, o2);
        Node right = lca2(head.right, o1, o2); // 分别从左右孩子要信息
        if (left != null && right != null) { // 左右子树均返回了不为空的结果，则当前节点为最低公共祖先。
            return head;
        }
        return left == null ? right : left; // 返回左右子数回传的不为null的结果。如果均为空，则返回空
    }
}
