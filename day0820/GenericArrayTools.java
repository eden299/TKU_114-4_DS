package day0820;

public class GenericArrayTools {

    public static <T> int countMatches(T[] data, T target) {
        if (data == null || data.length == 0) return 0;
        int count = 0;
        for (T item : data) {
            if (target == null) {
                if (item == null) count++;
            } else {
                if (target.equals(item)) count++;
            }
        }
        return count;
    }

    public static <T> T last(T[] data) {
        if (data == null || data.length == 0) return null;
        return data[data.length - 1];
    }

    public static <T> void swap(T[] data, int first, int second) {
        if (data == null || data.length == 0) return;
        if (first < 0 || first >= data.length || second < 0 || second >= data.length) {
            System.out.println("不合法的 Index: " + first + ", " + second);
            return;
        }
        T temp = data[first];
        data[first] = data[second];
        data[second] = temp;
    }

    public static void main(String[] args) {
        String[] arr = {"Java", "Python", "C++", "Java", null};

        System.out.println("Matches 'Java': " + countMatches(arr, "Java"));
        System.out.println("Matches null: " + countMatches(arr, null));
        System.out.println("Last item: " + last(arr));

        swap(arr, 0, 2);
        System.out.println("New first item after swap: " + arr[0]);
    }
}
