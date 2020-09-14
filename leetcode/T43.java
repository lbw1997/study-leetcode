package com.project.jvm.leetcode;

public class T43 {

    public String multiply(String num1, String num2) {

        if (num1.equals("0")||num2.equals("0")) return "0";

        String ret = "0";
        for (int i = num1.length()-1;i>=0;i--) {
            int c = 0;
            StringBuilder temp = new StringBuilder();

            for (int j = 0;j<num1.length()-1-i;j++) {
                temp.append(0);
            }
            int n1 = num1.charAt(i)-'0';

            for (int j = num2.length()-1;j>=0||c!=0;j--) {
                int n2 = j<0?0:num2.charAt(j)-'0';
                int mu = (n1*n2+c)%10;
                temp.append(mu);
                c = (n1*n2+c)/10;
            }
            ret = addStrings(ret,temp.reverse().toString());
        }
        return ret;
    }

    private String addStrings(String s1,String s2) {

        int c = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = s1.length()-1,j = s2.length()-1;i>=0||j>=0||c!=0;i--,j--) {
            int n1 = i<0?0:s1.charAt(i)-'0';
            int n2 = j<0?0:s2.charAt(j)-'0';
            int mu = (n1+n2+c)%10;
            sb.append(mu);
            c = (n1+n2+c)/10;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        T43 t = new T43();
        System.out.println(t.multiply("123", "456"));
    }
}
