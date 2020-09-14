package com.project.jvm.leetcode;

public class MinDeletionsToObtainStringInRightFormat {
    public int minDeletionsToObtainStringInRightFormat(String S) {
        int ACount = 0;
        int BCount = 0;
        int removeNum = 0;
        for (int i=0;i<S.length();i++) {
            char c = S.charAt(i);
            if(c == 'A') {
                ACount++;
                if(BCount>removeNum) {
                    removeNum++;
                }
            }else {
                BCount++;
                if (ACount==0) {
                    removeNum++;
                }
            }
        }
        return Math.min(ACount, Math.min(BCount, removeNum));
    }
}
