package Stack;

class DecodeWays {
    public static int numDecodings(String s) {
        char[] tmp = s.toCharArray();
        int[] arr = new int[tmp.length];
        for (int i = 0; i < tmp.length; i++) {
            arr[i] = Integer.parseInt(String.valueOf(tmp[i]));
        }

        return process(arr, 0);

    }

    public static int process(int[] arr, int i) {
        int len = arr.length;

        if (i == len - 1) {
            if (arr[i] == 0) {
                return 0;
            } else if (arr[i] != 0) {
                return 1;
            }
        }

        if (i == len - 2) {
            if (arr[i] == 0) {
                return 0;
            } else if (arr[i] * 10 + arr[i + 1] >= 27 || arr[i] * 10 + arr[i + 1] == 10 || arr[i] * 10 + arr[i + 1] == 20) {
                return 1;
            } else {
                return 2;
            }
        }

        if (arr[i] == 0) {
            return 0;
        } else if (arr[i] * 10 + arr[i + 1] >= 27 || arr[i] * 10 + arr[i + 1] == 10 || arr[i] * 10 + arr[i + 1] == 20) {
            return process(arr, i + 2);
        } else {
            return process(arr, i + 1) + process(arr, i + 2);
        }
    }

    public static void main(String[] args) {
        String s = "226";
        System.out.println(numDecodings(s));
    }
}