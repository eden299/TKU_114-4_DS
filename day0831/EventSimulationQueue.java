package day0831;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class EventSimulationQueue {
    public static class Event implements Comparable<Event> {
        int id;
        long time;
        String type;
        int sequence;

        public Event(int id, long time, String type, int sequence) {
            this.id = id;
            this.time = time;
            this.type = type;
            this.sequence = sequence;
        }

        @Override
        public int compareTo(Event other) {
            if (this.time != other.time) {
                return Long.compare(this.time, other.time);
            }
            return Integer.compare(this.sequence, other.sequence);
        }

        @Override
        public String toString() {
            return "Event[ID=" + id + ", Time=" + time + ", Type=" + type + ", Seq=" + sequence + "]";
        }
    }

    private PriorityQueue<Event> queue = new PriorityQueue<>();

    public void addEvent(Event e) {
        queue.add(e);
    }

    public boolean cancelEvent(int eventId) {
        return queue.removeIf(e -> e.id == eventId);
    }

    public List<Event> runSimulation() {
        List<Event> logs = new ArrayList<>();
        while (!queue.isEmpty()) {
            Event e = queue.poll();
            logs.add(e);
            System.out.println("Executed: " + e);
        }
        return logs;
    }

    public static void main(String[] args) {
        EventSimulationQueue sim = new EventSimulationQueue();
        sim.addEvent(new Event(1, 100, "START", 1));
        sim.addEvent(new Event(2, 200, "PROCESS", 2));
        sim.addEvent(new Event(3, 100, "CHECK", 2));
        sim.addEvent(new Event(4, 150, "CANCEL_TARGET", 3));

        sim.cancelEvent(4);
        sim.runSimulation();
    }
}
