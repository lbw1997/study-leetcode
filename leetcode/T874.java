package com.project.jvm.leetcode;

import java.util.HashSet;

/**
 * 机器人在一个无限大小的网格上行走，从点 (0, 0) 处开始出发，面向北方。该机器人可以接收以下三种类型的命令：
 *
 * -2：向左转 90 度
 * -1：向右转 90 度
 * 1 <= x <= 9：向前移动 x 个单位长度
 * 在网格上有一些格子被视为障碍物。
 *
 * 第 i 个障碍物位于网格点  (obstacles[i][0], obstacles[i][1])
 *
 * 机器人无法走到障碍物上，它将会停留在障碍物的前一个网格方块上，但仍然可以继续该路线的其余部分。
 *
 * 返回从原点到机器人所有经过的路径点（坐标为整数）的最大欧式距离的平方.
 *
 * 示例 1：
 *
 * 输入: commands = [4,-1,3], obstacles = []
 * 输出: 25
 * 解释: 机器人将会到达 (3, 4)
 */
public class T874 {

    public static int robotSim(int[] commands, int[][] obstacles) {

        int x = 0, y = 0, ans = 0, direction = 0;

        int[][] Direction = {{0,1},{1,0},{0,-1},{-1,0}};
        HashSet<String> hashSet = new HashSet<>();
        for (int[] arr:obstacles) {
            hashSet.add(arr[0]+","+arr[1]);
        }

        for (int comm:commands) {
            int next_x = 0;
            int next_y = 0;

            if (comm>=0) {

                for (int i = 0;i<comm;i++) {
                    next_x = x + Direction[direction][0];
                    next_y = y + Direction[direction][1];

                    if (hashSet.contains(next_x+","+next_y)) break;

                    x = next_x;
                    y = next_y;

                    ans = Math.max(ans,x*x+y*y);
                }
            } else {
                direction = comm == -1?(direction+1)%4 :(direction+3)%4;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] commands = {4,-1,3};
        int[][] obstacles = {};
        System.out.println(robotSim(commands, obstacles));
    }
}
