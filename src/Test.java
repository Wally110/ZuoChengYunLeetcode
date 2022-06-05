import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 6, 7, 8};
        List<Integer> l = Arrays.stream(a).boxed().collect(Collectors.toList());
        System.out.println(binarySearch2(l, 4));
    }

    static int binarySearch(List<Integer> f, int cur) {
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

    static int binarySearch2(List<Integer> f, int target) {
        int low = 0, high = f.size() - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (f.get(mid) < target) { // 二分查找，找右边界
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

}
