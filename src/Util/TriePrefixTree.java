package Util;

import java.util.HashMap;

class TrieNode {
    char val; // 节点值
    int p; // 经过当前节点的字符串个数
    int e; // 以当前节点为结束的字符串个数
    HashMap<Character, TrieNode> nexts; // 当前节点的下一节点

    public TrieNode(char val) {
        this.val = val;
        p = 0;
        e = 0;
        nexts = new HashMap<>();
    }
}

public class TriePrefixTree {
    TrieNode root;

    public TriePrefixTree() {
        root = new TrieNode('#');
    }

    public void insert(String word) {
        char[] arr = word.toCharArray();
        TrieNode cur = root;
        root.p++;
        for (int i = 0; i < arr.length; i++) {
            if (cur.nexts.containsKey(arr[i])) {
                cur = cur.nexts.get(arr[i]);
                cur.p++;
                if (i == arr.length - 1) {
                    cur.e++;
                }
            } else {
                TrieNode next = new TrieNode(arr[i]);
                next.p++;
                if (i == arr.length - 1) {
                    next.e++;
                }
                cur.nexts.put(arr[i], next);
                cur = next;
            }

        }
    }

    public boolean search(String word) {
        char[] arr = word.toCharArray();
        TrieNode cur = root;
        for (char c : arr) {
            if (cur.nexts.containsKey(c)) {
                cur = cur.nexts.get(c);
            } else {
                return false;
            }
        }
        return cur.e > 0;
    }

    public boolean startsWith(String prefix) {
        char[] arr = prefix.toCharArray();
        TrieNode cur = root;
        for (char c : arr) {
            if (cur.nexts.containsKey(c)) {
                cur = cur.nexts.get(c);
            } else {
                return false;
            }
        }
        return cur.p > 0;
    }

    public static void main(String[] args) {
        TriePrefixTree trie = new TriePrefixTree();

        trie.insert("apple");
        System.out.println(trie.search("apple"));   // return True
        System.out.println(trie.search("app"));     // return False
        System.out.println(trie.startsWith("app"));       // return True
        trie.insert("app");
        System.out.println(trie.search("app"));     // return True
    }
}
