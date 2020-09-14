package com.project.jvm.practice;

/**
 * 从二维数组中找到某个数，左到右，上到下都是按大小排序
 */
public class FindNumber {

    public static boolean find(int[][] arr, int num) {
        int rows = arr.length-1;
        int cols = arr[0].length-1;

        int row = 0;
        int col = cols;

        while(row<rows || col>0) {
            if (arr[row][col] == num) {
                return true;
            }else if (arr[row][col] >num) {
                col--;
            }else {
                row++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] arr = {{1,2,8,9},{2,4,9,12},{4,7,10,13},{6,8,11,15}};
        System.out.println(find(arr, 5));
    }
}
