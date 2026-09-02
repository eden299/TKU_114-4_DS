package day0902;

import java.util.*;

public class ServiceRequestSystem {
    public static class Request implements Comparable<Request> {
        private String id;
        private int priority;

        public Request(String id, int priority) {
            this.id = id;
            this.priority = priority;
        }

        public String getId() { return id; }
        public int getPriority() { return priority; }

        @Override
        public int compareTo(Request o) {
            return Integer.compare(o.priority, this.priority);
        }
    }

    private Map<String, Request> requestMap = new HashMap<>();
    private PriorityQueue<Request> pq = new PriorityQueue<>();

    public void addRequest(String id, int priority) {
        Request req = new Request(id, priority);
        requestMap.put(id, req);
        pq.offer(req);
    }

    public Request getRequestById(String id) {
        return requestMap.get(id);
    }

    public Request processNextRequest() {
        Request req = pq.poll();
        if (req != null) {
            requestMap.remove(req.getId());
        }
        return req;
    }

    public boolean cancelRequest(String id) {
        Request req = requestMap.remove(id);
        if (req != null) {
            pq.remove(req);
            return true;
        }
        return false;
    }
}
