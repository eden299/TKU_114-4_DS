package day0831;

import java.util.PriorityQueue;

public class SupportTicketQueue {
    public static class Ticket implements Comparable<Ticket> {
        String id;
        int severity;
        int createdOrder;

        public Ticket(String id, int severity, int createdOrder) {
            this.id = id;
            this.severity = severity;
            this.createdOrder = createdOrder;
        }

        @Override
        public int compareTo(Ticket other) {
            if (this.severity != other.severity) {
                return Integer.compare(other.severity, this.severity);
            }
            return Integer.compare(this.createdOrder, other.createdOrder);
        }
    }

    public static void main(String[] args) {
        PriorityQueue<Ticket> pq = new PriorityQueue<>();
        
        pq.add(new Ticket("T1", 2, 1));
        pq.add(new Ticket("T2", 5, 2));
        pq.add(new Ticket("T3", 5, 3));
        pq.add(new Ticket("T4", 1, 4));

        while (!pq.isEmpty()) {
            Ticket t = pq.poll();
            System.out.println(t.id + "|" + t.severity + "|" + t.createdOrder);
        }
    }
}
