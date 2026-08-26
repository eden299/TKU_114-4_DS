package day0824;

public class RecursiveTextTools {

    public static String reverse(String s) {
        if (s == null || s.length() <= 1) return s;
        return reverse(s.substring(1)) + s.charAt(0);
    }

    public static boolean isPalindrome(String s) {
        if (s == null) return false;
        String cleaned = s.replaceAll("\\s+", "").toLowerCase();
        return isPalindromeHelper(cleaned, 0, cleaned.length() - 1);
    }

    private static boolean isPalindromeHelper(String s, int left, int right) {
        if (left >= right) return true;
        if (s.charAt(left) != s.charAt(right)) return false;
        return isPalindromeHelper(s, left + 1, right - 1);
    }

    public static int countCharacter(String s, char target) {
        if (s == null || s.isEmpty()) return 0;
        int count = (s.charAt(0) == target) ? 1 : 0;
        return count + countCharacter(s.substring(1), target);
    }

    public static void main(String[] args) {
        String[] testStrings = {"", "a", "Level", "racecar", "Hello World"};

        System.out.println("=== Reverse Test ===");
        for (String str : testStrings) {
            System.out.println("\"" + str + "\" -> \"" + reverse(str) + "\"");
        }

        System.out.println("\n=== Palindrome Test ===");
        for (String str : testStrings) {
            System.out.println("\"" + str + "\" is Palindrome? " + isPalindrome(str));
        }

        System.out.println("\n=== Count Character 'l' Test ===");
        for (String str : testStrings) {
            System.out.println("\"" + str + "\" count of 'l': " + countCharacter(str, 'l'));
        }
    }
}
