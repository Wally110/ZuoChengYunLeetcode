package BiSearch;

import java.util.Arrays;

class Solution7 {
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];

        // 二分查找左边界
        res[0] = findLeft(nums, target);
        // 二分查找右边界
        res[1] = findRight(nums, target);

        return res;
    }

    public int findLeft(int[] nums, int target) {
        if (nums[0] == target) {
            return 0;
        }

        int i = 0, j = nums.length - 1;
        int mid = 0;

        while (i <= j) {
            mid = i + (j - i) / 2;

            if (i == j) {
                return nums[mid] == target && nums[mid - 1] != target ? mid : -1;
            }

            if (nums[mid] == target && nums[mid - 1] != target) {
                return mid;
            } else if (nums[mid] >= target) {
                j = mid - 1;
            } else {
                i = mid + 1;
            }
        }

        return -1;
    }

    public int findRight(int[] nums, int target) {
        if (nums[nums.length - 1] == target) {
            return nums.length - 1;
        }

        int i = 0, j = nums.length - 1;
        int mid = 0;

        while (i <= j) {
            mid = i + (j - i) / 2;

            if (i == j) {
                return nums[mid] == target && nums[mid + 1] != target ? mid : -1;
            }

            if (nums[mid] == target && nums[mid + 1] != target) {
                return mid;
            } else if (nums[mid] > target) {
                j = mid - 1;
            } else {
                i = mid + 1;
            }
        }

        return -1;
    }
}

public class FindFstAndLast {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution7().searchRange(new int[]{1,2,3}, 1)));
    }
}
