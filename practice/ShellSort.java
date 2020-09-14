package com.project.jvm.practice;

public class ShellSort {

    public static void sort(int[] nums) {

        int j ;
        for (int gap = nums.length/2;gap>0;gap /= 2) {

            for (int i = gap;i<nums.length;i++) {
                int temp = nums[i];

                for (j = i;j>=gap&&temp<nums[j-gap];j-=gap) {
                    nums[j] = nums[j-gap];
                }
                nums[j] = temp;
            }
            printArray(nums);
        }
    }

    public static void printArray(int[] nums) {

        for (int n:nums) {
            System.out.print(n+" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] nums = {1,9,2,10,3,11,4,12,5,13,6,14,7,15,8,16};

        sort(nums);
    }
}