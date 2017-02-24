package ru.academits.kalichkin.cft.programCommands;

public class ProgramCommands {


    protected static boolean direction(String args) {
        boolean direction;
        switch (args) {
            case "-a":
                direction = false;
                break;
            case "-d":
                direction = true;
                break;
            default:
                throw new IllegalArgumentException("Такой команды нет");
        }
        return direction;
    }
}
