package String;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 双指针 滑动窗口
public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        Map<Character, Integer> tRecord = new HashMap<>();

        for (char c : tArr) {
            if (!tRecord.containsKey(c)) {
                tRecord.put(c, 1);
            } else {
                tRecord.put(c, tRecord.get(c) + 1);
            }
        }

        int i = 0, j = 0;
        int min = sArr.length + 1;
        String res = "";

        while (j <= sArr.length) {

            if (isSatisfied(tRecord)) { // 当前遍历的window内部已经满足了条件
                while (i < sArr.length && !tRecord.containsKey(sArr[i])) {
                    i++;
                }

                if (j - i < min) {
                    min = j - i;
                    res = new String(Arrays.copyOfRange(sArr, i, j));
                }

                if (i < sArr.length && tRecord.containsKey(sArr[i])) {
                    tRecord.put(sArr[i], tRecord.get(sArr[i]) + 1);
                    i++;
                }
                while (i < sArr.length && !tRecord.containsKey(sArr[i])) {
                    i++;
                }
            } else { // 未满足条件
                if (j == sArr.length) {
                    break;
                }
                if (tRecord.containsKey(sArr[j])) {
                    tRecord.put(sArr[j], tRecord.get(sArr[j]) - 1);
                }
                j++; // 右边界继续找
            }
        }

        return res;
    }

    private boolean isSatisfied(Map<Character, Integer> tRecord) {
        for (char key : tRecord.keySet()) {
            if (tRecord.get(key) > 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new MinimumWindowSubstring().minWindow("bba", "ab"));
    }
}
