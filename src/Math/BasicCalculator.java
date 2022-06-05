package Math;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class Solution4 {
    public boolean isSame(char a, char b) {
        return (a >= '0' && a <= '9') && (b >= '0' && b <= '9');
    }

    public List<String> preProcess(String s) {
        s = s.trim();
        List<String> res = new ArrayList<>();

        for (int i = 0; i < s.length(); ) {
            for (int j = i; j <= s.length(); j++) {
                char start = s.charAt(i);
                if (start == ' ') {
                    i++;
                    continue;
                }
                if (j == s.length()) {
                    res.add(s.substring(i, j).trim());
                    i = s.length();
                    break;
                }

                char cur = s.charAt(j);
                if (!isSame(start, cur)) {
                    String tmp2 = s.substring(i, j).trim();
                    if (!tmp2.equals("")) {
                        res.add(tmp2);
                    }
                    String tmp = String.valueOf(cur);
                    if (!tmp.equals(" ")) {
                        res.add(tmp);
                    }
                    i = j + 1;
                    break;
                }
            }
        }
        return res;
    }

    public int calculate(String s) {
        List<String> arr = preProcess(s);
        Deque<String> stack = new ArrayDeque<>();

        for (String e : arr) {
            if (e.equals(" ")) {
                continue;
            } else {
                if (e.equals(")")) {
                    while (!stack.peek().equals("(")) { // 还没出现左括号
                        int b = Integer.parseInt(stack.pop());
                        int res = 0;
                        if (stack.peek().equals("+") || stack.peek().equals("-")) {
                            String op = stack.pop();
                            int a;
                            if (stack.peek().equals("(")) { // 没有a
                                a = 0;
                            } else {
                                a = Integer.parseInt(stack.pop());
                            }
                            if (stack.peek().equals("-")) {
                                op = (op.equals("+") ? "-" : "+");
                            }
                            switch (op) {
                                case "+":
                                    res = a + b;
                                    break;
                                case "-":
                                    res = a - b;
                                    break;
                            }
                        } else {
                            res = b;
                        }
                        if (stack.peek().equals("(")) {
                            stack.pop();
                            stack.push(String.valueOf(res));
                            break;
                        }
                        stack.push(String.valueOf(res));
                    }
                } else {
                    stack.push(e);
                }
            }
        }
        while (stack.size() > 1) { // 继续计算,里面已经没有括号了
            int b = Integer.parseInt(stack.pop());
            String op = stack.pop();
            int a;
            if (stack.size() == 0) { // 没有a
                a = 0;
            } else {
                a = Integer.parseInt(stack.pop());
            }
            if (stack.size() > 0 && stack.peek().equals("-")) {
                op = (op.equals("+") ? "-" : "+");
            }
            int res = 0;
            switch (op) {
                case "+":
                    res = a + b;
                    break;
                case "-":
                    res = a - b;
                    break;
            }
            stack.push(String.valueOf(res));
        }
        return Integer.parseInt(stack.peek());
    }
}

public class BasicCalculator {
    public static void main(String[] args) {
        String str = "(5-(1+(5)))";
        System.out.println(new Solution4().calculate(str));
    }
}
