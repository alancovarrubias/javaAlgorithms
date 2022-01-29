import java.util.*;
// Add any extra import statements you may need here

class Main {
    // Add any helper functions you may need here

    class BalanceBrackets {
        String bracketString;
        Stack<Character> openStack;

        public BalanceBrackets(String s) {
            bracketString = s;
            openStack = new Stack<>();
        }

        public boolean isBalanced() {
            for (char bracket : bracketString.toCharArray()) {
                if (isOpenBracket(bracket)) {
                    openStack.push(bracket);
                } else {
                    if (openStack.empty())
                        return false;
                    if (!bracketsMatch(openStack.pop(), bracket))
                        return false;
                }
            }
            return openStack.empty();
        }

        private boolean isOpenBracket(char ch) {
            String openBrackets = "({[";
            return openBrackets.indexOf(ch) != -1;
        }

        private boolean bracketsMatch(char openBracket, char closeBracket) {
            switch (openBracket) {
                case '{':
                    return closeBracket == '}';
                case '[':
                    return closeBracket == ']';
                case '(':
                    return closeBracket == ')';
            }
            throw new RuntimeException();
        }
    }

    boolean isBalanced(String s) {
        // Write your code here
        return new BalanceBrackets(s).isBalanced();
    }

    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom.
    int test_case_number = 1;

    void check(boolean expected, boolean output) {
        boolean result = (expected == output);
        char rightTick = '\u2713';
        char wrongTick = '\u2717';
        if (result) {
            System.out.println(rightTick + " Test #" + test_case_number);
        } else {
            System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
            System.out.print(expected);
            System.out.print(" Your output: ");
            System.out.print(output);
            System.out.println();
        }
        test_case_number++;
    }

    void printString(String str) {
        System.out.print("[" + str + "]");
    }

    public void run() {
        String s_1 = "{[(])}";
        boolean expected_1 = false;
        boolean output_1 = isBalanced(s_1);
        check(expected_1, output_1);

        String s_2 = "{{[[(())]]}}";
        boolean expected_2 = true;
        boolean output_2 = isBalanced(s_2);
        check(expected_2, output_2);

        // Add your own test cases here

    }

    public static void main(String[] args) {
        new Main().run();
    }
}