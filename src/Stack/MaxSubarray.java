package Stack;

public class MaxSubarray {
    static int maxSum = 0;

    public static int maxSubArray(int[] nums) {
        process(nums, nums.length - 1);
        return maxSum;
    }

    public static int process(int[] nums, int i) {
        if (i == 0) {
            maxSum = nums[0];
            return nums[0];
        }
        int pre = process(nums, i - 1);
        int maxNow = nums[i] + (Math.max(pre, 0));
        if (maxSum < maxNow) {
            maxSum = maxNow;
        }
        return maxNow;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(arr));
    }
}
