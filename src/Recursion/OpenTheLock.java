package Recursion;

import java.util.*;

class Solution6 {
    public int openLock(String[] deadends, String target) {
        if (target.equals("0000")) {
            return 0;
        }

        HashSet<String> dead = new HashSet<>();
        Collections.addAll(dead, deadends);

        if (dead.contains(target)) {
            return -1;
        }

        HashSet<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        HashMap<String, Integer> map = new HashMap<>(); // 记录步数

        queue.offer("0000");
        visited.add("0000");
        map.put("0000", 0);

        while (queue.size() > 0) {
            String cur = queue.poll();

            if (cur.equals(target)) {
                return map.get(cur);
            }

            for (String next : getNext(cur)) {
                if (!dead.contains(next) && !visited.contains(next)) {
                    visited.add(next);
                    queue.offer(next);
                    map.put(next, map.get(cur) + 1);
                }
            }
        }

        return -1;
    }

    public List<String> getNext(String cur) {
        char[] arr = cur.toCharArray();
        List<String> res = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            char temp = arr[i];
            arr[i] = nextNum(temp);
            res.add(new String(arr));
            arr[i] = preNum(temp);
            res.add(new String(arr));
            arr[i] = temp;
        }
        return res;
    }

    public char nextNum(char a) {
        if (a == '9') {
            return '0';
        } else {
            return (char) (a + 1);
        }
    }

    public char preNum(char a) {
        if (a == '0') {
            return '9';
        } else {
            return (char) (a - 1);
        }
    }
}

public class OpenTheLock {
    public static void main(String[] args) {
        String[] deadends = {"0201", "0101", "0102", "1212", "2002"};
        String target = "0202";
        System.out.println(new Solution6().openLock(deadends, target));
    }
}
