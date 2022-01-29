import java.io.*;
import java.util.*;
// Add any extra import statements you may need here

class Main {

    class MaxHeap {
        int maxSize;
        int[] heap;
        int size;

        public MaxHeap(int maxSize) {
            this.maxSize = maxSize;
            this.heap = new int[maxSize];
            this.size = 0;
        }

        private int getLeftChildIndex(int parentIndex) {
            return parentIndex * 2 + 1;
        }

        private int getRightChildIndex(int parentIndex) {
            return parentIndex * 2 + 2;
        }

        private int getParentIndex(int childIndex) {
            return (childIndex - 1) / 2;
        }

        private boolean hasLeftChild(int parentIndex) {
            return getLeftChildIndex(parentIndex) < size;
        }

        private boolean hasRightChild(int parentIndex) {
            return getRightChildIndex(parentIndex) < size;
        }

        private boolean hasParent(int childIndex) {
            return getParentIndex(childIndex) >= 0;
        }

        private int rightChild(int parentIndex) {
            return heap[getRightChildIndex(parentIndex)];
        }

        private int leftChild(int parentIndex) {
            return heap[getLeftChildIndex(parentIndex)];
        }

        private int parent(int childIndex) {
            return heap[getParentIndex(childIndex)];
        }

        private void swap(int index1, int index2) {
            int temp = heap[index1];
            heap[index1] = heap[index2];
            heap[index2] = temp;
        }

        public int[] peekThree() {
            if (size < 3) {
                int[] notEnoughArray = { 1, 1, -1 };
                return notEnoughArray;
            }
            int[] topThree = { heap[0], heap[1], heap[2] };
            return topThree;
        }

        public void add(int element) {
            heap[size++] = element;
            heapifyUp();
        }

        private void heapifyUp() {
            int index = size - 1;
            while (hasParent(index) && parent(index) < heap[index]) {
                swap(index, getParentIndex(index));
                index = getParentIndex(index);
            }
        }

        public int remove() {
            int element = heap[0];
            heap[0] = heap[--size];
            heapifyDown();
            return element;
        }

        private void heapifyDown() {
            int index = 0;
            while (hasLeftChild(index)) {
                int largestChildIndex = getLeftChildIndex(index);
                if (hasRightChild(index) && rightChild(index) > leftChild(index)) {
                    largestChildIndex = getRightChildIndex(index);
                }
                if (heap[index] < heap[largestChildIndex]) {
                    swap(index, largestChildIndex);
                    index = largestChildIndex;
                } else {
                    break;
                }
            }
        }
    }
    // Add any helper functions you may need here

    int[] findMaxProduct(int[] arr) {
        // Write your code here
        int[] maxProductArray = new int[arr.length];
        MaxHeap maxHeap = new MaxHeap(arr.length);
        for (int i = 0; i < arr.length; i++) {
            maxHeap.add(arr[i]);
            if (i < 2) {
                maxProductArray[i] = -1;
            } else {
                int one = maxHeap.remove();
                int two = maxHeap.remove();
                int three = maxHeap.remove();
                maxProductArray[i] = one * two * three;
                maxHeap.add(three);
                maxHeap.add(two);
                maxHeap.add(one);
            }
        }
        return maxProductArray;
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

    }

    public static void main(String[] args) {
        new Main().run();
    }
}