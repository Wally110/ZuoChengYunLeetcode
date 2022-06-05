package BiSearch;

interface MountainArray {
    int get(int index);

    int length();
}

public class FindinMountainArray {

    public int findInMountainArray(int target, MountainArray mountainArr) {
        int l = 0, r = mountainArr.length() - 1;
        int peekIndex = findPeek(mountainArr);

        int leftTargetIndex = biSearch(mountainArr, target, l, peekIndex, 0);
        if (leftTargetIndex != -1) {
            return leftTargetIndex;
        } else {
            return biSearch(mountainArr, target, peekIndex + 1, r, 1);
        }

    }

    public int biSearch(MountainArray mountainArr, int target, int l, int r, int type) {
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            int midVal = mountainArr.get(mid);

            if (midVal == target) {
                return mid;
            } else if (type == 0 ? midVal > target : midVal < target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }

    public int findPeek(MountainArray mountainArr) {
        int l = 0, r = mountainArr.length() - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            int midVal = mountainArr.get(mid);

            int leftVal = -1;
            if (mid - 1 >= l) {
                leftVal = mountainArr.get(mid - 1);
            }
            int rightVal = -1;
            if (mid + 1 <= r) {
                rightVal = mountainArr.get(mid + 1);
            }

            if ((leftVal == -1 || midVal > leftVal) && (rightVal == -1 || midVal > rightVal)) {
                return mid;
            } else if (midVal > leftVal && (rightVal == -1 || midVal < rightVal)) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }

}
