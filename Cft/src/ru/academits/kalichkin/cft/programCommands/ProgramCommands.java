package ru.academits.kalichkin.cft.programCommands;

public class ProgramCommands {

    public static boolean isAscending(String args) {
        boolean isAscending = true;
        switch (args) {
            case "-a":
                isAscending = false;
                break;
            case "-d":
                isAscending = true;
                break;
            default:
                throw new IllegalArgumentException("Такой команды нет");
        }
        return isAscending;
    }

   /* public List type(String str) {
        if (str.equals("-i")) {
            ArrayList<Integer> list = FileReader.readInt(args[0]);
        }
        if (str.equals("-s")) {
            ArrayList<String> list = FileReader.readString(args[0]);
        } else {
            throw new IllegalArgumentException("Такой команды нет");
        }
        return list;
    }
    */
}
