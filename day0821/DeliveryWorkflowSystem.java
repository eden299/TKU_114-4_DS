package day0821;

import java.util.*;

class DeliveryTask {
    private String id;
    private String item;

    public DeliveryTask(String id, String item) {
        this.id = id;
        this.item = item;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "包裹[" + id + ": " + item + "]";
    }
}

public class DeliveryWorkflowSystem {
    private Map<String, DeliveryTask> taskMap = new HashMap<>();
    private Deque<DeliveryTask> pendingQueue = new ArrayDeque<>();
    private Deque<DeliveryTask> completedStack = new ArrayDeque<>();

    public boolean addTask(String id, String item) {
        if (taskMap.containsKey(id)) {
            System.out.println("【新增失敗】包裹編號 " + id + " 已存在");
            return false;
        }
        DeliveryTask task = new DeliveryTask(id, item);
        taskMap.put(id, task);
        pendingQueue.offerLast(task);
        System.out.println("新增包裹成功: " + task);
        return true;
    }

    public DeliveryTask processNext() {
        if (pendingQueue.isEmpty()) {
            System.out.println("無等待配送之包裹");
            return null;
        }
        DeliveryTask task = pendingQueue.pollFirst();
        completedStack.push(task);
        System.out.println("配送完成: " + task);
        return task;
    }

    public boolean undo() {
        if (completedStack.isEmpty()) {
            System.out.println("【Undo 失敗】沒有已完成的配送紀錄可復原");
            return false;
        }
        DeliveryTask task = completedStack.pop();
        pendingQueue.offerFirst(task);
        System.out.println("Undo 成功，包裹重新放回待配送前端: " + task);
        return true;
    }

    public void queryTask(String id) {
        DeliveryTask task = taskMap.get(id);
        if (task != null) {
            System.out.println("查詢結果: " + task);
        } else {
            System.out.println("未找到編號 " + id + " 的包裹");
        }
    }

    public void printStatistics() {
        System.out.println("=== 統計資訊 ===");
        System.out.println("總包裹數: " + taskMap.size());
        System.out.println("待配送數: " + pendingQueue.size());
        System.out.println("已完成數: " + completedStack.size());
        System.out.println("----------------");
    }

    public static void main(String[] args) {
        DeliveryWorkflowSystem system = new DeliveryWorkflowSystem();

        system.addTask("D101", "筆記型電腦");
        system.addTask("D102", "智慧型手機");
        system.addTask("D101", "重複防呆測試"); 

        system.processNext();
        system.printStatistics();

        system.queryTask("D101");
        system.undo();
        system.printStatistics();
    }
}
