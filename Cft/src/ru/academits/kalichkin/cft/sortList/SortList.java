package ru.academits.kalichkin.cft.sortList;

import java.util.Comparator;
import java.util.List;
import ru.academits.kalichkin.cft.programCommands.ProgramCommands;

public class SortList {
    public static <T> void insertionSort(List<T> list, Comparator<T> tComparator, String str) {
        for (int i = 1; i < list.size(); i++) {

            T a = list.get(i);
            int b = i - 1;

            while (b >= 0 && (tComparator.compare(list.get(b), a) < 0) == ProgramCommands.isAscending(str)) {
                list.set(list.indexOf(list.get(b + 1)), list.get(b));
                list.set(list.indexOf(list.get(b)), list.get(b + 1));
                b--;
            }
            list.set(list.indexOf(list.get(b + 1)), a);
            list.set(list.indexOf(a), list.get(b + 1));
        }
    }
}
