package day0831;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StudentIdHashAnalysis {
    public static class AnalysisResult {
        int bucketCount;
        int totalElements;
        int totalCollisions;
        int maxChainLength;
        double averageChainLength;

        public void print() {
            System.out.println("=== Bucket Count: " + bucketCount + " ===");
            System.out.println("Total Elements: " + totalElements);
            System.out.println("Total Collisions: " + totalCollisions);
            System.out.println("Max Chain Length: " + maxChainLength);
            System.out.println("Avg Chain Length (non-empty): " + String.format("%.2f", averageChainLength));
            System.out.println();
        }
    }

    public static AnalysisResult analyze(List<String> studentIds, int bucketCount) {
        List<LinkedList<String>> buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new LinkedList<>());
        }

        for (String id : studentIds) {
            int idx = Math.abs(id.hashCode()) % bucketCount;
            buckets.get(idx).add(id);
        }

        int totalCollisions = 0;
        int maxChain = 0;
        int nonEmptyBuckets = 0;

        for (LinkedList<String> bucket : buckets) {
            int chainLen = bucket.size();
            if (chainLen > 0) {
                nonEmptyBuckets++;
                totalCollisions += (chainLen - 1);
            }
            if (chainLen > maxChain) {
                maxChain = chainLen;
            }
        }

        AnalysisResult result = new AnalysisResult();
        result.bucketCount = bucketCount;
        result.totalElements = studentIds.size();
        result.totalCollisions = totalCollisions;
        result.maxChainLength = maxChain;
        result.averageChainLength = nonEmptyBuckets == 0 ? 0 : (double) studentIds.size() / nonEmptyBuckets;

        return result;
    }

    public static void main(String[] args) {
        List<String> studentIds = new ArrayList<>();
        for (int i = 1000; i < 1200; i++) {
            studentIds.add("S" + i);
        }

        AnalysisResult res1 = analyze(studentIds, 10);
        AnalysisResult res2 = analyze(studentIds, 31);

        res1.print();
        res2.print();
    }
}