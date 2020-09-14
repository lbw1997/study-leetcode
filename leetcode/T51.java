package com.project.jvm.leetcode;

import java.util.*;

/**
 * N皇后问题
 *
 */
public class T51 {

    private int[] rows;
    private int[] queues;
    private int[] master;
    private int[] slave;
    private List<List<String>> ret;

    public List<List<String>> solveNQueens(int n) {

        rows = new int[n];
        queues = new int[n];
        master = new int[2*n+1];
        slave = new int[2*n+1];
        ret = new ArrayList<>();

        backtrack(n,0);
        return ret;
    }

    private void backtrack(int n,int row) {

        if (row>=n) return;
        for (int col = 0;col < n;col ++) {

           if (isNotUnderAttack(n,row,col)) {

               queues[row] = col;
               rows[col] = 1;
               master[row-col+n-1] = 1;
               slave[row+col] = 1;

               if (row == n-1) addSolution(n);

               backtrack(n,row+1);

               queues[row] = 0;
               rows[col] = 0;
               master[row-col+n-1] = 0;
               slave[row+col] = 0;
           }
        }
    }

    private void addSolution(int n) {

        List<String> solution = new ArrayList<>();

        for (int i = 0;i < n;i ++) {

            StringBuilder sb = new StringBuilder();
            int col = queues[i];
            for (int j = 0;j<col;j++) {
                sb.append(".");
            }
            sb.append("Q");
            for (int j = 0;j < n - col - 1; j++) {
                sb.append(".");
            }
            solution.add(sb.toString());
        }
        ret.add(solution);
    }

    private boolean isNotUnderAttack(int n,int row, int col) {
        int ret = rows[col] + master[row-col+n-1] + slave[row+col];
        return ret == 0;
    }


    public static void main(String[] args) {
        T51 t = new T51();
        List<List<String>> ret = t.solveNQueens(8);
        System.out.println(ret);
    }
}
