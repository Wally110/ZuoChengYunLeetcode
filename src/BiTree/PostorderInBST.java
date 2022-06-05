package BiTree;

import java.util.Arrays;

public class PostorderInBST {
    public boolean verifyPostorder(int[] postorder) {
        if (postorder.length == 0) {
            return true;
        }

        int[] inorder = Arrays.copyOf(postorder, postorder.length); // 拷贝新数组
        Arrays.sort(inorder);

        int head = postorder[postorder.length - 1];
        int headIndex = -1; // 头结点在中序遍历中的位置

        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == head) {
                headIndex = i;
                break;
            }
        }

        if (headIndex == -1) { // 如果头结点不存在与中序遍历中，那么不满足
            return false;
        }

        return verify(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1);

    }


    public boolean verify(int[] postorder, int i, int j, int[] inorder, int a, int b) { // 根据后序遍历和中序遍历建立二叉树
        if (i == j && a == b) { // 只剩一个节点
            return postorder[i] == inorder[a];
        } else if (i > j && a > b) { // 说明左子树或右子树为空
            return true;
        }

        int head = postorder[j];
        int headIndex = -1;

        for (int k = a; k <= b; k++) {
            if (inorder[k] == head) {
                headIndex = k;
                break;
            }
        }

        if (headIndex == -1) {
            return false;
        }

        int leftTreeLen = headIndex - a; // 通过中序遍历，得出左子树的长度

        boolean left = verify(postorder, i, i + leftTreeLen - 1, inorder, a, headIndex - 1);
        boolean right = verify(postorder, i + leftTreeLen, j - 1, inorder, headIndex + 1, b);
        return left && right;
    }

    public static void main(String[] args) {
        System.out.println(new PostorderInBST().verifyPostorder(new int[]{4, 6, 7, 5}));
    }
}
