package Queue;

import java.util.*;

public class SlidingWindowMax {
    // 双端队列滑动窗口
    // 窗口内最大值、最小值更新结构
    public int[] maxSlidingWindow(int[] nums, int k) {
        int l = 0;
        int r = 0;
        Deque<Integer> deque = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        int step = 0; // 前k步，只计算右边界，而后同时计算左边界和右边界。k步之后，开始记录窗口内最大值

        while (r < nums.length) {
            if (step < k) { // 前k步
                if (deque.size() > 0 && nums[deque.peekLast()] > nums[r] || deque.size() == 0) {
                    deque.offerLast(r);
                } else {
                    while (deque.size() > 0 && nums[deque.peekLast()] < nums[r]) {
                        deque.pollLast();
                    }
                    deque.offerLast(r);
                }
                r++;
            } else { // 第k步之后
                // 先更新右边界
                if (deque.size() > 0 && nums[deque.peekLast()] > nums[r] || deque.size() == 0) {
                    deque.offerLast(r);
                } else {
                    while (deque.size() > 0 && nums[deque.peekLast()] < nums[r]) {
                        deque.pollLast();
                    }
                    deque.offerLast(r);
                }
                // 再更新左边界
                if (deque.size() > 0 && nums[deque.peekFirst()] == nums[l]) {
                    deque.pollFirst();
                }
                // 更新边界
                r++;
                l++;
            }
            if (r - l == k) { // 窗口内有k个值时，就可以开始记录了
                res.add(nums[deque.peekFirst()]);
            }
            step++;
        }
        return res.stream().mapToInt(Integer::intValue).toArray(); // List<Integer> 转int[]
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,3,-1,-3,5,3,6,7};
        int k = 3;
        int[] res = new SlidingWindowMax().maxSlidingWindow(nums, k);
        System.out.println(Arrays.toString(res));
    }
}
