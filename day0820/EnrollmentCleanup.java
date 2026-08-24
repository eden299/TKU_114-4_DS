package day0820;

import java.util.*;

public class EnrollmentCleanup {
    public static void main(String[] args) {
        List<String> rawList = new ArrayList<>(Arrays.asList(
            "Alice", "Bob", null, "  ", "Alice", "Charlie", "Bob", "  ", "David", null
        ));

        System.out.println("清理前資料: " + rawList);

        Iterator<String> iterator = rawList.iterator();
        while (iterator.hasNext()) {
            String name = iterator.next();
            if (name == null || name.trim().isEmpty()) {
                iterator.remove();
            }
        }

        for (int i = 0; i < rawList.size(); i++) {
            rawList.set(i, rawList.get(i).trim());
        }

        System.out.println("清理後資料: " + rawList);

        Set<String> seen = new HashSet<>();
        Set<String> duplicates = new HashSet<>();

        for (String name : rawList) {
            if (!seen.add(name)) {
                duplicates.add(name);
            }
        }

        System.out.println("重複姓名報告: " + duplicates);
    }
}
