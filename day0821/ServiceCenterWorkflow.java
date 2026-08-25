package day0821;

import java.util.*;

class ServiceTicket {
    private String ticketId;
    private String customerName;

    public ServiceTicket(String ticketId, String customerName) {
        this.ticketId = ticketId;
        this.customerName = customerName;
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getCustomerName() {
        return customerName;
    }

    @Override
    public String toString() {
        return "票號[" + ticketId + ": " + customerName + "]";
    }
}

public class ServiceCenterWorkflow {
    private Map<String, ServiceTicket> ticketMap = new HashMap<>();
    private Deque<ServiceTicket> waitingQueue = new ArrayDeque<>();
    private Deque<ServiceTicket> completedStack = new ArrayDeque<>();
    private Set<String> idSet = new HashSet<>();

    public boolean createTicket(String ticketId, String customerName) {
        if (idSet.contains(ticketId)) {
            System.out.println("【建立失敗】Ticket ID 重複: " + ticketId);
            return false;
        }
        ServiceTicket ticket = new ServiceTicket(ticketId, customerName);
        idSet.add(ticketId);
        ticketMap.put(ticketId, ticket);
        waitingQueue.offerLast(ticket);
        System.out.println("成功建立票號: " + ticket);
        return true;
    }

    public ServiceTicket processNext() {
        if (waitingQueue.isEmpty()) {
            System.out.println("【處理失敗】等待隊列為空，無等待顧客");
            return null;
        }
        ServiceTicket ticket = waitingQueue.pollFirst();
        completedStack.push(ticket);
        System.out.println("開始服務顧客: " + ticket);
        return ticket;
    }

    public boolean cancelWaiting(String ticketId) {
        ServiceTicket ticket = ticketMap.get(ticketId);
        if (ticket == null) {
            System.out.println("【取消失敗】找不到票號: " + ticketId);
            return false;
        }
        if (!waitingQueue.contains(ticket)) {
            System.out.println("【取消失敗】票號 " + ticketId + " 不在等待隊列中（可能已完成服務）");
            return false;
        }
        waitingQueue.remove(ticket);
        ticketMap.remove(ticketId);
        idSet.remove(ticketId);
        System.out.println("成功取消等待中的票號: " + ticket);
        return true;
    }

    public boolean undoLastCompletion() {
        if (completedStack.isEmpty()) {
            System.out.println("【Undo 失敗】完成歷程為空，無操作可復原");
            return false;
        }
        ServiceTicket ticket = completedStack.pop();
        waitingQueue.offerFirst(ticket);
        System.out.println("Undo 成功: 將 " + ticket + " 重新移回等待隊列前端");
        return true;
    }

    public ServiceTicket findById(String ticketId) {
        return ticketMap.get(ticketId);
    }

    public void printSummary() {
        System.out.println("\n=== 服務中心數據摘要 ===");
        System.out.println("總登記數量: " + idSet.size());
        System.out.println("等待隊列: " + waitingQueue);
        System.out.println("完成歷程 Stack: " + completedStack);
        System.out.println("-------------------------\n");
    }

    public static void main(String[] args) {
        ServiceCenterWorkflow center = new ServiceCenterWorkflow();

        System.out.println("--- 1. 測試空 Queue 處理與 Undo ---");
        center.processNext();
        center.undoLastCompletion();

        System.out.println("\n--- 2. 測試建立號碼與防呆重複 ID ---");
        center.createTicket("A001", "Alice");
        center.createTicket("A002", "Bob");
        center.createTicket("A003", "Charlie");
        center.createTicket("A001", "重複測試"); 

        center.printSummary();

        System.out.println("--- 3. 測試取消不存在 ID 與取消等待中 ID ---");
        center.cancelWaiting("A999"); 
        center.cancelWaiting("A002"); 

        center.printSummary();

        System.out.println("--- 4. 測試處理服務與連續兩次 Undo ---");
        center.processNext(); 
        center.processNext(); 

        center.printSummary();

        System.out.println("第一次 Undo:");
        center.undoLastCompletion(); 

        System.out.println("第二次 Undo:");
        center.undoLastCompletion(); 

        center.printSummary();

        System.out.println("--- 5. 測試取消已完成的 ID ---");
        center.processNext(); 
        center.cancelWaiting("A001"); 
    }
}
