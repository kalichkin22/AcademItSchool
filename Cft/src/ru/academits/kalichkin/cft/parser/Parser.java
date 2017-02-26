package ru.academits.kalichkin.cft.parser;

import ru.academits.kalichkin.cft.type.Type;

public class Parser {

    public ProgramArgs parse(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException ("Необходимо ввести четыре аргумента");
        }

        ProgramArgs programArgs = new ProgramArgs();
        programArgs.setFileName(args[0]);
        programArgs.setFileOut(args[1]);
        programArgs.setType(parseType(args[2]));
        programArgs.setDirection(isIncrease(args[3]));

        return programArgs;
    }

    private static Type parseType(String type) {
        switch (type) {
            case "-i": {
                return Type.INTEGER;
            }
            case "-s": {
                return Type.STRING;
            }
            default:
                throw new IllegalArgumentException ("Такой команды нет");
        }
    }

    private static boolean isIncrease(String string) {
        boolean increase;
        switch (string) {
            case "-a":
                increase = true;
                break;
            case "-d":
                increase = false;
                break;
            default:
                throw new IllegalArgumentException ("Такой команды нет");
        }
        return increase;
    }

}
