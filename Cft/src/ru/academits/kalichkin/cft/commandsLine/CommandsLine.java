package ru.academits.kalichkin.cft.commandsLine;

import ru.academits.kalichkin.cft.fileReader.FileReader;
import ru.academits.kalichkin.cft.programCommands.ProgramCommands;
import ru.academits.kalichkin.cft.sortList.SortList;
import java.util.ArrayList;

public class CommandsLine extends ProgramCommands{

    public CommandsLine(String... args) {
        if (args.length != 4) {
            System.out.println("Необходимо ввести четыре команды");
            return;
        }
        switch (args[2]) {
            case "-i": {
                ArrayList<Integer> list = FileReader.readInt(args[0]);
                SortList.insertionSort(list, Integer::compareTo, getDirection(args[3]));
                FileReader.writeFile(list, args[1]);
                break;
            }
            case "-s": {
                ArrayList<String> list = FileReader.readString(args[0]);
                SortList.insertionSort(list, String::compareTo, getDirection(args[3]));
                FileReader.writeFile(list, args[1]);
                break;
            }

            default:
                throw new IllegalArgumentException("Такой команды нет");
        }
    }
}

