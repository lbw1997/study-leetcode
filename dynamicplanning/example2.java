package com.project.jvm.dynamicplanning;


import java.util.Arrays;

/**
 * 比较两个字符串需要多少次操作后才能相同
 * 如word1 -> exword2   输出3
 */
public class example2 {

    public static int compare(int[] prices) {

        int max = 0;
        int min = Integer.MAX_VALUE;
        for (int price:prices) {
            if (price<min) {
                min = price;
            }else if (price-min>max) {
                max = price-min;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {7,1,5,3,6,4};
        System.out.println(compare(arr));
    }
}
