package Util;

import java.util.Arrays;

public class QuickSort {
    public static int[] quickSort(int[] arr) {
        if (arr.length == 1) {
            return arr;
        }
        qSort(arr, 0, arr.length - 1);
        return arr;
    }

    public static void qSort(int[] arr, int i, int j) {
        if (i >= j) {
            return;
        }

        int pivot = parititon(arr, i, j);
        qSort(arr, i, pivot - 1);
        qSort(arr, pivot + 1, j);
    }

    // 不随机的快排，776ms 36.14%
    // 随机的快排， 10ms 84.39%
    // 堆排序，18ms
    // java自带，8ms
    public static int parititon(int[] arr, int i, int j) {
        int pivot = (int) (i + Math.random() * (j - i));
        swap(arr, i, pivot);

        int mid = arr[i];

        while (i < j) {
            while (i < j && arr[j] >= mid) {
                j--;
            }
            if (i < j) {
                arr[i] = arr[j];
                i++;
            }

            while (i < j && arr[i] <= mid) {
                i++;
            }
            if (i < j) {
                arr[j] = arr[i];
                j--;
            }

        }
        arr[i] = mid;
        return i;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        Arrays.stream(QuickSort.quickSort(new int[]{5, 2, 3, 1})).forEach(System.out::print);
    }
}
