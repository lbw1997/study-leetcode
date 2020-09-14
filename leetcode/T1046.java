package com.project.jvm.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 有一堆石头，每块石头的重量都是正整数。
 *
 * 每一回合，从中选出两块 最重的 石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 *
 * 如果 x == y，那么两块石头都会被完全粉碎；
 * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * 最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。
 */
public class T1046 {

    public static int lastStoneWeight(int[] stones) {

//        int len = stones.length;
//        int time = stones.length - 1;
//
//        if (stones.length == 1) {
//            return stones[0];
//        }
//
//        while (time-->0) {
//            Arrays.sort(stones);
//            stones[len-1] = Math.abs(stones[len-1]-stones[len-2]);
//            stones[len-2] = 0;
//        }
//        return stones[len-1];

        //2、使用优先级队列
        PriorityQueue<Integer> queue = new PriorityQueue<>(((o1, o2) -> o2-o1));

        for (int i = 0;i<stones.length;i++) {
            queue.offer(stones[i]);
        }

        while (queue.size()>1) {
            int x = queue.poll();
            int y = queue.poll();
            int diff = Math.abs(x-y);
            if (diff!=0) {
                queue.offer(diff);
            }
        }
        if (queue.isEmpty()) return 0;
        return queue.peek();
    }

    public static void main(String[] args) {
        int[] stones = {7,6,7,6,9};
        System.out.println(lastStoneWeight(stones));
    }
}
