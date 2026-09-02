package day0831;

import java.util.PriorityQueue;

public class EmergencyTriageQueue {
    public static class Patient implements Comparable<Patient> {
        String medicalRecordNumber;
        int urgency;
        int arrivalOrder;

        public Patient(String medicalRecordNumber, int urgency, int arrivalOrder) {
            this.medicalRecordNumber = medicalRecordNumber;
            this.urgency = urgency;
            this.arrivalOrder = arrivalOrder;
        }

        @Override
        public int compareTo(Patient other) {
            if (this.urgency != other.urgency) {
                return Integer.compare(other.urgency, this.urgency);
            }
            return Integer.compare(this.arrivalOrder, other.arrivalOrder);
        }

        @Override
        public String toString() {
            return medicalRecordNumber + " (Urgency: " + urgency + ", Order: " + arrivalOrder + ")";
        }
    }

    private PriorityQueue<Patient> queue = new PriorityQueue<>();
    private int orderCounter = 0;

    public void register(String medicalRecordNumber, int urgency) {
        Patient p = new Patient(medicalRecordNumber, urgency, ++orderCounter);
        queue.add(p);
        System.out.println("Registered: " + p);
    }

    public Patient peekNext() {
        return queue.peek();
    }

    public Patient callNext() {
        if (queue.isEmpty()) {
            System.out.println("Queue is empty. No patient to call.");
            return null;
        }
        Patient p = queue.poll();
        System.out.println("Called patient: " + p);
        return p;
    }

    public int size() {
        return queue.size();
    }

    public static void main(String[] args) {
        EmergencyTriageQueue triage = new EmergencyTriageQueue();
        triage.register("P001", 3);
        triage.register("P002", 5);
        triage.register("P003", 5);
        triage.register("P004", 1);

        System.out.println("Next patient: " + triage.peekNext());
        System.out.println("Current count: " + triage.size());

        triage.callNext();
        triage.callNext();
        triage.callNext();
        triage.callNext();
        triage.callNext();
    }
}
