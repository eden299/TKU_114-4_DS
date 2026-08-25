package day0821;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class Patient {
    private String id;
    private String name;

    public Patient(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + name;
    }
}

public class ClinicQueueSystem {
    private Deque<Patient> waitingQueue = new ArrayDeque<>();
    private List<Patient> completedList = new ArrayList<>();

    public void register(Patient patient) {
        waitingQueue.offerLast(patient);
        System.out.println("掛號成功: " + patient);
    }

    public boolean cancel(String patientId) {
        Patient target = null;
        for (Patient p : waitingQueue) {
            if (p.getId().equals(patientId)) {
                target = p;
                break;
            }
        }
        if (target != null) {
            waitingQueue.remove(target);
            System.out.println("取消掛號成功: " + target);
            return true;
        }
        System.out.println("取消失敗: 找不到病歷號 " + patientId);
        return false;
    }

    public Patient callNext() {
        if (waitingQueue.isEmpty()) {
            System.out.println("目前無等待看診之患者");
            return null;
        }
        Patient p = waitingQueue.pollFirst();
        completedList.add(p);
        System.out.println("請看診: " + p);
        return p;
    }

    public Patient peekNext() {
        if (waitingQueue.isEmpty()) {
            System.out.println("隊列為空，無下一位");
            return null;
        }
        return waitingQueue.peekFirst();
    }

    public void printCompletedList() {
        System.out.println("當日已完成看診名單: " + completedList);
    }

    public static void main(String[] args) {
        ClinicQueueSystem system = new ClinicQueueSystem();

        system.register(new Patient("P001", "張三"));
        system.register(new Patient("P002", "李四"));
        system.register(new Patient("P003", "王五"));

        System.out.println("下一位等待者: " + system.peekNext());

        system.cancel("P002"); 

        system.callNext();
        system.callNext();
        system.callNext(); 

        system.printCompletedList();
    }
}
