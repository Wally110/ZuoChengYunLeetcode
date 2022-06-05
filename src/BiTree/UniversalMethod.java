package BiTree;

// 二叉树递归，树形动态规划常用套路
// 每个节点分别从左孩子、右孩子获取信息，然后汇总信息后，返回自己的父节点
public class UniversalMethod {

    /**
     * 是否是 binary search tree
     */
    public static boolean isBST(Node head) {
        if (head == null) {
            return true;
        }
        return bstProcess(head).isBST;
    }

    public static class BSTReturnData {
        public int min;
        public int max;
        public boolean isBST;

        public BSTReturnData(int min, int max, boolean isBST) {
            this.min = min;
            this.max = max;
            this.isBST = isBST;
        }
    }

    public static BSTReturnData bstProcess(Node head) {
        if (head == null) {
            return new BSTReturnData(Integer.MAX_VALUE, Integer.MIN_VALUE, true);
        }
        BSTReturnData left = bstProcess(head.left);
        BSTReturnData right = bstProcess(head.right);
        boolean isBST = false;
        if (head.value >= left.max && head.value <= right.min && left.isBST && right.isBST) {
            isBST = true;
        }
        return new BSTReturnData(Math.min(left.min, head.value), Math.max(right.max, head.value), isBST);
    }

}
