package day0821;

class Task {
    private String id;
    private String title;

    public Task(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "[" + id + ": " + title + "]";
    }
}

class TaskNode {
    Task task;
    TaskNode next;

    public TaskNode(Task task) {
        this.task = task;
        this.next = null;
    }
}

class TaskLinkedList {
    private TaskNode head;
    private int size;

    public TaskLinkedList() {
        this.head = null;
        this.size = 0;
    }

    private boolean containsId(String id) {
        TaskNode curr = head;
        while (curr != null) {
            if (curr.task.getId().equals(id)) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    public boolean addFirst(Task task) {
        if (containsId(task.getId())) {
            System.out.println("【新增失敗】重複 ID: " + task.getId());
            return false;
        }
        TaskNode newNode = new TaskNode(task);
        newNode.next = head;
        head = newNode;
        size++;
        return true;
    }

    public boolean addLast(Task task) {
        if (containsId(task.getId())) {
            System.out.println("【新增失敗】重複 ID: " + task.getId());
            return false;
        }
        TaskNode newNode = new TaskNode(task);
        if (head == null) {
            head = newNode;
        } else {
            TaskNode curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = newNode;
        }
        size++;
        return true;
    }

    public Task findById(String id) {
        TaskNode curr = head;
        while (curr != null) {
            if (curr.task.getId().equals(id)) {
                return curr.task;
            }
            curr = curr.next;
        }
        return null;
    }

    public boolean insertAfter(String existingId, Task task) {
        if (containsId(task.getId())) {
            System.out.println("【插入失敗】新任務 ID 重複: " + task.getId());
            return false;
        }
        TaskNode curr = head;
        while (curr != null) {
            if (curr.task.getId().equals(existingId)) {
                TaskNode newNode = new TaskNode(task);
                newNode.next = curr.next;
                curr.next = newNode;
                size++;
                return true;
            }
            curr = curr.next;
        }
        System.out.println("【插入失敗】找不到基礎任務 ID: " + existingId);
        return false;
    }

    public boolean removeById(String id) {
        if (head == null) {
            System.out.println("【刪除失敗】清單為空，無法刪除 ID: " + id);
            return false;
        }

        if (head.task.getId().equals(id)) {
            head = head.next;
            size--;
            System.out.println("成功刪除 Head 節點: " + id);
            return true;
        }

        TaskNode curr = head;
        while (curr.next != null) {
            if (curr.next.task.getId().equals(id)) {
                curr.next = curr.next.next;
                size--;
                System.out.println("成功刪除節點: " + id);
                return true;
            }
            curr = curr.next;
        }

        System.out.println("【刪除失敗】找不到 ID: " + id);
        return false;
    }

    public int size() {
        return size;
    }

    public void printAll() {
        System.out.print("Current List (size=" + size + "): ");
        if (head == null) {
            System.out.println("Empty");
            return;
        }
        TaskNode curr = head;
        while (curr != null) {
            System.out.print(curr.task + (curr.next != null ? " -> " : ""));
            curr = curr.next;
        }
        System.out.println();
    }
}

public class LinkedTaskListSystem {
    public static void main(String[] args) {
        TaskLinkedList list = new TaskLinkedList();

        System.out.println("--- 測試空 List 刪除與搜尋 ---");
        list.printAll();
        list.removeById("T1");
        System.out.println("搜尋 T1: " + list.findById("T1"));

        System.out.println("\n--- 新增任務與測試重複 ID ---");
        list.addLast(new Task("T1", "需求分析"));
        list.addLast(new Task("T2", "架構設計"));
        list.addFirst(new Task("T0", "專案立項"));
        list.addLast(new Task("T3", "程式編寫"));
        list.addLast(new Task("T1", "重複 ID 測試")); 
        list.printAll();

        System.out.println("\n--- 測試 insertAfter ---");
        list.insertAfter("T2", new Task("T2.5", "設計審查"));
        list.printAll();

        System.out.println("\n--- 測試刪除 Middle (T2.5) ---");
        list.removeById("T2.5");
        list.printAll();

        System.out.println("\n--- 測試刪除 Head (T0) ---");
        list.removeById("T0");
        list.printAll();

        System.out.println("\n--- 測試刪除 Tail (T3) ---");
        list.removeById("T3");
        list.printAll();

        System.out.println("\n--- 測試刪除不存在 ID (T99) ---");
        list.removeById("T99");
    }
}
