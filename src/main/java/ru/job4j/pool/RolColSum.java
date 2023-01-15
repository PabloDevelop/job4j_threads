package ru.job4j.pool;

import org.apache.log4j.Logger;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {
    public static class Sums {
        private int rowSum;
        private int colSum;

        public Sums(int rowSum, int colSum) {
            this.rowSum = rowSum;
            this.colSum = colSum;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Sums sums = (Sums) o;
            return rowSum == sums.rowSum && colSum == sums.colSum;
        }

        @Override
        public int hashCode() {
            return Objects.hash(rowSum, colSum);
        }
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            int row = 0;
            int column = 0;
            for (int j = column; j < matrix[0].length; j++) {
                row += matrix[i][j];
                column += matrix[j][i];
            }
            sums[i] = new Sums(row, column);
        }
        return sums;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            sums[i] = doaAsync(matrix, i).get();
        }
        return sums;
    }

    private static CompletableFuture<Sums> doaAsync(int[][] matrix, int i) {
        return CompletableFuture.supplyAsync(() -> {
            int row = 0;
            int column = 0;
            for (int j = column; j < matrix[0].length; j++) {
                row += matrix[i][j];
                column += matrix[j][i];
            }
            return new Sums(row, column);
        });
    }
}