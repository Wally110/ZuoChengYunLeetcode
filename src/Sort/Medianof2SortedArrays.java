package Sort;

class Solution5 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int len = len1 + len2;
        int target = 0;
        int a = 0, b = 0;

        if ((len & 1) == 0) { // 偶数
            target = len / 2;
        } else {
            target = len / 2 + 1; // 2
        }
        if (len1 == 0) {
            a = nums2[target - 1];
            b = (target == len2 ? a : nums2[target]);
        } else if (len2 == 0) {
            a = nums1[target - 1];
            b = (target == len1 ? a : nums1[target]);
        } else {
            int i = 0, j = 0;
            for (int k = 0; k <= target + 1; ) {
                if (i == nums1.length || j != nums2.length && nums1[i] > nums2[j]) {
                    k++;
                    if (k == target) {
                        a = nums2[j];
                    } else if (k == target + 1) {
                        b = nums2[j];
                    }
                    j++;
                } else {
                    k++;
                    if (k == target) {
                        a = nums1[i];
                    } else if (k == target + 1) {
                        b = nums1[i];
                    }
                    i++;
                }
            }
        }
        if ((len & 1) == 0) { // 偶数
            return (a + b) / 2.0;
        } else {
            return a;
        }
    }
}

public class Medianof2SortedArrays {
    public static void main(String[] args) {
        int[] a = {1, 3};
        int[] b = {2};
        System.out.println(new Solution5().findMedianSortedArrays(a, b));
    }
}
