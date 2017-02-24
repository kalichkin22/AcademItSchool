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

        boolean direction;
        switch (args[3]) {
            case "-a":
                direction = false;
                break;
            case "-d":
                direction = true;
                break;
            default:
                throw new NoSuchElementException ("Такой команды нет");
        }

        switch (args[2]) {
            case "-i": {
                ArrayList<Integer> list = FileReader.readInt(args[0]);
                SortList.insertionSort(list, Integer::compareTo, direction);
                FileReader.writeFile(list, args[1]);
                break;
            }
            case "-s": {
                ArrayList<String> list = FileReader.readString(args[0]);
                SortList.insertionSort(list, String::compareTo, direction);
                FileReader.writeFile(list, args[1]);
                break;
            }

            default:
                throw new NoSuchElementException("Такой команды нет");
        }
    }
}

