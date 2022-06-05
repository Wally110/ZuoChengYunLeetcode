package Recursion;

import java.util.ArrayList;
import java.util.List;

class Solution3 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        process(candidates, res, ans, 0, target); // 为快速实现，首先使用暴力递归
        return res;
    }

    public void process(int[] candidates, List<List<Integer>> res, List<Integer> ans, int i, int rest) {
        if (rest == 0) {
            List<Integer> newAns = new ArrayList<>(ans);
            res.add(newAns);
            return;
        }
        if (rest < 0 || i >= candidates.length) {
            return;
        }

        for (int k = 0; k * candidates[i] <= rest; k++) {
            int kk = k;
            while (kk-- > 0) {
                ans.add(candidates[i]);
            }
            process(candidates, res, ans, i + 1, rest - k * candidates[i]);
            int kkk = k;
            while (kkk-- > 0) { // 回溯
                ans.remove(ans.size() - 1);
            }
        }
    }
}

public class CombinationSum {
    public static void main(String[] args) {
        int[] a = {2, 3, 6, 7};
        int target = 7;
        List<List<Integer>> result = new Solution3().combinationSum(a, target);
        for (List<Integer> l : result) {
            System.out.println(l.toString());
        }
    }
}
