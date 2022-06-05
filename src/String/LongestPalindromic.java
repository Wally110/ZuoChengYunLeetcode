package String;

public class LongestPalindromic {
    public String longestPalindrome(String s) {
        char[] arr = s.toCharArray();
        char[] sharpedArr = new char[2 * arr.length + 1];
        int i = 0;
        for (char c : arr) {
            sharpedArr[i++] = c;
            sharpedArr[i++] = '#';
        }

        return manacher(sharpedArr);
    }

    private String manacher(char[] arr) {
        int len = arr.length;
        int maxC = 0; // 最大回文的中心
        int maxR = 0; // 最大回文的回文半径

        int[] record = new int[len];
        record[0] = 0; // 0位置的回文半径是0

        for (int i = 1; i < len; i++) {
            if (i > maxC + maxR) { // 当前节点在最大回文半径之外，暴力解
                int r = 1;
                while (i - r >= 0 && i + r < len && arr[i + r] == arr[i - r]) {
                    record[i] = r;
                    r++;
                }
            } else { // 当前节点在最大回文半径之内
                int j = maxC - (i - maxC); // 当前节点关于最大回文中心的对称点j
                int jL = j - record[j]; // j的最左回文边界
//                int jR = j + record[j]; // j的最右回文边界

                if (jL > maxC - maxR) { // j的回文区域边界在最大回文边界内
                    record[i] = record[j];
                } else if (jL < maxC - maxR) { // j的回文区域边界在最大回文边界外
                    record[i] = j - (maxC - maxR);
                } else { // j的回文区域边界与最大回文边界重合
                    int r = j - (maxC - maxR);
                    while (i - r >= 0 && i + r < len && arr[i + r] == arr[i - r]) {
                        record[i] = r;
                        r++;
                    }
                }

            }
            if (record[i] > maxR) {
                maxR = record[i];
                maxC = i;
            }
        }

        StringBuilder res = new StringBuilder();
        for (int i = maxC - maxR; i <= maxC + maxR; i++) {
            if (arr[i] != '#') {
                res.append(arr[i]);
            }
        }

        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(new LongestPalindromic().longestPalindrome("cbbd"));
    }
}
