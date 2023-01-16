package ru.job4j.pool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {

    private static Sums sumCalc(int[][] matrix, int i) {
        int row = 0;
        int column = 0;
        for (int j = column; j < matrix[0].length; j++) {
            row += matrix[i][j];
            column += matrix[j][i];
        }
        return new Sums(row, column);
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            sums[i] = sumCalc(matrix, i);
        }
        return sums;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            int row = i;
            sums[i] = CompletableFuture.supplyAsync(() -> sumCalc(matrix, row)).get();
        }
        return sums;
    }
}