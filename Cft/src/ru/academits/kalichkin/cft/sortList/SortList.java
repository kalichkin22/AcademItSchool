package ru.academits.kalichkin.cft.sortList;


import java.util.Comparator;
import java.util.List;


public class SortList {
    public static <T> void insertionSort(List<T> list, Comparator<T> tComparator, boolean direction) {

        for (int i = 1; i < list.size(); i++) {

            T a = list.get(i);
            int b = i - 1;

            while (b >= 0 && (tComparator.compare(list.get(b), a) < 0) == direction) {
                list.set(list.indexOf(list.get(b + 1)), list.get(b));
                list.set(list.indexOf(list.get(b)), list.get(b + 1));
                b--;
            }
            list.set(list.indexOf(list.get(b + 1)), a);
            list.set(list.indexOf(a), list.get(b + 1));
        }
    }
}
