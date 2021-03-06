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

package FibGcmLcm.fibonacci;

import java.util.Scanner;

/**
 * Simple iterative Fibonacci implementation
 */
public class Fib {

    /**
     * Method to calculate FibGcmLcm.fibonacci numbers
     * @param n with constraints 0 <= n <= 45.
     * @return calculated fib number
     */
    private static long fib(int n) {

        if (n < 0 || n > 45) {
            return -1;
        }

        if (n <= 1) {
            return n;
        }

        int[] sum = new int[n + 1];
        sum[0] = 0;
        sum[1] = 1;

        for (int i = 2; i <= n; i++) {
            sum[i] = sum[i - 1] + sum[i - 2];
        }

        return sum[n];
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        System.out.println(fib(n));
    }
}
