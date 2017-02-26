package ru.academits.kalichkin.cft.sortList;
import java.util.Comparator;

class SortString implements Comparator<String> {

    public int compare(String a, String b) {
        return a.compareTo(b);
    }
}