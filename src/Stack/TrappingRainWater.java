package Stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class TrappingRainWater {
    // 单调栈结构，可求每个元素a左右的比自己大的值和比自己小的值
    // 自底向顶递减栈，输出每个元素左右第一个比自己大的值
    // 自底向顶递增栈，输出每个元素左右第一个比自己小的值
    // 元素出栈时，确定自己的大于值或小于值。此时形成一个可计算的水洼。

    public int trap(int[] height) {
        Deque<Integer> stack = new ArrayDeque<>(); // 存下标，以保留更多信息
        int sum = 0;

        for (int i = 0; i < height.length; i++) {
            if (stack.size() == 0 || height[stack.peek()] >= height[i]) {
                stack.push(i);
            } else {
                int right = height[i]; // 右侧的比自己大的值
                while (height[stack.peek()] < right) {
                    int bottom = height[stack.pop()]; // 元素出栈，左边比自己大的是新的栈顶，右边比自己大的是当前元素
                    if (stack.size() == 0) {
                        break;
                    }
                    int leftIndex = stack.peek();
                    int left = height[leftIndex]; // 左侧的比自己大的值
                    sum += (i - leftIndex - 1) * (Math.min(left - bottom, right - bottom));
                }
                stack.push(i);
            }
        }

        return sum;

    }

    public static void main(String[] args) {
        System.out.println(new TrappingRainWater().trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }
}
