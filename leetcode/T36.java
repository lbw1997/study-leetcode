package com.project.jvm.leetcode;

/**
 * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
 *
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 *
 */
public class T36 {

    private int L = 9;

    public boolean isValidSudoku(char[][] board) {

        boolean[][] row = new boolean[L][L];
        boolean[][] col = new boolean[L][L];
        boolean[][] boxes = new boolean[L][L];

        for (int r = 0;r<L;r++) {

            for (int c = 0;c<L;c++) {

                if (board[r][c] != '.') {
                    int num = board[r][c] - '1';
                    int box_index = r/3*3 + c/3;

                    if (row[r][num] || col[c][num] || boxes[box_index][num]) {
                        return false;
                    }
                    row[r][num] = true;
                    col[c][num] = true;
                    boxes[box_index][num] = true;
                }
            }
        }
        return true;
    }
}
