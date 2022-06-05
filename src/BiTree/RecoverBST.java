package BiTree;

import java.util.LinkedList;

public class RecoverBST {
    LinkedList<TreeNode> candidate = new LinkedList<>();
    TreeNode lastNode;

    public void recoverTree(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root);
        if (!candidate.isEmpty()) {
            swap(candidate.peekFirst(), candidate.peekLast());
        }
    }

    public void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        if (lastNode != null && root.val < lastNode.val) {
            candidate.add(lastNode);
            candidate.add(root);
        }
        lastNode = root;
        inorder(root.right);
    }

    public void swap(TreeNode a, TreeNode b) {
        int temp = a.val;
        a.val = b.val;
        b.val = temp;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2);

        new RecoverBST().recoverTree(root);
    }
}
// 当前节点与左右子树节点比较，将异常节点替换的方案是不可行的。
