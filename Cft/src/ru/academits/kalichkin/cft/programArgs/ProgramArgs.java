package ru.academits.kalichkin.cft.programArgs;

public class ProgramArgs {

    private String fileName;
    private String fileOut;
    private String type;
    private String direction;

    public ProgramArgs(String fileName, String fileOut, String type, String direction) {
        this.fileName = fileName;
        this.fileOut = fileOut;
        this.type = type;
        this.direction = direction;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileOut() {
        return fileOut;
    }

    public String getType() {
        return type;
    }

    public String getDirection() {
        return direction;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileOut(String fileOut) {
        this.fileOut = fileOut;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}