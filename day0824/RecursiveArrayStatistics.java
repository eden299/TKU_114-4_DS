package day0824;

public class RecursiveArrayStatistics {

    public static int maximum(int[] arr) {
        if (arr == null || arr.length == 0) throw new IllegalArgumentException("Array cannot be null or empty.");
        return maxHelper(arr, 0);
    }

    private static int maxHelper(int[] arr, int index) {
        if (index == arr.length - 1) return arr[index];
        return Math.max(arr[index], maxHelper(arr, index + 1));
    }

    public static int minimum(int[] arr) {
        if (arr == null || arr.length == 0) throw new IllegalArgumentException("Array cannot be null or empty.");
        return minHelper(arr, 0);
    }

    private static int minHelper(int[] arr, int index) {
        if (index == arr.length - 1) return arr[index];
        return Math.min(arr[index], minHelper(arr, index + 1));
    }

    public static int countAbove(int[] arr, int threshold) {
        if (arr == null || arr.length == 0) throw new IllegalArgumentException("Array cannot be null or empty.");
        return countAboveHelper(arr, threshold, 0);
    }

    private static int countAboveHelper(int[] arr, int threshold, int index) {
        if (index == arr.length) return 0;
        int count = arr[index] > threshold ? 1 : 0;
        return count + countAboveHelper(arr, threshold, index + 1);
    }

    public static void main(String[] args) {
        int[] arr = {12, -5, 8, 20, 3, 15};
        System.out.println("Max: " + maximum(arr));
        System.out.println("Min: " + minimum(arr));
        System.out.println("Count above 10: " + countAbove(arr, 10));

        try {
            maximum(new int[]{});
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }
    }
}
