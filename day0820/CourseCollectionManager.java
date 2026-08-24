package day0820;

import java.util.*;

class StudentRecord {
    private String studentId;
    private String name;
    private int score;
    private List<String> tags;

    public StudentRecord(String studentId, String name, int score, List<String> tags) {
        this.studentId = studentId;
        this.name = name;
        this.score = score;
        this.tags = tags != null ? new ArrayList<>(tags) : new ArrayList<>();
    }

    public String getStudentId() { return studentId; }
    public String getName() { return name; }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
    public List<String> getTags() { return tags; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentRecord that = (StudentRecord) o;
        return Objects.equals(studentId, that.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }

    @Override
    public String toString() {
        return String.format("StudentRecord{id='%s', name='%s', score=%d, tags=%s}", studentId, name, score, tags);
    }
}

public class CourseCollectionManager {

    private List<StudentRecord> recordList = new ArrayList<>();
    private Set<StudentRecord> recordSet = new HashSet<>();
    private Map<String, StudentRecord> recordMap = new LinkedHashMap<>();

    public void addOrUpdate(StudentRecord record) {
        if (record == null || record.getStudentId() == null) return;

        String id = record.getStudentId();
        if (recordMap.containsKey(id)) {
            StudentRecord existing = recordMap.get(id);
            recordList.remove(existing);
            recordSet.remove(existing);
        }

        recordList.add(record);
        recordSet.add(record);
        recordMap.put(id, record);
    }

    public boolean updateScore(String studentId, int score) {
        if (!recordMap.containsKey(studentId)) {
            return false;
        }
        StudentRecord record = recordMap.get(studentId);
        record.setScore(score);
        return true;
    }

    public List<StudentRecord> findByTag(String tag) {
        List<StudentRecord> result = new ArrayList<>();
        if (tag == null || tag.trim().isEmpty()) {
            return result;
        }
        String searchTag = tag.trim();
        for (StudentRecord record : recordList) {
            for (String t : record.getTags()) {
                if (t != null && t.trim().equalsIgnoreCase(searchTag)) {
                    result.add(record);
                    break;
                }
            }
        }
        return result;
    }

    public Map<String, Integer> scoreDistribution() {
        Map<String, Integer> dist = new LinkedHashMap<>();
        dist.put("A", 0); // 90~100
        dist.put("B", 0); // 80~89
        dist.put("C", 0); // 70~79
        dist.put("D", 0); // 60~69
        dist.put("F", 0); // < 60

        for (StudentRecord record : recordList) {
            int score = record.getScore();
            if (score >= 90) dist.put("A", dist.get("A") + 1);
            else if (score >= 80) dist.put("B", dist.get("B") + 1);
            else if (score >= 70) dist.put("C", dist.get("C") + 1);
            else if (score >= 60) dist.put("D", dist.get("D") + 1);
            else dist.put("F", dist.get("F") + 1);
        }
        return dist;
    }

    public List<StudentRecord> top(int count) {
        if (count <= 0) return new ArrayList<>();

        List<StudentRecord> sortedList = new ArrayList<>(recordList);
        sortedList.sort((r1, r2) -> {
            if (r2.getScore() != r1.getScore()) {
                return Integer.compare(r2.getScore(), r1.getScore());
            }
            return r1.getStudentId().compareTo(r2.getStudentId());
        });

        int limit = Math.min(count, sortedList.size());
        return new ArrayList<>(sortedList.subList(0, limit));
    }

    public void removeBelow(int minimum) {
        Iterator<StudentRecord> iterator = recordList.iterator();
        while (iterator.hasNext()) {
            StudentRecord record = iterator.next();
            if (record.getScore() < minimum) {
                iterator.remove();                 
                recordSet.remove(record);          
                recordMap.remove(record.getStudentId()); 
            }
        }
    }

    public void printAll() {
        System.out.println("List 大小: " + recordList.size() +
                           " | Set 大小: " + recordSet.size() +
                           " | Map 大小: " + recordMap.size());
        recordList.forEach(System.out::println);
    }

    public static void main(String[] args) {
        CourseCollectionManager manager = new CourseCollectionManager();

        manager.addOrUpdate(new StudentRecord("S001", "Alice", 85, Arrays.asList("Java", "Backend", "  ")));
        manager.addOrUpdate(new StudentRecord("S002", "Bob", 92, Arrays.asList("Java", "Frontend")));
        manager.addOrUpdate(new StudentRecord("S003", "Charlie", 58, Arrays.asList("Database", "")));
        manager.addOrUpdate(new StudentRecord("S004", "David", 85, Arrays.asList("Backend", null))); 
        manager.addOrUpdate(new StudentRecord("S005", "Eve", 74, Arrays.asList("Java", "AI")));
        manager.addOrUpdate(new StudentRecord("S001", "Alice", 95, Arrays.asList("Java", "Advanced")));
        manager.addOrUpdate(new StudentRecord("S006", "Frank", 45, Arrays.asList("  ", "Frontend")));

        System.out.println("=== 初始與更新後的學生資料 ===");
        manager.printAll();

        System.out.println("\n=== 更新 S003 成績至 65 分 ===");
        manager.updateScore("S003", 65);

        System.out.println("\n=== 搜尋 Tag: 'Java' ===");
        manager.findByTag("Java").forEach(System.out::println);

        System.out.println("\n=== 成績等級分佈 ===");
        System.out.println(manager.scoreDistribution());

        System.out.println("\n=== 前 3 名學生 ===");
        manager.top(3).forEach(System.out::println);

        System.out.println("\n=== 移除低於 60 分的學生 ===");
        manager.removeBelow(60);
        manager.printAll();
    }
}
