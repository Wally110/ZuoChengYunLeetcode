package Recursion;

import java.util.HashMap;

public class EditDistance {
    HashMap<String, Integer> dp = new HashMap<>();

    public int minDistance(String word1, String word2) {
        char[] w1 = word1.toCharArray();
        char[] w2 = word2.toCharArray();

        if (w1.length == 0) {
            return w2.length;
        } else if (w2.length == 0) {
            return w1.length;
        }

        return process(w1, w1.length - 1, w2, w2.length - 1);
    }

    private int process(char[] w1, int i, char[] w2, int j) {
        if (!dp.containsKey(i + ":" + j)) {
            int ans = 0;
            if (i < 0) {
                ans = j + 1;// 长度为坐标+1
            } else if (j < 0) {
                ans = i + 1;
            } else {
                int m1 = process(w1, i - 1, w2, j) + 1; // word1插入一个
                int m2 = process(w1, i, w2, j - 1) + 1; // word2插入一个
                int m3 = process(w1, i - 1, w2, j - 1); // word1替换一个
                if (w1[i] != w2[j]) {
                    m3++;
                }
                ans = Math.min(m1, Math.min(m2, m3));
            }

            dp.put(i + ":" + j, ans);
        }

        return dp.get(i + ":" + j);
    }


    public static void main(String[] args) {
        System.out.println(new EditDistance().minDistance("intention", "execution"));
    }
}

