package ru.academits.kalichkin.cft.programArgs;

import ru.academits.kalichkin.cft.type.Type;

public class ProgramArgs {

    private String fileName;
    private String fileOut;
    private Type type;
    private boolean direction;


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

    public void setDirection(boolean direction) {
        this.direction = direction;
    }

    public boolean isDirection() {
        return direction;
    }

    public String getFileOut() {
        return fileOut;
    }

    public boolean getDirection() {
        return direction;
    }
}