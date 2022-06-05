package Recursion;

import java.util.HashMap;

public class LongestIncreasingSubSeq {
    int maxLen = 0;
    HashMap<Integer, Integer> dp = new HashMap<>();

    public int lengthOfLIS(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }

        process(nums, nums.length - 1);
        return maxLen;
    }

    public int process(int[] nums, int i) { // 以nums[i]为最后一个元素的最长递增子序列
        if (!dp.containsKey(i)) {
            if (i == 0) {
                return 1;
            }

            int len;
            int max = 0;

            for (int k = 0; k < i; k++) {
                len = process(nums, k); // 就算不满足nums[i] > nums[k]，也要通过递归计算一下。
                if (nums[i] > nums[k]) {
                    max = Math.max(max, len);
                }
            }
            maxLen = Math.max(maxLen, max + 1);

            dp.put(i, max + 1);
        }
        return dp.get(i);
    }

    public static void main(String[] args) {
        System.out.println(new LongestIncreasingSubSeq().lengthOfLIS(new int[]{1, 3, 6, 7, 9, 4, 10, 5, 6}));
    }
}
