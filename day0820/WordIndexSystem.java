package day0820;

import java.util.*;

public class WordIndexSystem {
    public static void main(String[] args) {
        String[] sentences = {
            "Java is great, and Java is powerful.",
            "Learning Java programming is fun!",
            "Practice makes perfect."
        };

        Map<String, Integer> wordCountMap = new HashMap<>();
        Set<String> uniqueWords = new HashSet<>();

        for (String sentence : sentences) {
            String cleaned = sentence.replaceAll("[,.]", "").toLowerCase();
            String[] words = cleaned.split("\\s+");
            
            for (String word : words) {
                if (word.isEmpty()) continue;
                uniqueWords.add(word);
                wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
            }
        }

        List<String> frequentWords = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
            if (entry.getValue() >= 2) {
                frequentWords.add(entry.getKey());
            }
        }

        System.out.println("所有不重複單字 (Set): " + uniqueWords);
        System.out.println("單字出現次數統計 (Map): " + wordCountMap);
        System.out.println("出現至少兩次的單字: " + frequentWords);
    }
}
