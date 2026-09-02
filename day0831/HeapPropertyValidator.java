package day0831;

import java.util.List;

public class HeapPropertyValidator {
    public static boolean isMinHeap(List<Integer> list) {
        if (list == null) return false;
        int n = list.size();
        if (n <= 1) return true;

        for (int i = 0; i <= (n - 2) / 2; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < n && list.get(i) > list.get(left)) return false;
            if (right < n && list.get(i) > list.get(right)) return false;
        }
        return true;
    }

    public static boolean isMaxHeap(List<Integer> list) {
        if (list == null) return false;
        int n = list.size();
        if (n <= 1) return true;

        for (int i = 0; i <= (n - 2) / 2; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < n && list.get(i) < list.get(left)) return false;
            if (right < n && list.get(i) < list.get(right)) return false;
        }
        return true;
    }
}
