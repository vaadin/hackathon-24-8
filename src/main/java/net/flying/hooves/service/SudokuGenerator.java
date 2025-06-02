package net.flying.hooves.service;


import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SudokuGenerator {

    private static final int SIZE = 9;
    private static final int SUBGRID = 3;
    private final int[][] board = new int[SIZE][SIZE];
    private final Random random = new Random();

    public int[][] generate() {
        fillBoard(0, 0);
        return board;
    }

    private boolean fillBoard(int row, int col) {
        if (row == SIZE) return true;  // Finished all rows

        int nextRow = (col == SIZE - 1) ? row + 1 : row;
        int nextCol = (col + 1) % SIZE;

        int[] numbers = randomOrder();

        for (int num : numbers) {
            if (isValid(row, col, num)) {
                board[row][col] = num;
                if (fillBoard(nextRow, nextCol)) return true;
                board[row][col] = 0; // Backtrack
            }
        }

        return false;
    }

    private int[] randomOrder() {
        int[] numbers = new int[SIZE];
        for (int i = 0; i < SIZE; i++) {
            numbers[i] = i + 1;
        }

        for (int i = SIZE - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = numbers[i];
            numbers[i] = numbers[j];
            numbers[j] = temp;
        }

        return numbers;
    }

    private boolean isValid(int row, int col, int num) {
        // Check row and column
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == num || board[i][col] == num)
                return false;
        }

        // Check 3x3 subgrid
        int boxRowStart = row - row % SUBGRID;
        int boxColStart = col - col % SUBGRID;

        for (int i = 0; i < SUBGRID; i++) {
            for (int j = 0; j < SUBGRID; j++) {
                if (board[boxRowStart + i][boxColStart + j] == num)
                    return false;
            }
        }

        return true;
    }

    public void printBoard() {
        for (int r = 0; r < SIZE; r++) {
            for (int d = 0; d < SIZE; d++) {
                System.out.print(board[r][d] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Removes cells from a full Sudoku board and returns their indexes
     * @param board The completed Sudoku board (9x9) to modify in-place
     * @param cellsToRemove Number of cells to remove (difficulty control)
     * @return List of removed cell positions (each as [row, col])
     */
    public static List<Pair<Integer, Integer>> makePlayable(int[][] board, int cellsToRemove) {
        List<Pair<Integer, Integer>> removedIndexes = new ArrayList<>();

        List<int[]> allPositions = new ArrayList<>();
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                allPositions.add(new int[]{row, col});
            }
        }

        Collections.shuffle(allPositions);
        int removedCount = 0;

        for (int[] pos : allPositions) {
            if (removedCount >= cellsToRemove) break;

            int row = pos[0];
            int col = pos[1];

            int backup = board[row][col];
            board[row][col] = 0; // Remove it

            // For simplicity, we’re not checking uniqueness here.
            // If you want unique puzzles, use a solver check here.
            removedIndexes.add(new ImmutablePair<>(row, col));
            removedCount++;
        }

        return removedIndexes;
    }

    // Test the generator
    public static void main(String[] args) {
        SudokuGenerator generator = new SudokuGenerator();
        generator.generate();
        generator.printBoard();
    }
}
