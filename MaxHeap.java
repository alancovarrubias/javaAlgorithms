public class MaxHeap {
    private int[] heap;
    private int size;
    private int maxsize;

    public MaxHeap(int maxsize) {
        this.maxsize = maxsize;
        size = 0;
        heap = new int[this.maxsize];
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

    private void add(int element) {
        heap[size++] = element;
        heapifyUp();
    }

    private int peek() {
        if (size == 0)
            throw new RuntimeException();
        return heap[0];
    }

    private int remove() {
        if (size == 0)
            throw new RuntimeException();
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
            } else {
                break;
            }
            index = largestChildIndex;
        }
    }

    private void heapifyUp() {
        int index = size - 1;
        while (hasParent(index) && parent(index) < heap[index]) {
            swap(index, getParentIndex(index));
            index = getParentIndex(index);
        }
    }

    public void printHeap() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println("");
    }

    // Method 10
    // main dri er method
    public static void main(String[] arg) {
        System.out.println(5/2);
    }
}
