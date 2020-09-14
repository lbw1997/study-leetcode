package com.project.jvm.leetcode;

public class T37 {

    private int L = 9;
    boolean[][] row = new boolean[L][L];
    boolean[][] col = new boolean[L][L];
    boolean[][] boxes = new boolean[L][L];

    public void solveSudoku(char[][] board) {


        for (int r = 0;r<L;r++) {

            for (int c = 0; c < L; c++) {

                if (board[r][c] != '.') {
                    int num = board[r][c] - '1';
                    int box_index = r / 3 * 3 + c / 3;
                    row[r][num] = true;
                    col[c][num] = true;
                    boxes[box_index][num] = true;
                }
            }
        }
        backtrack(0,0,board);
    }

    public boolean backtrack(int r,int c, char[][] board) {

        if (c == L) {
            c = 0;
            r ++;
            if (r == L) {
                return true;
            }
        }

        if (board[r][c] == '.') {

            int index = r/3*3 +c/3;
            for (int i = 0;i<9;i++) {
                boolean canUsed = !(row[r][i]||col[c][i] ||boxes[index][i]);
                if (canUsed) {
                    row[r][i] = true;
                    col[c][i] = true;
                    boxes[index][i] = true;

                    board[r][c] = (char)('1'+i);
                    if (backtrack(r,c+1,board)) {
                        return true;
                    }
                    board[r][c] = '.';
                    boxes[index][i] = false;
                    col[c][i] = false;
                    row[r][i] = false;
                }
            }
        }else {
            return backtrack(r,c+1,board);
        }
        return false;
    }
}
