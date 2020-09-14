package com.project.jvm.sort;

/**
 * 快速排序，占坑法，先找个基准元素，从数组左边先开始执行，若有比该元素大的则j--，有小的就把该元素放到右边。
 * 之后从左边开始执行，若有比基准元素小的则i++，有大的就放到右边
 *  当i和j重合时，就把基准元素赋值给数组对应的元素，即此时基准值左边比自己小，右边都比自己大
 *  之后以这把数组分为两块，分别进行迭代运算。
 */
public class FastSort {

    public static void main(String[] args) {
        int arr[] = {6,7,8,9,4,1,2,5,7,3,5,10};
        int start = 0;
        int end = arr.length-1;
        FastSort fastSort = new FastSort();
        fastSort.sort(arr, start, end);
        for (int a:arr) {
            System.out.print(a);
        }
    }

    public void sort(int[] arr,int start,int end) {
        int i = start;
        int j = end;
        int temp = arr[start];
        if (i<j) {
            while(i<j) {
                while(j>i && arr[j]>=temp) {
                    j--;
                }
                arr[i] = arr[j];
                while(i<j && arr[i]<=temp) {
                    i++;
                }
                arr[j] = arr[i];
            }
            arr[i] = temp;
            sort(arr,start,i-1);
            sort(arr,i+1,end);
        }
    }
}
