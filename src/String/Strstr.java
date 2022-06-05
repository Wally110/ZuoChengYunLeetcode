package String;

public class Strstr {
    public int getIndexKMP(String s, String p) {
        if (p.equals("")) {
            return 0;
        }

        char[] sArr = s.toCharArray();
        char[] pArr = p.toCharArray();
        int iS = 0, iP = 0;
        int[] next = getNextArray(pArr);

        while (iS < sArr.length && iP < pArr.length) {
            if (sArr[iS] == pArr[iP]) { // 匹配时，同暴力解
                iS++;
                iP++;
            } else if (next[iP] != -1) { // 不匹配，但是可以前跳
                iP = next[iP]; // 匹配串的指针不动
            } else { // 不匹配且不能前跳，匹配串指针后移
                iS++; // 此时iP一定等于0
            }
        }

        return iP == pArr.length ? iS - iP : -1;
    }

    // 求每个位置之前，前缀与后缀相同的最大长度
    public int[] getNextArray(char[] arr) {
        if (arr.length == 1) {
            return new int[]{-1};
        } else if (arr.length == 2) {
            return new int[]{-1, 0};
        }

        int[] nextArr = new int[arr.length];
        nextArr[0] = -1;
        nextArr[1] = 0;
        int i = 2; // 从2位置开始计算
        int k = 0; // 当前计算好的相同前缀的下一位置，是下标也是长度

        while (i < arr.length) {
            if (arr[i - 1] == arr[k]) {
                nextArr[i] = k + 1; // 相同前缀长度+1
                k = nextArr[i];
                i++;
            } else if (k > 0) { // 看k位置的前一个
                k = nextArr[k];
            } else { // k==0代表已经没有历史可以参考了
                nextArr[i] = 0;
                i++;
            }
        }
        return nextArr;
    }

    public static void main(String[] args) {
        System.out.println(new Strstr().getIndexKMP("hello", "ll"));
    }

}
