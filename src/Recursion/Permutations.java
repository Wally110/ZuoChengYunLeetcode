package Recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Permutations {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {

        process(nums, 0);
        return res;

    }

    public void process(int[] nums, int a) { // 从a到len替换
        if (a == nums.length) {
            res.add(Arrays.stream(nums).boxed().collect(Collectors.toList())); // int[] 转 List<Integer>
        }

        for (int i = a; i < nums.length; i++) {
            swap(nums, a, i);
            process(nums, a + 1);
            swap(nums, i, a); // 回溯
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        Permutations permutations = new Permutations();
        List<List<Integer>> res = permutations.permute(new int[]{1, 2, 3});
        for (List<Integer> ans : res) {
            ans.forEach(System.out::print);
            System.out.println();
        }
    }
}
