package ru.academits.kalichkin.minesweeper.common;

import java.util.Comparator;

public class SortPerson implements Comparator<PersonWin> {

    public int compare(PersonWin o1, PersonWin o2) {
        return o1.getTime().compareTo(o2.getTime());
    }
}