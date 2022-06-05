package Math;

public class SquareRoot {
    public int mySqrt(int x) {
        if (x == 1) {
            return 1;
        }

        int ans = 0;
        int l = 0, r = x;

        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if ((long) mid * mid <= x) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return ans;

    }

    public static void main(String[] args) {
        System.out.println(new SquareRoot().mySqrt(2147395599));
    }
}
