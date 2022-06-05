package Util;

import java.util.HashMap;
import java.util.List;

public class UnionFindSet {
    HashMap<Integer, Integer> fatherMap;
    HashMap<Integer, Integer> sizeMap;

    public UnionFindSet(List<Integer> list) {
        fatherMap = new HashMap<>();
        sizeMap = new HashMap<>();
        for (int e : list) {
            fatherMap.put(e, e);
            sizeMap.put(e, 1);
        }
    }

    public int findHead(int a) {
        int father = fatherMap.get(a);

        if (father != a) { // 找到根节点，根节点的头是自己
            father = findHead(father); // 递归调用，递归树中每个节点都进行了扁平化
        }
        fatherMap.put(a, father); // 扁平化操作
        return father;
    }

    public boolean isSameSet(int a, int b) {
        return findHead(a) == findHead(b);
    }

    public void union(int a, int b) {
        int aHead = findHead(a);
        int bHead = findHead(b);

        if (aHead != bHead) {
            int aSetSize = sizeMap.get(a);
            int bSetSize = sizeMap.get(b);

            if (aSetSize <= bSetSize) {
                fatherMap.put(aHead, bHead); // 将a的头链接到b的头上。小的放大的后面
                sizeMap.put(bHead, bSetSize + aSetSize); // 只需更改被插入集合的头的长度即可。其余不变，因为不会被访问到。
            } else {
                fatherMap.put(bHead, aHead);
                sizeMap.put(aHead, aSetSize + bSetSize);
            }
        }
    }
}
