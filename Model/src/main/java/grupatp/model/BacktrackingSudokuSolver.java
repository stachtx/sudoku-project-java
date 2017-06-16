/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupatp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Tomasz Stachura
 * @author Paweł Ograbek
 */
public class BacktrackingSudokuSolver implements SudokuSolver {

    @Override
    public void solve(final SudokuBoard b) {
              
        List<Integer> solution = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            solution.add(i);
        }
        Collections.shuffle(solution);
        int[] shuffledBoard = solution.stream().mapToInt(i->i).toArray();
        int[] board = new int[81];
        System.arraycopy(shuffledBoard, 0, board, 0, 9);
        int x = 9;
        boolean flag;
        boolean[][] fill = new boolean[82][9];
        do {
            flag = true;
            for (int i = 0; i < 9; i++) {
                if (!fill[x][i]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                for (int i = 0; i < 9; i++) {
                    fill[x][i] = false;
                }
                board[x] = 0;
                x--;
            } else {
                int m = 1;
                while (fill[x][m - 1]) {
                    m++;
                }
                for (int i = 0; i < 9; i++) {
                    if ((b.checkSolve(x, m, board)) && (!fill[x][m - 1])) {
                        board[x] = m;
                        x++;
                        fill[x][m - 1] = true;
                        break;
                    }
                    fill[x][m - 1] = true;
                    m++;
                    if (m > 9) {
                        m = 1;
                    }
                }
            }
        } while (x < 81);
        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                b.setBoard(i, j, board[i + j * 9]);
            }
        }

    }
 
}
