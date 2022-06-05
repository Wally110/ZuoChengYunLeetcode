package BiTree;

import BiTree.TreeNode;

public class HouseRobberIII {
    static class Info {
        int moneyWithHead; // 当前节点来时的，最大收益
        int moneyWithoutHead; // 当前节点不来时的，最大收益

        public Info(int moneyWithHead, int moneyWithoutHead) {
            this.moneyWithHead = moneyWithHead;
            this.moneyWithoutHead = moneyWithoutHead;
        }
    }

    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Info res = process(root);

        return Math.max(res.moneyWithHead, res.moneyWithoutHead);
    }

    public Info process(TreeNode root) {
        if (root == null) {
            return null;
        }

        int moneyWithHead;
        int moneyWithoutHead;

        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);

        if (leftInfo != null && rightInfo != null) {
            moneyWithHead = root.val + leftInfo.moneyWithoutHead + rightInfo.moneyWithoutHead;
            moneyWithoutHead = Math.max(leftInfo.moneyWithHead, leftInfo.moneyWithoutHead)
                    + Math.max(rightInfo.moneyWithHead, rightInfo.moneyWithoutHead);
        } else if (leftInfo == null && rightInfo != null) {
            moneyWithHead = root.val + rightInfo.moneyWithoutHead;
            moneyWithoutHead = Math.max(rightInfo.moneyWithHead, rightInfo.moneyWithoutHead);
        } else if (leftInfo != null && rightInfo == null) {
            moneyWithHead = root.val + leftInfo.moneyWithoutHead;
            moneyWithoutHead = Math.max(leftInfo.moneyWithHead, leftInfo.moneyWithoutHead);
        } else {
            moneyWithHead = root.val;
            moneyWithoutHead = 0;
        }

        return new Info(moneyWithHead, moneyWithoutHead);

    }
}
