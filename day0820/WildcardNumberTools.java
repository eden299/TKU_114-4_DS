package day0820;

import java.util.ArrayList;
import java.util.List;

public class WildcardNumberTools {

    public static double average(List<? extends Number> values) {
        if (values == null || values.isEmpty()) {
            return 0.0;
        }
        double sum = 0.0;
        for (Number num : values) {
            if (num != null) {
                sum += num.doubleValue();
            }
        }
        return sum / values.size();
    }

    public static double maximum(List<? extends Number> values) {
        if (values == null || values.isEmpty()) {
            return Double.NaN;
        }
        double max = Double.NEGATIVE_INFINITY;
        for (Number num : values) {
            if (num != null) {
                double val = num.doubleValue();
                if (val > max) {
                    max = val;
                }
            }
        }
        return max == Double.NEGATIVE_INFINITY ? Double.NaN : max;
    }

    public static void addRange(List<? super Integer> target, int start, int end) {
        if (target == null || start > end) {
            return;
        }
        for (int i = start; i <= end; i++) {
            target.add(i);
        }
    }

    public static void main(String[] args) {
        List<Integer> intList = List.of(10, 20, 30);
        List<Double> doubleList = List.of(1.5, 2.5, 3.5);
        List<Integer> emptyList = new ArrayList<>();

        System.out.println("Integer 平均: " + average(intList));
        System.out.println("Double 最大值: " + maximum(doubleList));
        System.out.println("空 List 平均: " + average(emptyList));
        System.out.println("空 List 最大值: " + maximum(emptyList));

        List<Number> numList = new ArrayList<>();
        addRange(numList, 1, 5);
        System.out.println("addRange 結果: " + numList);
    }
}
