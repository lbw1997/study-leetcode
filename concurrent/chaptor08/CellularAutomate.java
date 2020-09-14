package com.project.jvm.concurrent.chaptor08;

import java.util.Random;

/**
 * * 1. 如果一个细胞只有0或1个邻居，它将因为孤独而死；
 * * 2. 如果一个细胞有4到8个邻居，它将因为拥挤而死；
 * * 3. 如果一个细胞恰有2或者3个邻居，它将继续生存下去；
 * * 4. 如果一个空格子恰有3个邻居，将“生”出一个新细胞；
 * * 5. 其他的空格子继续维持原状。
 *
 * 思路：类似扫雷游戏，记录每个细胞周围细胞数，每次分裂发生算作一代
 */
public class CellularAutomate {

    public int[][] group = new int[100][100];
    public Random random = new Random(10);
    public void init() {
        for (int i = 0;i<group.length;i++) {
            for (int j =0;j<group[0].length;j++) {
                if (random.nextInt()>5) {
                    group[i][j] = 1;
                }
            }
        }
    }

    public void rule(int cycle) {
        while(cycle-->0) {
            for (int i = 0;i<group.length;i++) {
                for (int j =0;j<group[0].length;j++) {
                    //if ()
                }
            }
        }
    }

    public int aroundCount(int i, int j) {
        int count = 0;
        for (i = 0;i<group.length;i++) {
            for (j =0;j<group[0].length;j++) {
                //if ()
            }
        }
        return 0;
    }

    public void print() {
        for (int i = 0;i<group.length;i++) {
            for (int j =0;j<group[0].length;j++) {
                System.out.print(group[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        CellularAutomate auto = new CellularAutomate();
        auto.init();
        auto.print();
    }
}
