import java.io.BufferedReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class ThreeSumToTarget {
    //Input: nums = [-1, 0, 1, 2, -1, -4], target = 0 Output: [[-1, -1, 2], [-1, 0, 1]]
    public static void main(String[] args) {
    /*
    * Goal :
    * Given an array of integers and a target sum,
    * find all unique triplets that add up to the target.
    * Return the triplets as a List of List<Integer>.
     */
        Scanner targetScanner = new Scanner(System.in);

        // Read a line of input
        System.out.println("Enter targets:");
        String targets = targetScanner.nextLine();
        targetScanner.close();

        // Split the string by whitespace
        String[] tokens = targets.trim().split("\\s+");
        int[] sortedTargets = sortIntArray(tokens);

        for (String arg : args) {
            //logging
            System.out.println(arg);

            // loops each line of input
            Scanner scanner = new Scanner(arg);
            String intsDelimited = scanner.nextLine();
            scanner.close();

            String[] integerListAsStringArr = intsDelimited.trim().split(",");
            int[] sortedNumbers = sortIntArray(integerListAsStringArr);

            int max;
            int maxTwoSum;
            int min;
            int minTwoSum;

            max = sortedNumbers[sortedNumbers.length - 1];
            maxTwoSum = max + sortedNumbers[sortedNumbers.length - 2];
            min = sortedNumbers[0];
            minTwoSum = min + sortedNumbers[1];

            for (int target : sortedTargets) {

                for (int xOptionsIndex = 0; xOptionsIndex < sortedNumbers.length; xOptionsIndex++) {

                    int x = sortedNumbers[xOptionsIndex];
                    if (x + maxTwoSum >= target && x + minTwoSum <= target) {

                        final int[] yOptions = removeFirstNElements(sortedNumbers, xOptionsIndex + 1);
                        for (int yOptionsIndex = 0; yOptionsIndex < yOptions.length; yOptionsIndex++) {

                            int y = yOptions[yOptionsIndex];
                            if (x + y + max >= target && x + y + min <= target) {
                                final int[] zOptions = removeFirstNElements(yOptions, yOptionsIndex + 1);
                                for (int z : zOptions) {

                                    if (x + y + z == target) {
                                        System.out.println("[ " + x + ", " + y + ", " + z + " ] = " + target);
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }
    }

    private static int[] removeFirstNElements(int[] original, int n) {

        if (original.length == 0) {
            System.out.println("Array is empty. Nothing to remove.");
            return new int[0];
        }
        // Create a new array with one less element
        int[] shorter = new int[original.length - n];

        System.arraycopy(original, n, shorter, 0, original.length - n);
        return shorter;
    }

    private static int[] sortIntArray(String[] arr) {
        int[] numbers = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            numbers[i] = Integer.parseInt(arr[i]);
        }
        Arrays.sort(numbers);
        return numbers;
    }
}