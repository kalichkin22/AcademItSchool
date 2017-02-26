package ru.academits.kalichkin.cft.parser;

import ru.academits.kalichkin.cft.Type;
import ru.academits.kalichkin.cft.programArgs.ProgramArgs;

import java.util.NoSuchElementException;

public class Parser {

    public ProgramArgs parse(String[] args) {
        if (args.length != 4) {
            System.out.println("Необходимо ввести четыре команды");
        }

        ProgramArgs programArgs = new ProgramArgs();
        programArgs.setFileName(args[0]);
        programArgs.setFileOut(args[1]);
        programArgs.setType(parseType(args[2]));
        programArgs.setDirection(isDirection(args[3]));

        return programArgs;
    }

    private Type parseType(String type) {
        switch (type) {
            case "-i": {
                return Type.INTEGER;
            }
            case "-s": {
                return Type.STRING;
            }
            default:
                throw new NoSuchElementException("Такой команды нет");
        }
    }

    private static boolean isDirection(String str) {
        boolean direction;
        switch (str) {
            case "-a":
                direction = false;
                break;
            case "-d":
                direction = true;
                break;
            default:
                throw new NoSuchElementException("Такой команды нет");
        }
        return direction;
    }

}
