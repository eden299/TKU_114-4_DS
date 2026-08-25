package day0821;

import java.util.*;

public class CollectionChoiceReport {

    public static void main(String[] args) {
        System.out.println("=== 集合選擇報告與實作示範 ===\n");

        System.out.println("【需求 1】保留搜尋紀錄且允許重複");
        System.out.println("  Interface     : List<String>");
        System.out.println("  Implementation: ArrayList<String>");
        List<String> searchHistory = new ArrayList<>();
        searchHistory.add("Java 教學");
        searchHistory.add("Data Structure");
        searchHistory.add("Java 教學"); 
        System.out.println("  操作結果: " + searchHistory + "\n");

        System.out.println("【需求 2】保存不重複會員編號");
        System.out.println("  Interface     : Set<String>");
        System.out.println("  Implementation: HashSet<String>");
        Set<String> memberIds = new HashSet<>();
        memberIds.add("M001");
        memberIds.add("M002");
        memberIds.add("M001");
        System.out.println("  操作結果: " + memberIds + "\n");

        System.out.println("【需求 3】以學號查詢成績");
        System.out.println("  Interface     : Map<String, Integer>");
        System.out.println("  Implementation: HashMap<String, Integer>");
        Map<String, Integer> studentGrades = new HashMap<>();
        studentGrades.put("S101", 95);
        studentGrades.put("S102", 88);
        System.out.println("  操作結果 (查詢 S101 成績): " + studentGrades.get("S101") + " 分\n");

        System.out.println("【需求 4】依到達順序處理列印工作");
        System.out.println("  Interface     : Queue<String>");
        System.out.println("  Implementation: ArrayDeque<String>");
        Queue<String> printQueue = new ArrayDeque<>();
        printQueue.offer("Doc1.pdf");
        printQueue.offer("Doc2.pdf");
        System.out.println("  操作結果 (取出首位列印): " + printQueue.poll() + "\n");

        System.out.println("【需求 5】復原最近操作");
        System.out.println("  Interface     : Deque<String>");
        System.out.println("  Implementation: ArrayDeque<String>");
        Deque<String> actionStack = new ArrayDeque<>();
        actionStack.push("Action_1");
        actionStack.push("Action_2");
        System.out.println("  操作結果 (Pop 復原最近操作): " + actionStack.pop());
    }
}
