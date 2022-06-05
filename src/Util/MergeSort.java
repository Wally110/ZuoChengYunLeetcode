package Util;

import java.util.Arrays;

public class MergeSort {
    public int[] sort(int[] arr, int i, int j) {
        if (i == j) {
            return Arrays.copyOfRange(arr, i, j + 1);
        }
        int mid = (i + j) / 2;
        int[] left = sort(arr, i, mid);
        int[] right = sort(arr, mid + 1, j);
        return merge(left, right);
    }

    public int[] merge(int[] left, int[] right) {
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
        int[] res = new MergeSort().sort(new int[]{7, 5, 6, 4}, 0, 3);
        Arrays.stream(res).forEach(System.out::println);
    }
}
