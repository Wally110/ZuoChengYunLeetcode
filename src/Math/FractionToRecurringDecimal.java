package Math;

import java.util.ArrayList;
import java.util.List;

public class FractionToRecurringDecimal {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }

        String quotientStr = ""; // 各位的商
        List<Long> numeratorlist = new ArrayList<>(); // 各位的余数list
        StringBuilder res = new StringBuilder();
        int start = -1, end = -1;

        if (numerator > 0 && denominator < 0 || numerator < 0 && denominator > 0) {
            res.append("-");
        }
        long numeratorL = Math.abs((long) numerator); // 有符号int的最小数转为整数，需要先转为long
        long denominatorL = Math.abs((long) denominator);


        res.append(numeratorL / denominatorL); // 处理整数部分
        numeratorL %= denominatorL;


        if (numeratorL == 0) {
            return res.toString();
        }

        numeratorL *= 10; // 余数乘以10，开始计算十分位
        while (numeratorL > 0) { // 小数部分
            if (numeratorlist.contains(numeratorL)) { // 余数曾经出现过，说明循环了
                start = numeratorlist.indexOf(numeratorL);
                end = numeratorlist.size();
                break;
            } else {
                numeratorlist.add(numeratorL);
            }

            quotientStr += (numeratorL / denominatorL);
            numeratorL %= denominatorL;
            numeratorL *= 10; // 余数乘以10，开始计算下一分位
        }

        if (numeratorL == 0) { // 无循环
            return res.append(".").append(quotientStr).toString();
        } else { // 有循环
            String ans1 = quotientStr.substring(0, start); // 循环外
            String ans2 = quotientStr.substring(start, end); // 循环体
            return res.append(".").append(ans1).append("(").append(ans2).append(")").toString();
        }

    }

    public static void main(String[] args) {
        System.out.println(new FractionToRecurringDecimal().fractionToDecimal(1, 7));
    }
}
