
package ru.academits.kalichkin.cft.main;

import ru.academits.kalichkin.cft.fileReader.FileReader;
import ru.academits.kalichkin.cft.programCommands.ProgramCommands;
import ru.academits.kalichkin.cft.sortList.SortList;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Integer> list = FileReader.readInt(args[0]);
        List<String> list1 = FileReader.readString(args[0]);
        SortList.insertionSort(list, Integer::compareTo, true);
        FileReader.writeFile(list, args[1]);
    }
}

