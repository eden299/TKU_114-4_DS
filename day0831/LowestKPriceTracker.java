package day0831;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class LowestKPriceTracker {
    public static List<Integer> getLowestKPrices(List<Integer> prices, int k) {
        if (k <= 0 || prices == null) return new ArrayList<>();

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (Integer price : prices) {
            if (price == null || price < 0) continue;

            if (maxHeap.size() < k) {
                maxHeap.add(price);
            } else if (price < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.add(price);
            }
        }

        List<Integer> result = new ArrayList<>(maxHeap);
        Collections.sort(result);
        return result;
    }
}
