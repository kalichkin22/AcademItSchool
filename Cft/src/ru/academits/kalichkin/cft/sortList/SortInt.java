package ru.academits.kalichkin.cft.sortList;

import java.util.Comparator;

class SortInt implements Comparator<Integer> {

    public int compare(Integer a, Integer b) {
        return a.compareTo(b);
    }
}
