package ru.academits.kalichkin.cft.main;

import ru.academits.kalichkin.cft.Type;
import ru.academits.kalichkin.cft.fileReader.FileReader;
import ru.academits.kalichkin.cft.parser.Parser;
import ru.academits.kalichkin.cft.programArgs.ProgramArgs;
import ru.academits.kalichkin.cft.sortList.SortList;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Main {

    public static void main(String[] args) {
        Parser a = new Parser();
        ProgramArgs programArgs = a.parse(args);

        try {
            if (programArgs.getType() == Type.INTEGER) {
                ArrayList<Integer> list = FileReader.readInt(programArgs.getFileName());
                SortList.insertionSort(list, Integer::compareTo, programArgs.isDirection());
                FileReader.writeFile(list, programArgs.getFileOut());
            } else if (programArgs.getType() == Type.STRING) {
                ArrayList<String> list = FileReader.readString(programArgs.getFileName());
                SortList.insertionSort(list, String::compareTo, programArgs.isDirection());
                FileReader.writeFile(list, programArgs.getFileOut());
            }

        } catch (Exception e) {
            System.out.println();
        }
    }
}

