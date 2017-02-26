package ru.academits.kalichkin.cft.parser;

public class ProgramArgs {

    private String fileName;
    private String fileOut;
    private Type type;
    private boolean increase;


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileOut(String fileOut) {
        this.fileOut = fileOut;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setIncrease(boolean increase) {
        this.increase = increase;
    }

    public String getFileOut() {
        return fileOut;
    }

    public boolean getIncrease() {
        return increase;
    }
}