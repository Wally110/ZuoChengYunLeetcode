package String;

import java.util.Arrays;

class Solution2 {
    public String reverseWords(String s) {
        char[] arr = s.trim().toCharArray();
        int len = arr.length;

        int cnt = 0;
        for (int i = 1; i < len; i++) {
            if (arr[i - 1] == ' ' && arr[i] == ' ') {
                cnt++;
            }
            arr[i - cnt] = arr[i];
        }

        for (int i = 0; i < len; i++) {
            if (i > len - 1 - cnt) {
                arr[i] = ' ';
            }
        }

        char[] arr2 = Arrays.copyOfRange(arr, 0, len - cnt);

        for (int i = 0; i < len - cnt; ) {
            if (i == len - cnt - 1) {
                break;
            }
            for (int j = i + 1; j < len - cnt; j++) {
                if (arr2[j] == ' ') {
                    swap(arr2, i, j - 1);
                    i = j + 1;
                    break;
                } else if (j == len - cnt - 1) {
                    swap(arr2, i, j);
                    i = j + 1;
                }
            }
        }

        swap(arr2, 0, len - cnt - 1);

        return String.valueOf(arr2).trim();

    }

    public void swap(char[] arr, int a, int b) {
        while (a <= b) {
            char tmp = arr[a];
            arr[a] = arr[b];
            arr[b] = tmp;
            a++;
            b--;
        }
    }
}

class Solution {
    public String reverseWords(String s) {
        char[] arr = s.trim().toCharArray();
        int len = arr.length;

        for (int i = 0; i < len; ) {
            for (int j = i + 1; j < len; j++) {
                if (arr[j] == ' ') {
                    swap(arr, i, j - 1);
                    i = nextI(arr, j);
                    break;
                } else if (j == len - 1) {
                    swap(arr, i, j);
                    i = len;
                    break;
                }
            }
            i++;
        }

        int cnt = 0;
        for (int i = 1; i < len; i++) {
            if (arr[i - 1] == ' ' && arr[i] == ' ') {
                cnt++;
            }
            arr[i - cnt] = arr[i];
        }

        for (int i = 0; i < len; i++) {
            if (i > len - 1 - cnt) {
                arr[i] = ' ';
            }
        }

        swap(arr, 0, len - 1);

        return String.valueOf(arr).trim();

    }

    public void swap(char[] arr, int a, int b) {
        while (a <= b) {
            char tmp = arr[a];
            arr[a] = arr[b];
            arr[b] = tmp;
            a++;
            b--;
        }
    }

    public int nextI(char[] arr, int j) {
        while (j < arr.length && arr[j] == ' ') {
            j++;
        }
        return j;
    }
}

public class ReverseWordsInStr {
    public static void main(String[] args) {
        String s = "F R  I   E    N     D      S      ";
        System.out.println(new Solution2().reverseWords(s));
    }
}
