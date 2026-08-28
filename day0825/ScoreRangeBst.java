package day0825;

public class ScoreRangeBst {
    static class ScoreRecord implements Comparable<ScoreRecord> {
        int score;
        int studentId;

        ScoreRecord(int score, int studentId) {
            this.score = score;
            this.studentId = studentId;
        }

        @Override
        public int compareTo(ScoreRecord o) {
            if (this.score != o.score) {
                return Integer.compare(this.score, o.score);
            }
            return Integer.compare(this.studentId, o.studentId);
        }

        @Override
        public String toString() {
            return String.format("(Score: %d, ID: %d)", score, studentId);
        }
    }

    static class Node {
        ScoreRecord record;
        Node left, right;
        Node(ScoreRecord record) { this.record = record; }
    }

    private Node root;

    public void insert(int score, int studentId) {
        root = insertRec(root, new ScoreRecord(score, studentId));
    }

    private Node insertRec(Node node, ScoreRecord rec) {
        if (node == null) return new Node(rec);
        int cmp = rec.compareTo(node.record);
        if (cmp < 0) node.left = insertRec(node.left, rec);
        else if (cmp > 0) node.right = insertRec(node.right, rec);
        return node;
    }

    public void printScoreRange(int minScore, int maxScore) {
        System.out.printf("Students with scores in range [%d, %d]:\n", minScore, maxScore);
        rangeRec(root, minScore, maxScore);
        System.out.println();
    }

    private void rangeRec(Node node, int min, int max) {
        if (node == null) return;
        if (node.record.score > min) {
            rangeRec(node.left, min, max);
        }
        if (node.record.score >= min && node.record.score <= max) {
            System.out.print(node.record + " ");
        }
        if (node.record.score < max) {
            rangeRec(node.right, min, max);
        }
    }

    public static void main(String[] args) {
        ScoreRangeBst bst = new ScoreRangeBst();
        bst.insert(85, 1001);
        bst.insert(70, 1002);
        bst.insert(85, 1003);
        bst.insert(92, 1004);
        bst.insert(60, 1005);

        bst.printScoreRange(70, 90);
    }
}
