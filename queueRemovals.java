import java.io.*;
import java.util.*;
// Add any extra import statements you may need here

class Main {
    class PositionFinder {
        private Element[] elements;
        private int iterations;
        private int[] positions;
        private int positionIndex;
        private Queue<Element> queue;
        private Queue<Element> tempQueue;
        private PriorityQueue<Integer> maxHeap;

        public PositionFinder(int[] arr, int x) {
            this.positions = new int[x];
            this.positionIndex = 0;
            this.iterations = x;
            this.queue = new LinkedList<>();
            this.maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            this.tempQueue = new LinkedList<>();
            this.elements = createElements(arr);
        }

        public int[] findPositions() {
            fillQueue();
            while (iterations > 0) {
                popFromQueue();
                addBackToQueue();
                iterations--;
            }
            return positions;
        }

        private void addBackToQueue() {
            int maxValue = maxHeap.peek();
            maxHeap.clear();
            int initialPositionIndex = positionIndex;
            while (tempQueue.peek() != null) {
                Element e = tempQueue.poll();
                boolean elementIsMax = e.value == maxValue;
                boolean positionNotYetAdded = initialPositionIndex == positionIndex;
                if (elementIsMax && positionNotYetAdded) {
                    addElementIndexToPosition(e);
                } else {
                    decrementElementAndAddToQueue(e);
                }
            }
        }

        private void addElementIndexToPosition(Element e) {
            positions[positionIndex] = e.index;
            positionIndex++;
        }

        private void decrementElementAndAddToQueue(Element e) {
            e.value = e.value > 0 ? e.value - 1 : 0;
            queue.add(e);
        }

        private void popFromQueue() {
            for (int i = 0; i < positions.length && queue.peek() != null; i++) {
                Element element = queue.poll();
                tempQueue.add(element);
                maxHeap.add(element.value);
            }
        }

        private void fillQueue() {
            for (Element element : elements)
                queue.add(element);
        }

        private Element[] createElements(int[] arr) {
            Element[] elements = new Element[arr.length];
            for (int i = 0; i < arr.length; i++)
                elements[i] = new Element(arr[i], i + 1);
            return elements;
        }
    }

    class Element {
        int value;
        int index;

        public Element(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    int[] findPositions(int[] arr, int x) {
        PositionFinder finder = new PositionFinder(arr, x);
        return finder.findPositions();
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
        int n_1 = 6;
        int x_1 = 5;
        int[] arr_1 = { 1, 2, 2, 3, 4, 5 };
        int[] expected_1 = { 5, 6, 4, 1, 2 };
        int[] output_1 = findPositions(arr_1, x_1);
        check(expected_1, output_1);

        int n_2 = 13;
        int x_2 = 4;
        int[] arr_2 = { 2, 4, 2, 4, 3, 1, 2, 2, 3, 4, 3, 4, 4 };
        int[] expected_2 = { 2, 5, 10, 13 };
        int[] output_2 = findPositions(arr_2, x_2);
        check(expected_2, output_2);

        // Add your own test cases here

    }

    public static void main(String[] args) {
        new Main().run();
    }
}