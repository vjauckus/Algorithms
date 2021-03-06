/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016.  Dmytro Karataiev
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package Percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] percolations;
    private int experiments;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T) {

        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("illegal argument " + N + " " + T);
        }

        int[] moves = new int[N * N];

        for (int i = 0; i < N * N; i++) {
            moves[i] = i;
        }

        percolations = new double[T];
        experiments = T;

        for (int i = 0; i < T; i++) {
            StdRandom.shuffle(moves);
            Percolation perc = new Percolation(N);
            int turn = 0;

            while (!perc.percolates() && turn < N * N) {

                int row = moves[turn] / N + 1;
                int column = moves[turn] % N;
                if (column == 0) column = N;

                perc.open(row, column);
                perc.isFull(row, column);
                turn++;

            }
            percolations[i] = (double) turn / (N * N);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(percolations);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(percolations);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (1.96 * stddev()) / Math.sqrt(experiments);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (1.96 * stddev()) / Math.sqrt(experiments);
    }

    // test client (described below)
    public static void main(String[] args) {

        if (args.length < 2 || Integer.parseInt(args[0]) <= 0 || Integer.parseInt(args[1]) <= 0) {
            throw new IllegalArgumentException("check arguments, N <= 0, T <= 0");
        }

        // arguments on launch
        int gridSize = Integer.parseInt(args[0]);
        int experiments = Integer.parseInt(args[1]);

        PercolationStats percolationStats = new PercolationStats(gridSize, experiments);

        System.out.print("mean = " + percolationStats.mean() + "\n"
                + "stddev = " + percolationStats.stddev() + "\n"
                + "95% confidence interval = " + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi());
    }


}
