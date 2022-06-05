package BiSearch;

public class MinimumInRotaredSortedArray {
    public int findMin(int[] nums) {
        int l = 0;
        int r = nums.length - 1;

        while (l <= r) {
            int mid = l + ((r - l) >> 1);

            if ((mid - 1 < l || nums[mid - 1] > nums[mid]) && (mid + 1 > r || nums[mid + 1] > nums[mid])) {
                return nums[mid];
            } else {
                if (nums[mid] < nums[l]) {
                    r = mid - 1;
                } else if (nums[mid] > nums[r]) {
                    l = mid + 1;
                } else {
                    return nums[l];
                }
            }
        }

        return -1;

    }

    public static void main(String[] args) {
        System.out.println(new MinimumInRotaredSortedArray().findMin(new int[]{3, 4, 5, 1, 2}));
    }
}
