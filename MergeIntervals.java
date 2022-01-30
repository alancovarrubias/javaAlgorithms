import java.util.ArrayList;

public class MergeIntervals {
    private int[][] user1;
    private int[][] user2;
    private int user1Length;
    private int user2Length;

    public MergeIntervals(int[][] user1, int[][] user2) {
        this.user1 = user1;
        this.user2 = user2;
        this.user1Length = user1.length;
        this.user2Length = user2.length;
    }

    public ArrayList<int[]> merge() {
        int user1Index = 0;
        int user2Index = 0;
        ArrayList<int[]> intervalList = new ArrayList<>();
        while (user1Index < user1Length || user2Index < user2Length) {
            if (user1Index < user1Length && user2Index < user2Length) {
                int[] merged = mergeIntervals(user1[user1Index], user2[user2Index]);
                while ((user1Index < user1Length && withinInterval(merged, user1[user1Index])) || (user2Index < user2Length && withinInterval(merged, user2[user2Index]))) {
                    if (user1Index < user1Length && withinInterval(merged, user1[user1Index])) {
                        merged = mergeIntervals(merged, user1[user1Index]);
                        user1Index++;
                    }
                    if (user2Index < user2Length && withinInterval(merged, user2[user2Index])) {
                        merged = mergeIntervals(merged, user2[user2Index]);
                        user2Index++;
                    }
                }
                intervalList.add(merged);
            } else if (user1Index < user1Length) {
                while (user1Index < user1Length)
                    intervalList.add(user1[user1Index++]);
            } else if (user2Index < user2Length) {
                while (user2Index < user2Length)
                    intervalList.add(user2[user2Index++]);
            }
        }
        return intervalList;
    }

    private int[] mergeIntervals(int[] interval1, int[] interval2) {
        int[] start = startInterval(interval1, interval2);
        int[] other = otherInterval(interval1, interval2);
        if (withinInterval(start, other)) {
            int endTime = latestEndTime(start, other);
            int[] merged = { start[0], endTime };
            return merged;
        } else {
            return start;
        }
    }

    private int latestEndTime(int[] interval1, int[] interval2) {
        return interval1[1] >= interval2[1] ? interval1[1] : interval2[1];
    }

    private int[] startInterval(int[] interval1, int[] interval2) {
        return interval1[0] <= interval2[0] ? interval1 : interval2;
    }

    private int[] otherInterval(int[] interval1, int[] interval2) {
        return interval1[0] <= interval2[0] ? interval2 : interval1;
    }

    private boolean withinInterval(int[] interval, int[] testInterval) {
        return testInterval[0] >= interval[0] && testInterval[0] <= interval[1];
    }

    public static void main(String[] args) {
        System.out.println("hey");
    }
}
