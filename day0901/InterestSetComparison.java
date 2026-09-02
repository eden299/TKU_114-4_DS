package day0901;

import java.util.HashSet;
import java.util.Set;

public class InterestSetComparison {

    public static <T> Set<T> getUnion(Set<T> first, Set<T> second) {
        Set<T> result = new HashSet<>(first);
        result.addAll(second);
        return result;
    }

    public static <T> Set<T> getIntersection(Set<T> first, Set<T> second) {
        Set<T> result = new HashSet<>(first);
        result.retainAll(second);
        return result;
    }

    public static <T> Set<T> getFirstOnly(Set<T> first, Set<T> second) {
        Set<T> result = new HashSet<>(first);
        result.removeAll(second);
        return result;
    }

    public static <T> Set<T> getSecondOnly(Set<T> first, Set<T> second) {
        Set<T> result = new HashSet<>(second);
        result.removeAll(first);
        return result;
    }
}