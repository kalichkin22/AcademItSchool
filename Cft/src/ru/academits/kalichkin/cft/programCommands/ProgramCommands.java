package ru.academits.kalichkin.cft.programCommands;


import ru.academits.kalichkin.cft.fileReader.FileReader;
import ru.academits.kalichkin.cft.sortList.SortList;

import java.util.AbstractList;
import java.util.ArrayList;

public class ProgramCommands {
    private ArrayList list;
    private FileReader reader;
    private SortList sort;
    private String[] args;


    public ProgramCommands (String a, String b) {
        if (a.equals("-i")) {
            list = FileReader.readInt(args[0]);
        } else if (a.equals("-s")) {
            list = FileReader.readString(args[0]);
        }
    }
}
