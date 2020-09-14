package com.project.jvm.practice;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class FindK {

    public static int find(Integer[] nums,int k) {

        //1、对数组前K个数进行从大到小排序
        Arrays.sort(nums,0,k, Collections.reverseOrder());

        //2、对之后元素使用插入排序
        int j;
        for (int i = k;i<nums.length;i++) {

            int temp = nums[i];

            for (j = i;j>0&&temp>nums[j-1];j--) {
                nums[j] = nums[j-1];
            }
            nums[j] = temp;
        }

        for (int n : nums) {
            System.out.println(n);
        }

        return nums[0];
    }



    public static void main(String[] args) {

        Integer[] nums = {34,8,50,51,32,64};
        int i = find(nums,3);
        System.out.println(i);
    }
}
