package day0821;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListImplementationLab {

    public static void processList(List<Integer> list) {

        list.add(10);
        list.add(20);
        list.add(30);

        list.add(1, 15);

        int searchTarget = 20;
        boolean contains = list.contains(searchTarget);

        list.remove(2);

        int sum = 0;
        for (int num : list) {
            sum += num;
        }

        System.out.println("最終 List 內容: " + list);
        System.out.println("是否包含 " + searchTarget + ": " + contains);
        System.out.println("元素總和: " + sum);
    }

    public static void main(String[] args) {
        System.out.println("--- 測試 ArrayList ---");
        processList(new ArrayList<>());

        System.out.println("\n--- 測試 LinkedList ---");
        processList(new LinkedList<>());
    }
}
