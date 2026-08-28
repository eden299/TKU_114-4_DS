package day0825;

public class StudentBstIndex {
    static class Student {
        int studentId;
        String name;

        Student(int studentId, String name) {
            this.studentId = studentId;
            this.name = name;
        }

        @Override
        public String toString() {
            return "ID: " + studentId + ", Name: " + name;
        }
    }

    static class Node {
        Student data;
        Node left, right;

        Node(Student data) {
            this.data = data;
        }
    }

    private Node root;

    public boolean insert(Student student) {
        if (search(student.studentId) != null) {
            System.out.println("Insert failed: Duplicate studentId " + student.studentId);
            return false;
        }
        root = insertRec(root, student);
        return true;
    }

    private Node insertRec(Node node, Student student) {
        if (node == null) return new Node(student);
        if (student.studentId < node.data.studentId) {
            node.left = insertRec(node.left, student);
        } else if (student.studentId > node.data.studentId) {
            node.right = insertRec(node.right, student);
        }
        return node;
    }

    public Student search(int studentId) {
        Node curr = root;
        while (curr != null) {
            if (curr.data.studentId == studentId) return curr.data;
            if (studentId < curr.data.studentId) curr = curr.left;
            else curr = curr.right;
        }
        return null;
    }

    public void delete(int studentId) {
        root = deleteRec(root, studentId);
    }

    private Node deleteRec(Node node, int studentId) {
        if (node == null) return null;
        if (studentId < node.data.studentId) {
            node.left = deleteRec(node.left, studentId);
        } else if (studentId > node.data.studentId) {
            node.right = deleteRec(node.right, studentId);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node minNode = getMin(node.right);
            node.data = minNode.data;
            node.right = deleteRec(node.right, minNode.data.studentId);
        }
        return node;
    }

    private Node getMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    public static void main(String[] args) {
        StudentBstIndex index = new StudentBstIndex();
        index.insert(new Student(102, "Alice"));
        index.insert(new Student(101, "Bob"));
        index.insert(new Student(105, "Charlie"));

        index.insert(new Student(101, "Duplicate Bob"));

        System.out.println("Search 101: " + index.search(101));
        index.delete(101);
        System.out.println("After Delete 101, Search 101: " + index.search(101));
    }
}
