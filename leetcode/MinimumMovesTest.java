package com.project.jvm.leetcode;

public class MinimumMovesTest {

    public static int minimumMoves(String S) {
        char[] arr = S.toCharArray();
        int aCount = 0;
        int bCount = 0;
        int num =0;
        for(int i=0;i<arr.length;i++) {
            if(i==arr.length-1) {
                if(arr[i] == 'a') {
                    aCount++;
                    num += aCount/3;
                }
                if(arr[i] == 'b') {
                    bCount++;
                    num += bCount/3;
                }
            }else{
                if(arr[i] == 'a') {
                    aCount ++;
                    if(arr[i+1] =='b') {
                        num += aCount/3;
                        aCount = 0;
                    }
                }
                if(arr[i] == 'b') {
                    bCount++;
                    if(arr[i+1] =='a') {
                        num += bCount/3;
                        bCount = 0;
                    }
                }
            }
        }
        return num;
    }
    public static void main(String[] args) {
        String s= "abaa";
        System.out.println(MinimumMovesTest.minimumMoves(s));
    }
}
