package Heap;

import java.util.PriorityQueue;

public class MedianFinder {
    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;
    int cnt;


    public MedianFinder() {
        cnt = 0;
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
    }

    public void addNum(int num) {
        cnt++;
        if (cnt == 1) {//第一个元素，放入大根堆
            maxHeap.offer(num);
        } else {
            if (maxHeap.size() > 0 && num > maxHeap.peek()) { // 比大根堆中堆顶大的元素
                minHeap.add(num); // 放入小根堆
            } else {
                maxHeap.add(num);
            }
        }
        if (maxHeap.size() - minHeap.size() >= 2) {
            minHeap.offer(maxHeap.poll());
        } else if (minHeap.size() - maxHeap.size() >= 2) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {
        int maxHeapPeek = maxHeap.size() == 0 ? 0 : maxHeap.peek();
        int minHeapPeek = minHeap.size() == 0 ? 0 : minHeap.peek();
        if ((cnt & 1) == 1) { // 奇数
            return maxHeap.size() > minHeap.size() ? maxHeapPeek : minHeapPeek;
        } else { // 偶数
            return (maxHeapPeek + minHeapPeek) / 2.0;
        }

    }
}