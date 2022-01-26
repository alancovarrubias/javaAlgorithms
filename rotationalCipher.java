import java.util.*;
// Add any extra import statements you may need here

class Main {

    char rotateLowercaseChar(char input, int rotationFactor) {
        Rotator numericRotator = new Rotator(97, 26);
        return numericRotator.rotateChar(input, rotationFactor);
    }

    char rotateUppercaseChar(char input, int rotationFactor) {
        Rotator numericRotator = new Rotator(65, 26);
        return numericRotator.rotateChar(input, rotationFactor);
    }

    char rotateNumericChar(char input, int rotationFactor) {
        Rotator numericRotator = new Rotator(48, 10);
        return numericRotator.rotateChar(input, rotationFactor);
    }

    char rotateChar(char input, int rotationFactor) {
        if (input >= 48 && input <= 57) {
            return rotateNumericChar(input, rotationFactor);
        } else if (input >= 65 && input <= 90) {
            return rotateUppercaseChar(input, rotationFactor);
        } else if (input >= 97 && input <= 122) {
            return rotateLowercaseChar(input, rotationFactor);
        } else {
            return input;
        }
    }

    String rotationalCipher(String input, int rotationFactor) {
        char[] charArray = input.toCharArray();
        StringBuilder strBuilder = new StringBuilder();
        for (char charInput : charArray) {
            char rotatedInput = rotateChar(charInput, rotationFactor);
            strBuilder.append(rotatedInput);
        }
        return strBuilder.toString();
    }

    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom.
    int test_case_number = 1;

    void check(String expected, String output) {
        boolean result = (expected.equals(output));
        char rightTick = '\u2713';
        char wrongTick = '\u2717';
        if (result) {
            System.out.println(rightTick + " Test #" + test_case_number);
        } else {
            System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
            printString(expected);
            System.out.print(" Your output: ");
            printString(output);
            System.out.println();
        }
        test_case_number++;
    }

    void printString(String str) {
        System.out.print("[\"" + str + "\"]");
    }

    public void run() {
        String input_1 = "All-convoYs-9-be:Alert1.";
        int rotationFactor_1 = 4;
        String expected_1 = "Epp-gsrzsCw-3-fi:Epivx5.";
        String output_1 = rotationalCipher(input_1, rotationFactor_1);
        check(expected_1, output_1);

        String input_2 = "abcdZXYzxy-999.@";
        int rotationFactor_2 = 200;
        String expected_2 = "stuvRPQrpq-999.@";
        String output_2 = rotationalCipher(input_2, rotationFactor_2);
        check(expected_2, output_2);

        // Add your own test cases here

    }

    public static void main(String[] args) {
        new Main().run();
    }
}

class Rotator {
    int base;
    int setLength;
    public Rotator(int base, int setLength) {
        this.base = base;
        this.setLength = setLength;
    }

    char rotateChar(char input, int rotationFactor) {
        int zeroBasedInput = input - this.base;
        int rotatedNumeric = (zeroBasedInput + rotationFactor) % this.setLength;
        return (char) (rotatedNumeric + this.base);
    }
}