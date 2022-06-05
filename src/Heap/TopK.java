package Heap;

import java.util.PriorityQueue;

// 题目要求返回排序后第k大的数，而非第k大不同的数。所以不需要去重
public class TopK {
    public static int process(int[] arr, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(k); // 默认小根堆
        for (int value : arr) {
            if (heap.size() < k) {
                heap.offer(value);
            } else {
                if (value >= heap.peek()) {
                    heap.poll();
                    heap.offer(value);
                }
            }
        }
        return heap.peek(); // 小根堆的头结点是第k大
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 2, 1, 5, 6, 4};
        System.out.println(process(arr, 2));
    }
}
