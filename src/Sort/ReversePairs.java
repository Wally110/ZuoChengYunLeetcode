package Sort;

import java.util.Arrays;

public class ReversePairs {
    int cnt = 0;

    public int reversePairs(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return 0;
        }
        mergeSort(nums, 0, nums.length - 1);
        return cnt;
    }

    private int[] mergeSort(int[] arr, int i, int j) {
        if (i == j) {
            return Arrays.copyOfRange(arr, i, j + 1);
        }
        int mid = (i + j) / 2;
        int[] left = mergeSort(arr, i, mid);
        int[] right = mergeSort(arr, mid + 1, j);
        return merge(left, right);
    }

    private int[] merge(int[] left, int[] right) {
        int len1 = left.length;
        int len2 = right.length;

        int[] res = new int[len1 + len2];
        int index = 0;
        int p1 = 0, p2 = 0;

        while (p1 < len1 && p2 < len2) {
            if (left[p1] <= right[p2]) {
                res[index] = left[p1];
                index++;
                p1++;
            } else {
                res[index] = right[p2];
                index++;
                p2++;
                cnt += len1 - p1;
            }
        }
        while (p1 < len1) {
            res[index] = left[p1];
            index++;
            p1++;
        }
        while (p2 < len2) {
            res[index] = right[p2];
            index++;
            p2++;
        }

        return res;
    }

    public static void main(String[] args) {
        int res = new ReversePairs().reversePairs(new int[]{7, 5, 6, 4});
        System.out.println(res);
    }
}
