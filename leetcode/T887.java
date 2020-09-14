package com.project.jvm.leetcode;

import java.util.HashMap;

/**
 * 你将获得 K 个鸡蛋，并可以使用一栋从 1 到 N  共有 N 层楼的建筑。
 *
 * 每个蛋的功能都是一样的，如果一个蛋碎了，你就不能再把它掉下去。
 *
 * 你知道存在楼层 F ，满足 0 <= F <= N 任何从高于 F 的楼层落下的鸡蛋都会碎，从 F 楼层或比它低的楼层落下的鸡蛋都不会破。
 *
 * 每次移动，你可以取一个鸡蛋（如果你有完整的鸡蛋）并把它从任一楼层 X 扔下（满足 1 <= X <= N）。
 *
 * 你的目标是确切地知道 F 的值是多少。
 *
 * 无论 F 的初始值如何，你确定 F 的值的最小移动次数是多少？
 */
public class T887 {

    HashMap<Integer,Integer> memo = new HashMap<>();
    public int superEggDrop(int K, int N) {
        if (N == 0) return 0;
        if (K == 1) return N;

        Integer key = K + N*1000;
        if (memo.containsKey(key)) return memo.get(key);

        int left = 1, right = N;
        while (left+1<right) {
            int mid = (left+right)/2;
            int broken = superEggDrop(K-1,mid-1);
            int not_broken = superEggDrop(K,N-mid);
            if (broken>not_broken) {
                right = mid;
            }else {
                left = mid;
            }
        }
        int min = 1+Math.min(Math.max(superEggDrop(K-1,left-1),superEggDrop(K,N-left)),
                Math.max(superEggDrop(K-1,right-1),superEggDrop(K,N-right)));
        memo.put(key,min);
        return min;
    }


    public static void main(String[] args) {
        T887 t887 = new T887();
        System.out.println(t887.superEggDrop(2,7));
    }
}
