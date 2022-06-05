package Util;

import java.util.Arrays;

public class Heap {
    private final int[] arr;

    public Heap(int[] arr) {
        this.arr = arr;
        buildHeap(this.arr);
    }

    private void buildHeap(int[] arr) {
        if (arr.length == 1) {
            return;
        }
        int end = arr.length / 2 - 1;

        for (int i = end; i >= 0; i--) {
            adjustDown(i, arr.length - 1);
        }

    }

    // j 是接受调整的右边界，闭区间
    private void adjustDown(int i, int j) { // also called heapify
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int maxSub;

        if (right > j && left <= j) {
            maxSub = left;
        } else if (left > j) {
            return;
        } else {
            maxSub = arr[left] > arr[right] ? left : right;
        }

        if (arr[i] < arr[maxSub]) { // 不满足大根堆要求
            swap(i, maxSub);
        }

        if (maxSub <= arr.length / 2 - 1) {
            adjustDown(maxSub, j);
        }

    }

    public int[] sort() { // 堆排序
        int j = arr.length - 1; // j是锁定的边界

        while (j > 0) { // 当有只剩一个元素后，就不用进行以下调整了
            swap(0, j);
            j--;
            adjustDown(0, j);
        }
        return arr;
    }

    private void swap(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        Arrays.stream(new Heap(new int[]{3}).sort()).forEach(System.out::print);

    }
}