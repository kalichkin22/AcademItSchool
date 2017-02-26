package ru.academits.kalichkin.cft.sortList;

import java.util.Comparator;
import java.util.List;


public class SortList {
    public static <T> void insertionSort(List<T> list, Comparator<T> tComparator, boolean direction) {
        for (int i = 1; i < list.size(); i++) {

            T obj = list.get(i);
            int j = i - 1;

            while (j >= 0 && (tComparator.compare(list.get(j), obj) < 0) == direction) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, obj);
        }
    }
}
