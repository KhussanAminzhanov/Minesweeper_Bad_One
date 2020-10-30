package minesweeper;

import java.util.Arrays;
import java.util.Random;

public class GameField {

    private final char[][] field;
    private final char[][] state;
    private final int rows;
    private final int cols;
    private int[][] mines;
    private int numMarks;

    public GameField(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        field = new char[rows][cols];
        state = new char[rows][cols];
        for (char[] chars : field) Arrays.fill(chars, '.');
        for (char[] chars : state) Arrays.fill(chars, '.');
        numMarks = 0;
    }

    public void putMines(int numMines, int r, int c) {
        Random random = new Random();
        mines = new int[numMines][2];
        int row = random.nextInt(rows);
        int col = random.nextInt(cols);
        for (int i = 0; i < numMines; i++) {
            while (state[row][col] == 'X' || (row == r && col == c)) {
                row = random.nextInt(rows);
                col = random.nextInt(cols);
            }
            state[row][col] = 'X';
            mines[i] = new int[]{row, col};
        }
    }

    public void printField() {
        System.out.println(" |123456789|");
        System.out.println("-|---------|");
        for (int i = 0, fieldLength = field.length; i < fieldLength; i++) {
            char[] chars = field[i];
            System.out.print(i + 1 + "|");
            for (char aChar : chars) {
                System.out.print(aChar);
            }
            System.out.println("|");
        }
        System.out.println("-|---------|");
    }

    public void printState() {
        System.out.println(" |123456789|");
        System.out.println("-|---------|");
        for (int i = 0, fieldLength = field.length; i < fieldLength; i++) {
            char[] chars = state[i];
            System.out.print(i + 1 + "|");
            for (char aChar : chars) {
                System.out.print(aChar);
            }
            System.out.println("|");
        }
        System.out.println("-|---------|");
    }

    public boolean isAllMarked() {
        if (mines == null) return false;
        for (int[] mine : mines) {
            if (field[mine[0]][mine[1]] != '*') return false;
        }
        return numMarks == mines.length;
    }

    public void putNumbers() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (state[i][j] == 'X') inc(i, j);
            }
        }
    }

    public boolean setMark(int row, int col, String command) {
        if (command.equals("mine")) return setMineMark(row, col);
        if (command.equals("free")) return setFreeMark(row, col);
        return true;
    }

    public void showMines() {
        for (int[] mine : mines) {
            field[mine[0]][mine[1]] = 'X';
        }
        printField();
    }

    private void inc(int row, int col) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (row + i >= 0 && row + i < rows && col + j >= 0 && col + j < rows) {
                    if (state[row + i][col + j] == '.') state[row + i][col + j] = '1';
                    else if (state[row + i][col + j] != 'X') state[row + i][col + j]++;
                }
            }
        }
    }

    public void reveal(int row, int col) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (row + i >= 0 && row + i < rows && col + j >= 0 && col + j < rows) {
                    if (field[row + i][col + j] == '/') {
                        if (state[row][col] == '.') field[row][col] = '/';
                        if (state[row][col] >= '1' && state[row][col] <= '8') field[row][col] = state[row][col];
                    }
                }
            }
        }
    }

    private void revealAll(int row, int col) {
        field[row][col] = '/';
        for (int k = 0; k < 10; k++) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    reveal(i, j);
                }
            }
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    reveal(8 - i, 8 - i);
                }
            }
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    reveal(8 - i, j);
                }
            }
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    reveal(i, 8 - j);
                }
            }
        }
    }

    private boolean isMine(int row, int col) {
        for (int[] mine : mines) {
            if (row == mine[0] && col == mine[1]) return true;
        }
        return false;
    }

    private boolean setMineMark(int row, int col) {
        char c = field[row][col];
        if (c == '*') {
            field[row][col] = '.';
            numMarks--;
        } else {
            field[row][col] = '*';
            numMarks++;
        }
        return true;
    }

    private boolean setFreeMark(int row, int col) {
        if (isMine(row, col)) return false;
        if (state[row][col] == '.') {
            revealAll(row, col);
        } else field[row][col] = state[row][col];
        return true;
    }

}
