package Stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class LargeRectangle {
    public int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        int maxArea = 0;

        // 对于每个元素，找左右比自己小的值
        // 单调栈，自底向上由小到大

        for (int i = 0; i < heights.length; i++) {
            if (stack.size() > 0 && heights[stack.peek()] <= heights[i]) { // 栈顶元素小于等于当前值
                stack.push(i);
            } else {
                int rightMin = i;
                int leftMin = 0;
                int area = 0;
                int cur = 0;
                while (stack.size() > 0 && heights[stack.peek()] > heights[i]) { // 栈顶元素大于当前值
                    cur = stack.pop();
                    if (stack.size() == 0) {
                        leftMin = -1;
                    } else {
                        leftMin = stack.peek();
                    }

                    if (leftMin == -1) { // 左侧没有比自己小的
                        area = heights[cur] * rightMin;
                    } else {
                        area = heights[cur] * (rightMin - leftMin - 1);
                    }
                    maxArea = Math.max(maxArea, area);
                }
                stack.push(i);
            }
        }

        while (stack.size() > 0) {
            int cur = stack.pop();
            int leftMin = 0;
            int area = 0;

            if (stack.size() == 0) {
                leftMin = -1;
            } else {
                leftMin = stack.peek();
            }

            if (leftMin == -1) { // 左侧没有比自己小的
                area = heights[cur] * heights.length; // 右侧也没有比自己小的
            } else {
                area = heights[cur] * (heights.length - leftMin - 1);
            }
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }

    public static void main(String[] args) {
        int[] arr = {2, 4};
        System.out.println(new LargeRectangle().largestRectangleArea(arr));
    }
}
