package day0821;

import java.util.ArrayDeque;
import java.util.Deque;

class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "顧客: " + name;
    }
}

public class CounterWaitingQueue {
    private Deque<Customer> queue = new ArrayDeque<>();

    public void addCustomer(Customer customer) {
        queue.offerLast(customer);
        System.out.println("加入隊列: " + customer.getName());
    }

    public Customer peekNext() {
        if (queue.isEmpty()) {
            System.out.println("隊列為空，無下一位顧客");
            return null;
        }
        return queue.peekFirst();
    }

    public Customer serveNext() {
        if (queue.isEmpty()) {
            System.out.println("隊列為空，無可服務對象");
            return null;
        }
        Customer c = queue.pollFirst();
        System.out.println("正在服務: " + c.getName());
        return c;
    }

    public void displayStatus() {
        System.out.println("等待人數: " + queue.size() + " | 是否為空: " + queue.isEmpty());
    }

    public static void main(String[] args) {
        CounterWaitingQueue counter = new CounterWaitingQueue();

        counter.displayStatus();
        counter.addCustomer(new Customer("Alice"));
        counter.addCustomer(new Customer("Bob"));

        System.out.println("下一位是: " + counter.peekNext());
        counter.displayStatus();

        counter.serveNext();
        counter.serveNext();
        counter.serveNext(); 
        counter.displayStatus();
    }
}
