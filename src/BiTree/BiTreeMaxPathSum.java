package BiTree;// Definition for a binary tree node.


import BiTree.TreeNode;

public class BiTreeMaxPathSum {
    public static int maxPathSum(TreeNode root) {
        Info info = process(root);
        return info.longSum;
    }

    public static Info process(TreeNode root) {
        if (root == null) {
            return null;
        }

        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);

        int longSumWithRoot = 0;
        int longSum = 0;

        if (leftInfo == null && rightInfo == null) {
            longSumWithRoot = root.val;
            longSum = longSumWithRoot;

        } else if (leftInfo != null && rightInfo == null) {
            longSumWithRoot = Math.max(root.val, root.val + leftInfo.longSumWithRoot);
            longSum = Math.max(longSumWithRoot, leftInfo.longSum);

        } else if (leftInfo == null && rightInfo != null) {
            longSumWithRoot = Math.max(root.val, root.val + rightInfo.longSumWithRoot);
            longSum = Math.max(longSumWithRoot, rightInfo.longSum);

        } else {
            longSumWithRoot = Math.max(root.val, root.val + leftInfo.longSumWithRoot);
            longSumWithRoot = Math.max(longSumWithRoot, root.val + rightInfo.longSumWithRoot);

            longSum = Math.max(leftInfo.longSum, rightInfo.longSum);
            longSum = Math.max(longSum, longSumWithRoot);
            longSum = Math.max(longSum, root.val + leftInfo.longSumWithRoot + rightInfo.longSumWithRoot);
        }

        return new Info(longSum, longSumWithRoot);
    }


    public static class Info {
        public int longSum; // 最大距离
        public int longSumWithRoot; // 包含头节点的最大距离

        public Info(int longSum, int longSumWithRoot) {
            this.longSum = longSum;
            this.longSumWithRoot = longSumWithRoot;
        }
    }

    public static void main(String[] args) {

    }
}
