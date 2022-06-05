package BiTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class MaximumWidth {
    public int widthOfBinaryTree(Node root) {
        if (root == null) {
            return 0;
        }

        Queue<Node> nodeQueue = new LinkedList<>();
        Queue<Integer> labelQueue = new LinkedList<>();
        nodeQueue.offer(root);
        labelQueue.offer(0);
        int res = 0;

        while (!nodeQueue.isEmpty()) {
            int len = nodeQueue.size();
            ArrayList<Integer> width = new ArrayList<>();

            for (int i = 0; i < len; i++) { // 每次遍历一行。省去了存储行信息。
                Node cur = nodeQueue.poll();
                int label = labelQueue.poll();

                width.add(label);

                if (cur.left != null) {
                    nodeQueue.offer(cur.left);
                    labelQueue.offer(2 * label);
                }

                if (cur.right != null) {
                    nodeQueue.offer(cur.right);
                    labelQueue.offer(2 * label + 1);
                }
            }

            res = Math.max(res, width.get(width.size() - 1) - width.get(0) + 1);
        }

        return res;
    }
}
