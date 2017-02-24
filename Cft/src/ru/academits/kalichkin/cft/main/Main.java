package ru.academits.kalichkin.cft.main;

import ru.academits.kalichkin.cft.fileReader.FileReader;
import ru.academits.kalichkin.cft.sortList.SortList;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Main {

    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println("Необходимо ввести четыре команды");
            return;
        }

        switch (args[2]) {
            case "-i": {
                ArrayList<Integer> list = FileReader.readInt(args[0]);
                SortList.insertionSort(list, Integer::compareTo, args[3]);
                FileReader.writeFile(list, args[1]);
                break;
            }
            case "-s": {
                ArrayList<String> list = FileReader.readString(args[0]);
                SortList.insertionSort(list, String::compareTo, args[3]);
                FileReader.writeFile(list, args[1]);
                break;
            }

            default:
                throw new NoSuchElementException("Такой команды нет");
        }
    }
}

