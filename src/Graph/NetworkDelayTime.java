package Graph;

import java.util.*;

public class NetworkDelayTime {
    final int INF = Integer.MAX_VALUE / 2;

    public int disBetween(int[][] times, int a, int b) {
        for (int[] time : times) {
            if (time[0] == a && time[1] == b) {
                return time[2];
            }
        }
        return INF;
    }

    public int networkDelayTime(int[][] times, int n, int k) {
        HashMap<Integer, Integer> distance = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            distance.put(i, INF); // INF表示不可达
        }
        distance.put(k, 0); // 初始时，仅起点位置可达

        HashSet<Integer> lockedSet = new HashSet<>();

        while (n > 0) {
            int currentLock = 0; // 当前锁定的节点
            int minDistance = Integer.MAX_VALUE;

            for (int key : distance.keySet()) {
                int value = distance.get(key);
                if (value < minDistance && !lockedSet.contains(key)) {
                    minDistance = value;
                    currentLock = key;
                }
            }
            if (minDistance == INF) { // 无可达节点
                break;
            }
            lockedSet.add(currentLock); // 锁定

            for (int key : distance.keySet()) {
                int disBetweenCK = disBetween(times, currentLock, key);
                int dis = distance.get(key);
                distance.put(key, Math.min(dis, minDistance + disBetweenCK));
            }

            n--;
        }

        // 返回最大的传输时间 value
        List<Integer> distanceList = new ArrayList<>(distance.values());
        distanceList.sort((o1, o2) -> o2 - o1);
        int res = distanceList.get(0);

        return res == INF ? -1 : res;
    }


    public static void main(String[] args) {
        int[][] times = {{3, 5, 78}
                , {2, 1, 1}
                , {1, 3, 0}
                , {4, 3, 59}
                , {5, 3, 85}
                , {5, 2, 22}
                , {2, 4, 23}
                , {1, 4, 43}
                , {4, 5, 75}
                , {5, 1, 15}
                , {1, 5, 91}
                , {4, 1, 16}
                , {3, 2, 98}
                , {3, 4, 22}
                , {5, 4, 31}
                , {1, 2, 0}
                , {2, 5, 4}
                , {4, 2, 51}
                , {3, 1, 36}
                , {2, 3, 59}};
        int n = 5;
        int k = 5;
        System.out.println(new NetworkDelayTime().networkDelayTime(times, n, k));
    }
}
