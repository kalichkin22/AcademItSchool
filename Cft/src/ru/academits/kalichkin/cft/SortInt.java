package ru.academits.kalichkin.cft;

import java.util.Comparator;

abstract class SortInt <T> implements Comparator<Integer> {

    public <T> int compare(Integer a, Integer b) {
        return a.compareTo(b);
    }
}
