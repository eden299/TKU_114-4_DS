package day0820;

import java.util.*;

public class CourseTagReport {
    public static void main(String[] args) {
        String[] inputTags = {"Java", "Backend", "Database", "Java", "Spring", "Backend", "Java"};

        List<String> tagList = new ArrayList<>(Arrays.asList(inputTags));

        Set<String> tagSet = new HashSet<>(tagList);

        Map<String, Integer> tagCountMap = new HashMap<>();
        for (String tag : tagList) {
            tagCountMap.put(tag, tagCountMap.getOrDefault(tag, 0) + 1);
        }

        System.out.println("原始標籤列表 (List): " + tagList);
        System.out.println("不重複標籤集 (Set): " + tagSet);
        System.out.println("標籤出現頻率 (Map): " + tagCountMap);
    }
}
