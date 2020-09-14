package com.project.jvm.leetcode;

public class T48 {

    public void rotate(int[][] matrix) {
        int n = matrix.length;

        for (int i = 0;i<n/2+n%2;i++) {
            for (int j = 0;j<n/2;j++) {
                int[] temp = new int[4];
                int row = i;
                int col = j;
                for (int k = 0;k<4;k++) {
                    temp[k] = matrix[row][col];
                    int x = row;

                    row = col;
                    col = n-1-x;
                }
                for (int k = 0;k<4;k++) {
                    matrix[row][col] = temp[(k+3)%4];
                    int x = row;
                    row = col;
                    col = n-1-x;
                }
            }
        }
    }
}
