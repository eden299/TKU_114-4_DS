package day0824;

public class RecursiveDigitReport {

    public static int digitSum(int n) {
        n = Math.abs(n);
        if (n < 10) return n;
        return (n % 10) + digitSum(n / 10);
    }

    public static int digitCount(int n) {
        n = Math.abs(n);
        if (n < 10) return 1;
        return 1 + digitCount(n / 10);
    }

    public static int countDigit(int n, int target) {
        n = Math.abs(n);
        target = Math.abs(target);
        if (n < 10) return (n == target) ? 1 : 0;
        return ((n % 10) == target ? 1 : 0) + countDigit(n / 10, target);
    }

    public static void main(String[] args) {
        int[] testCases = {50205, 0, -731};
        for (int num : testCases) {
            System.out.println("Number: " + num);
            System.out.println("  Digit Sum: " + digitSum(num));
            System.out.println("  Digit Count: " + digitCount(num));
            System.out.println("  Count of '0': " + countDigit(num, 0));
            System.out.println();
        }
    }
}
