import java.util.stream.IntStream;
import java.util.Arrays;
import java.util.Set;
import java.util.Queue;
import java.util.HashSet;
import java.util.LinkedList;

class MinimizePermutations {
    private int[] arr;
    private int[] target;
    private int operations;
    private Set<String> seen = new HashSet<>();
    private Queue<int[]> queue = new LinkedList<>();

    public MinimizePermutations(int[] arr) {
        this.arr = arr;
        this.target = IntStream.rangeClosed(1, arr.length).toArray();
        this.operations = 0;
    }

    public int minOperations() {
        addArrayToVisited(arr);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.remove();
                if (Arrays.equals(target, curr))
                    return operations;
                addPermutations(curr);
            }
            operations++;
        }
        return operations;
    }

    private void addPermutations(int[] curr) {
        for (int j = 0; j < curr.length; j++) {
            for (int k = j + 1; k < curr.length; k++)
                addPermutation(curr, j, k);
        }
    }

    private void addPermutation(int[] curr, int j, int k) {
        int[] next = curr.clone();
        reverse(next, j, k);
        if (!seen.contains(Arrays.toString(next)))
            addArrayToVisited(next);
    }

    private void addArrayToVisited(int[] arr) {
        queue.add(arr);
        seen.add(Arrays.toString(arr));
    }

    private void reverse(int[] arr, int from, int to) {
        for (; from < to; from++, to--) {
            int tmp = arr[from];
            arr[from] = arr[to];
            arr[to] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] arr = { 3, 1, 2 };
        int operations = new MinimizePermutations(arr).minOperations();
        System.out.println(operations);
    }
}
