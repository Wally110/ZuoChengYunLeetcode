package BiSearch;

public class FindDuplicateNumII {
    public int findDuplicate2(int[] nums) {
        int slow = 0;
        int fast = 0;

        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        fast = 0;

        do {
            slow = nums[slow];
            fast = nums[fast];
        } while (slow != fast);

        return fast;
    }

    public int findDuplicate(int[] nums) {
        int n = nums.length - 1;
        int l = 1, r = n;
        int res = -1;

        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            int cnt = getCntSmallerThan(nums, mid);
            if (cnt <= mid) {
                l = mid + 1;
            } else {
                r = mid - 1;
                res = mid;
            }

        }

        return res;

    }

    public int getCntSmallerThan(int[] nums, int a) {
        int cnt = 0;
        for (int num : nums) {
            if (num <= a) {
                cnt++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        System.out.println(new FindDuplicateNumII().findDuplicate2(new int[]{1, 3, 4, 2, 2}));
    }
}
