package ru.academits.kalichkin.cft.programCommands;

public class ProgramCommands {

    private String fileName;
    private String fileOut;
    private String type;


    protected boolean getDirection(String args) {
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
