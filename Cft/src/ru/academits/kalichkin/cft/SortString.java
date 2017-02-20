package ru.academits.kalichkin.cft;
import java.util.Comparator;


class SortString <T> implements Comparator<String> {

    @Override
    public int compare(String obj1, String obj2) {
        return obj1.compareTo(obj2);
    }
}