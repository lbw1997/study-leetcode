package com.project.jvm.leetcode;

import java.util.*;

/**
 * 在一个二维的花园中，有一些用 (x, y) 坐标表示的树。由于安装费用十分昂贵，你的任务是先用最短的绳子围起所有的树。
 * 只有当所有的树都被绳子包围时，花园才能围好栅栏。你需要找到正好位于栅栏边界上的树的坐标。
 *
 */
public class T587 {

    public int[][] outerTrees(int[][] points) {

        int n = points.length;

        if (n<4) {
            return points;
        }

        HashSet<int[]> ret = new HashSet<>();

        int left_most = 0;
        for (int i = 1;i<n;i++) {

            if (points[i][0] < points[left_most][0]) {
                left_most = i;
            }
        }

        int p = left_most;
        do {
            int q = (p+1)%n;
            for (int i = 0;i<n;i++) {
                if (orientation(points[i],points[q],points[p])<0) {
                    q = i;
                }
            }
            for (int i = 0;i<n;i++) {
                if (i != q && i != p && orientation(points[i],points[p],points[q]) == 0
                        &&isBetween(points[i],points[p],points[q])) {
                    ret.add(points[i]);
                }
            }
            ret.add(points[p]);
            p = q;

        }while (p!=left_most);

        int[][] arr = new int[ret.size()][2];
        return ret.toArray(arr);
    }

    public int orientation(int[] i, int[] q, int[] p) {

        return (q[0] - p[0]) * (i[1]-p[1]) - (i[0] - p[0]) * (q[1]-p[1]);
    }

    public boolean isBetween(int[] i, int[] q, int[] p) {

        boolean a = (i[0] >= p[0] && i[0] <= q[0] || i[0] <= p[0] && i[0] >= q[0]);
        boolean b = (i[1] >= p[1] && i[1] <= q[1] || i[1] <= p[1] && i[1] >= q[1]);
        return a&&b;
    }

    public static void main(String[] args) {
        int[][] points = new int[6][2];
        points[0] = new int[]{1,2};
        points[1] = new int[]{2,2};
        points[2] = new int[]{4,2};
        points[3] = new int[]{5,2};
        points[4] = new int[]{6,2};
        points[5] = new int[]{7,2};

        T587 t = new T587();
        int[][] ints = t.outerTrees(points);

        for (int[] arr : ints) {
            for (int a :arr) {
                System.out.print(a);
            }
            System.out.println();
        }
    }
}
