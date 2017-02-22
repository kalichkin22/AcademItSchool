package ru.academits.kalichkin.cft.main;


import ru.academits.kalichkin.cft.fileReader.FileReader;
import ru.academits.kalichkin.cft.sortList.SortList;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<String> list = FileReader.read(args[0]);
        SortList.insertionSort(list, String::compareTo);
        FileReader.writeFile(list, args[1]);
    }


}

