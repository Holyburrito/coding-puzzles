import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ThreeSumToTargetSolution {

    public static void main(String[] args) {
        Scanner targetScanner = new Scanner(System.in);

        // Read a line of input
        System.out.println("Enter targets:");
        String targets = targetScanner.nextLine();
        targetScanner.close();

        // Split the string by whitespace
        String[] tokens = targets.trim().split("\\s+");
        int[] sortedTargets = ThreeSumToTarget.sortIntArray(tokens);

        for (String arg : args) {
            //logging
            System.out.println(arg);

            // loops each line of input
            Scanner scanner = new Scanner(arg);
            String intsDelimited = scanner.nextLine();
            scanner.close();

            String[] integerListAsStringArr = intsDelimited.trim().split(",");
            int[] sortedNumbers = ThreeSumToTarget.sortIntArray(integerListAsStringArr);
            for  (int target : sortedTargets) {
                for (int[] ints : threeNumberSum(sortedNumbers, target)) {
                    System.out.println("[ " + ints[0] + "," + ints[1] + "," + ints[2] + "] = " + target);
                }
            }
        }
    }

    // Method to find all triplets with the given sum
    private static List<int[]> threeNumberSum ( int[] arr, int targetSum) {
        Arrays.sort(arr);
        List<int[]> triplets = new ArrayList<>();

        for (int i = 0; i < arr.length - 2; i++) {
            int left = i + 1;
            int right = arr.length - 1;

            while (left < right) {
                int currentSum = arr[i] + arr[left] + arr[right];

                if (currentSum == targetSum) {
                    triplets.add(new int[]{arr[i], arr[left], arr[right]});
                    left++;
                    right--;
                } else if (currentSum < targetSum) {
                    left++;
                } else if (currentSum > targetSum) {
                    right--;
                }
            }
        }
        return triplets;
    }
}
