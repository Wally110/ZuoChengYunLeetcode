package BiTree;

import java.util.ArrayDeque;
import java.util.Deque;


public class BSTIterator {
    Deque<TreeNode> stack = new ArrayDeque<>();

    public BSTIterator(TreeNode root) {
        stack.push(root);
        while (stack.size() > 0 && stack.peek().left != null) {
            stack.push(stack.peek().left);
        }
    }

    public int next() {
        TreeNode cur = stack.pop();
        if (cur.right != null) {
            stack.push(cur.right);
            while (stack.size() > 0 && stack.peek().left != null) {
                stack.push(stack.peek().left);
            }
        }
        return cur.val;
    }

    public boolean hasNext() {
        return stack.size() != 0;
    }
}
