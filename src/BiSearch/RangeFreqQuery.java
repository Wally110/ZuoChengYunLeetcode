package BiSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RangeFreqQuery {
    HashMap<Integer, List<Integer>> map = new HashMap<>();

    public RangeFreqQuery(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], new ArrayList<Integer>());
            }
            map.get(arr[i]).add(i);
        }

    }

    public int query(int left, int right, int value) {
        if (map.containsKey(value)) {
            List<Integer> list = map.get(value);
            int cnt = 0;

            int l = binarySearch(list, left); // 二分查找，找左边界
            int r = binarySearch(list, right);
            cnt = r - l + 1;
            if (l < 0 || list.get(l) < left) { // 注意边界条件
                cnt--;
            }

            return cnt;
        }
        return 0;
    }

    public int binarySearch(List<Integer> list, int target) {
        int l = 0, r = list.size() - 1;
        int ans = -1;

        while (l <= r) {
            int mid = l + ((r - l) >> 1);

            if (list.get(mid) <= target) { // 二分查找，找左边界
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {

    }
}
