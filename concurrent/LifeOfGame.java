package com.project.jvm.concurrent;

import java.util.Random;

/**
 * 规则：
 * 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
 * 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
 * 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
 * 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；

 */
public class LifeOfGame {

    public int[][] bound = new int[10][10];
    public int rowMax = bound.length-1;
    public int lowMax = bound[0].length-1;
    public Random random = new Random(10);


    public static void main(String[] args) {
        LifeOfGame l = new LifeOfGame();
        l.init();
        l.print(l.bound);
        int[][] nextBound = l.game(l.bound);
        System.out.println();
        l.print(nextBound);
    }


    public void init() {
        for (int i = 0;i<bound.length;i++) {
            for (int j = 0;j<bound[0].length;j++) {
                if (random.nextInt()>5) {
                    bound[i][j]=1;
                }
            }
        }
    }

    public int[][] game(int[][] bound) {
        int[][] nextBound = new int[bound.length][bound[0].length];
        for (int i = 0;i<bound.length;i++) {
            for (int j = 0;j<bound[0].length;j++) {
                nextBound[i][j] = 0;
                int count = count(i, j);
                if (count==3) {
                    nextBound[i][j] = 1;
                }
                if (count==2) {
                    nextBound[i][j] = bound[i][j];
                }
            }
        }
        return nextBound;
    }

    public int count(int i,int j) {
        int count = 0;
        //右上角
        if (i != 0&&j!=lowMax){
            count+=bound[i-1][j+1];
        }
        //上
        if (i!=0) {
            count+=bound[i-1][j];
        }
        //左上
        if (i!=0&&j!=0) {
            count+=bound[i-1][j-1];
        }
        //左
        if (j!=0) {
            count+=bound[i][j-1];
        }
        //左下
        if (i!=rowMax&&j!=0) {
            count+=bound[i+1][j-1];
        }
        //下
        if (i!=rowMax) {
            count+=bound[i+1][j];
        }
        //右下
        if (i!=rowMax&&j!=lowMax) {
            count+=bound[i+1][j+1];
        }
        //右
        if (j!=lowMax) {
            count+=bound[i][j+1];
        }
        return count;
    }

    public void print(int[][] bound) {
        for (int i = 0;i<bound.length;i++) {
            for (int j =0;j<bound[0].length;j++) {
                System.out.print(bound[i][j]+" ");
            }
            System.out.println();
        }
    }
}
