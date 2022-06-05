package String;

import java.util.ArrayList;
import java.util.List;

public class Integer2EnglishWords {
    String[] singles = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"}; // 1~9
    String[] teens = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen"
            , "Eighteen", "Nineteen"}; // 10~19
    String[] tens = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"}; // 20~90
    String[] thousands = {"", "Thousand", "Million", "Billion"};

    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }

        int a = num;
        List<Integer> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        while (a > 0) {
            list.add(a % 10); // 取每一位
            a = a / 10;
        }
        int len = list.size();

        int cnt = (int) Math.ceil(list.size() / 3.0 - 1); // 有几个三位，从0开始。 向上取整
        while (cnt >= 0) { // 从高位开始
            int first = -1, second = -1, third = -1;
            if (3 * cnt + 2 < len) {
                third = list.get(3 * cnt + 2);
            }
            if (3 * cnt + 1 < len) {
                second = list.get(3 * cnt + 1);
            }
            first = list.get(3 * cnt);

            process(cnt, first, second, third, sb);
            cnt--;
        }

        return sb.toString().trim();
    }

    public void process(int cnt, int first, int second, int third, StringBuilder sb) {

        if (third != -1 && third != 0) { // 如果第三位为0或不存在，则不加 Hundred
            sb.append(singles[third]).append(" Hundred ");
        }

        if (second != -1) { // 第二位存在
            if (second == 1) { // 第二位是1
                sb.append(teens[first]).append(" "); // 根据第一位看第二位
            } else if (second >= 2) {
                sb.append(tens[second]).append(" ");
            }
        }

        if (first != -1 && first != 0 && second != 1) { // 如果第二位是1，则不看第一位
            sb.append(singles[first]).append(" ");
        }

        if (third != 0 || second != 0 || first != 0) {
            sb.append(thousands[cnt]).append(" ");
        }

    }

    public static void main(String[] args) {
        String res = new Integer2EnglishWords().numberToWords(50868);
        System.out.println(res);
    }
}
