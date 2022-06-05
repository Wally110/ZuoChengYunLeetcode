package Util;

import java.util.*;

public class NumbersOfProvince {
    public int findCircleNum(int[][] isConnected) {
        int len = isConnected.length;

        List<Integer> nodes = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            nodes.add(i);
        }

        UnionFindSet ufs = new UnionFindSet(nodes);
        for (int i = 0; i < len; i++) {
            for (int j = i; j < isConnected[i].length; j++) {
                if (isConnected[i][j] == 1) {
                    ufs.union(i, j);
                }
            }
        }

        Set<Integer> headSets = new HashSet<>();
        for (int i = 0; i < len; i++) {
            headSets.add(ufs.findHead(i));
        }

        System.out.println(Arrays.toString(headSets.stream().mapToInt(Integer::intValue).toArray()));

        return headSets.size();
    }

    public static void main(String[] args) {
        int[][] arr = {{1, 1, 1, 0, 1, 1, 1, 0, 0, 0}
                , {1, 1, 0, 0, 0, 0, 0, 1, 0, 0}
                , {1, 0, 1, 0, 0, 0, 0, 0, 0, 0}
                , {0, 0, 0, 1, 1, 0, 0, 0, 1, 0}
                , {1, 0, 0, 1, 1, 0, 0, 0, 0, 0}
                , {1, 0, 0, 0, 0, 1, 0, 0, 0, 0}
                , {1, 0, 0, 0, 0, 0, 1, 0, 1, 0}
                , {0, 1, 0, 0, 0, 0, 0, 1, 0, 1}
                , {0, 0, 0, 1, 0, 0, 1, 0, 1, 1}
                , {0, 0, 0, 0, 0, 0, 0, 1, 1, 1}};
        System.out.println(new NumbersOfProvince().findCircleNum(arr));
    }
}
