package Recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class RussianDollEnvelopes {
    int maxLen = 0;
    HashMap<Integer, Integer> dp = new HashMap<>();

    public int maxEnvelopes(int[][] envelopes) {
        int len = envelopes.length;
        if (len == 1) {
            return 1;
        }
        Arrays.sort(envelopes, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o2[1] - o1[1]);

        List<Integer> f = new ArrayList<>();
        f.add(envelopes[0][1]);
        for (int i = 1; i < len; i++) {
            int cur = envelopes[i][1];
            if (cur > f.get(f.size() - 1)) {
                f.add(cur);
            } else {
                int index = binarySearch(f, cur); // 在f中找cur的右边界
                f.set(index, cur);
            }

        }
        return f.size();
    }

    private int binarySearch(List<Integer> f, int cur) {
        int l = 0, r = f.size() - 1;
        int index = 0;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (f.get(mid) >= cur) { // 二分查找，找右边界
                index = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return index;
    }

    private void process2(int[][] envelopes, int[] dpArr) {
        for (int i = 1; i < dpArr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (envelopes[j][0] < envelopes[i][0] && envelopes[j][1] < envelopes[i][1]) {
                    dpArr[i] = Math.max(dpArr[i], dpArr[j] + 1);
                }
            }
            maxLen = Math.max(maxLen, dpArr[i]);
        }
    }

    public int process(int[][] envelopes, int i) { // 以第i个信封为最后的最长序列
        if (!dp.containsKey(i)) {
            int ans;
            if (i == 0) {
                ans = 1;
            } else {

                int max = 1, len;

                for (int j = 0; j < i; j++) {
                    len = process(envelopes, j);
                    if (envelopes[j][0] < envelopes[i][0] && envelopes[j][1] < envelopes[i][1]) {
                        max = Math.max(max, len + 1);
                    }
                }
                maxLen = Math.max(maxLen, max);
                ans = max;
            }
            dp.put(i, ans);
        }
        return dp.get(i);
    }

    public static void main(String[] args) {
        int[][] arr = {{10, 8}, {1, 12}, {6, 15}, {2, 18}};
        System.out.println(new RussianDollEnvelopes().maxEnvelopes(arr));
    }
}
