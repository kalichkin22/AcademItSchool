package ru.academits.kalichkin.cft.sortList;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class SortList {
    public static <T> void insertionSort(List<T> list, Comparator<T> tComparator, String str) {
        for (int i = 1; i < list.size(); i++) {

            T obj = list.get(i);
            int j = i - 1;

            while (j >= 0 && (tComparator.compare(list.get(j), obj) < 0) == isDirection(str)) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, obj);
        }
    }

    private static boolean isDirection(String str) {
        boolean direction;
        switch (str) {
            case "-a":
                direction = false;
                break;
            case "-d":
                direction = true;
                break;
            default:
                throw new NoSuchElementException("Такой команды нет");
        }
        return direction;
    }
}
