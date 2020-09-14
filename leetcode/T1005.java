package com.project.jvm.leetcode;

import java.util.Arrays;

/**
 * 给定一个整数数组 A，我们只能用以下方法修改该数组：我们选择某个索引 i 并将 A[i] 替换为 -A[i]，然后总共重复这个过程 K 次。（我们可以多次选择同一个索引 i。）
 *
 * 以这种方式修改数组后，返回数组可能的最大和。

 */
public class T1005 {

    public static int largestSumAfterKNegations(int[] A, int K) {

//        1、常规解法
//       int ans = 0;
//       Arrays.sort(A);
//       for (int i = 0;i<A.length;i++) {
//           if (K>0&&A[i]<0) {
//               K--;
//               A[i] = -A[i];
//           }
//       }
//        if (K%2 == 1) {
//           Arrays.sort(A);
//           A[0] = -A[0];
//       }
//
//       for (int a: A) {
//           ans += a;
//       }
//       return ans;

//        2、用number数组
        int[] number = new int[201];
        for (int a : A) {
            number[a+100] ++;
        }

        int i = 0;
        while (K>0) {
            while (number[i]==0) {
                i++;
            }
            number[i]--;
            number[200-i]++;
            if (i>100) {
                i = 200-i;
            }
            K--;
        }

        int ans = 0;
        for (int a = 0;a<number.length;a++) {
            if (a>0) {
                ans += (a-100)*number[a];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] A = {-8,3,-5,-3,-5,-2};
        int K = 6;
        System.out.println(largestSumAfterKNegations(A, K));
    }
}
