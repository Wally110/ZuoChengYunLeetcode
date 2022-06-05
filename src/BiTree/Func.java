package BiTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Func {

    /**
     * recursive pre-order traverse
     */
    public static void preOrder(Node head) {
        if (head == null) {
            return;
        }
        System.out.println(head.value);
        preOrder(head.left);
        preOrder(head.right);
    }

    /**
     * iterative pre-order traverse
     */
    public static void iterativePreOrder(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        while (!stack.empty()) {
            Node cur = stack.pop();
            System.out.println(cur.value);
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }

    /**
     * recursive post-order traverse
     */
    public static void postOrder(Node head) {
        if (head == null) {
            return;
        }
        postOrder(head.left);
        postOrder(head.right);
        System.out.println(head.value);
    }

    /**
     * iterative post-order traverse
     */
    public static void iterativePostOrder(Node head) {
        if (head == null) {
            return;
        }
        ArrayList<Integer> arr = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        arr.add(head.value);
        while (!stack.empty()) {
            Node cur = stack.pop();
            arr.add(cur.value);
            if (cur.left != null) {
                stack.push(cur.left);
            }
            if (cur.right != null) {
                stack.push(cur.right);
            }
        }

        Collections.reverse(arr); // 根右左->左右根
        arr.forEach(System.out::println);
    }

    /**
     * recursive mid-order traverse
     */
    public static void midOrder(Node head) {
        if (head == null) {
            return;
        }
        midOrder(head.left);
        System.out.println(head.value);
        midOrder(head.right);
    }

    /**
     * iterative mid-order traverse
     */
    public static void iterativeMidOrder(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.add(head);
        while (!stack.empty()) {
            Node cur = stack.peek();
            if (cur.left != null) { // TODO:错误！ 已经入栈的节点会频繁入栈
                stack.push(cur.left);
            } else {
                stack.pop();
                System.out.println(cur.value);
                if (cur.right != null) {
                    stack.push(cur.right);
                }
            }
        }
    }

    public static void iterativeInOrder(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        Node cur = head; // cur指向当前访问的节点

        while (cur != null || !stack.empty()) {
            while (cur != null) {
                stack.push(cur); // 只在此处入栈
                cur = cur.left;
            }


            cur = stack.pop();
            System.out.println(cur.value);

            cur = cur.right; // cur不能指向已经入栈的节点。要么指向右子树，要么为空
        }
    }

    public static void main(String[] args) {
        Node root = new Node(2, new Node(1, null, null), new Node(3, null, null));
        iterativeInOrder(root);
    }
}
