package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CourseScheduleII {
    static class Node {
        int value;
        int inDegree;
        ArrayList<Node> nexts;

        public Node(int value) {
            this.value = value;
            this.inDegree = 0;
            this.nexts = new ArrayList<>();
        }
    }


    public int[] findOrder(int numCourses, int[][] prerequisites) {
        HashMap<Integer, Node> map = new HashMap<>(); // 使用哈希表表示图
        List<Integer> res = new ArrayList<>();

        for (int[] entry : prerequisites) {
            int a = entry[0];
            int b = entry[1];

            if (!map.containsKey(a)) {
                Node nodeA = new Node(a);
                map.put(a, nodeA);
            }
            map.get(a).inDegree++; // 入度+1


            if (!map.containsKey(b)) {
                Node nodeB = new Node(b);
                map.put(b, nodeB);
            }
            map.get(b).nexts.add(map.get(a)); // b->a
        }

        List<Integer> nodeInMap = new ArrayList<>(map.keySet()); // 记录位于依赖图中的节点

        int round = numCourses;

        while (round > 0) {
            for (int k : map.keySet()) {
                Node cur = map.get(k);
                if (cur.inDegree == 0) { // 存在节点入度为0
                    for (Node next : cur.nexts) { // 邻居节点入度减一
                        next.inDegree--;
                    }
                    res.add(map.remove(k).value); // 将该节点删除，append到结果集
                    break; // 必须break，重新找入度为0节点
                }
            }
            round--;
        }

        if (res.size() == nodeInMap.size()) { // 依赖图中节点可被拓扑排序
            while (numCourses > 0) { // 将依赖图之外的节点append到结果集
                if (!nodeInMap.contains(numCourses - 1)) {
                    res.add(numCourses - 1);
                }
                numCourses--;
            }
        } else { // 依赖图中节点不能被拓扑排序
            res.clear(); // 课程顺序为空数组
        }

        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        Arrays.stream(new CourseScheduleII().findOrder(3, new int[][]{{1, 0}, {1, 2}, {0, 1}})).forEach(System.out::print);
    }
}
