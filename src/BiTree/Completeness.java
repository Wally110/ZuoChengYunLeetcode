package BiTree;

import java.util.LinkedList;
import java.util.Queue;

public class Completeness {
    public boolean isCompleteTree(Node root) {
        if (root == null) {
            return true;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        boolean flag = false;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (cur.left == null && cur.right != null) {
                return false;
            }

            if (flag && !isLeaf(root)) {
                return false;
            }

            if (!flag && isUncomplete(cur)) {
                flag = true;
            }

            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }

        }

        return true;

    }

    public boolean isUncomplete(Node cur) {
        return cur.left == null && cur.right == null || cur.left != null && cur.right == null;
    }

    public boolean isLeaf(Node cur) {
        return cur.left == null && cur.right == null;
    }
}
