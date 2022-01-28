import java.io.*;
import java.util.*;
// Add any extra import statements you may need here

class Main {
    class MaxHeap {
        private int[] heap;
        private int maxSize;
        private int size;
        public MaxHeap(int maxSize) {
            this.heap = new int[maxSize];
            this.maxSize = maxSize;
            this.size = 0;
        }

        private int parent(int pos) { return (pos - 1) / 2; }
        private int leftChild(int pos) { return (pos * 2) + 1; }
        private int rightChild(int pos) { return (pos * 2) + 2; }
    }
    class LargestTripleProduct {
        int[] arr;

        public LargestTripleProduct(int[] arr) {
            this.arr = arr;
        }

        public int[] calculate() {
            int[] tripleProductArray = new int[this.arr.length];
            for (int i = 0; i < this.arr.length; i++) {
                tripleProductArray[i] = calculateIndexValue(i);
            }
            return tripleProductArray;
        }
        
        public int calculateIndexValue(int index) {
            if (index < 2)
                return -1;
            return index;
        }
    }

    // Add any helper functions you may need here

    int[] findMaxProduct(int[] arr) {
        LargestTripleProduct calculator = new LargestTripleProduct(arr);
        return arr;
    }

    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom.
    int test_case_number = 1;

    void check(int[] expected, int[] output) {
        int expected_size = expected.length;
        int output_size = output.length;
        boolean result = true;
        if (expected_size != output_size) {
            result = false;
        }
        for (int i = 0; i < Math.min(expected_size, output_size); i++) {
            result &= (output[i] == expected[i]);
        }
        char rightTick = '\u2713';
        char wrongTick = '\u2717';
        if (result) {
            System.out.println(rightTick + " Test #" + test_case_number);
        } else {
            System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
            printIntegerArray(expected);
            System.out.print(" Your output: ");
            printIntegerArray(output);
            System.out.println();
        }
        test_case_number++;
    }

    void printIntegerArray(int[] arr) {
        int len = arr.length;
        System.out.print("[");
        for (int i = 0; i < len; i++) {
            if (i != 0) {
                System.out.print(", ");
            }
            System.out.print(arr[i]);
        }
        System.out.print("]");
    }

    public void run() {
        int[] arr_1 = { 1, 2, 3, 4, 5 };
        int[] expected_1 = { -1, -1, 6, 24, 60 };
        int[] output_1 = findMaxProduct(arr_1);
        check(expected_1, output_1);

        int[] arr_2 = { 2, 4, 7, 1, 5, 3 };
        int[] expected_2 = { -1, -1, 56, 56, 140, 140 };
        int[] output_2 = findMaxProduct(arr_2);
        check(expected_2, output_2);

        // Add your own test cases here
        System.out.println(7 / 2);

    }

    public static void main(String[] args) {
        new Main().run();
    }
}