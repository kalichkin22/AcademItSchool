package ru.academits.kalichkin.minesweeper.model;

public class Cell {
    private boolean isMine;
    private boolean isOpen;
    private boolean isFlag;
    private boolean isQuestion;
    private int amountMinesNear;

    Cell() {
        this.isOpen = false;
        this.isFlag = false;
        this.isQuestion = false;
    }


    public boolean isMine() {
        return isMine;
    }


    void setMine() {
        isMine = true;
    }


    public boolean isOpen() {
        return isOpen;
    }


    void setOpen() {
        this.isOpen = true;
    }


    public boolean isFlag() {
        return isFlag;
    }


    void setFlag(boolean isFlag) {
        this.isFlag = isFlag;
    }


    boolean isQuestion() {
        return isQuestion;
    }


    void setQuestion(boolean question) {
        isQuestion = question;
    }


    public int getAmountMinesNear() {
        return amountMinesNear;
    }


    void setAmountMinesNear(int amountMinesNear) {
        this.amountMinesNear = amountMinesNear;
    }


    @Override
    public String toString() {
        String s = "[X]";
        if (isOpen) {
            if (!isFlag && amountMinesNear == 0) {
                s = "[ ]";
            }

            if (this.amountMinesNear > 0) {
                s = "[" + Integer.toString(amountMinesNear) + "]";
            }

            if (isMine) {
                s = "[*]";
            }
        }

        if (isFlag) {
            s = "[f]";
        }

        if (isFlag && !isMine && isOpen) {
            s = "[̶f]";
        }

        if (isQuestion) {
            s = "[?]";
        }

        if (isQuestion && !isMine && isOpen) {
            s = "[&]";
        }

        if (isQuestion && isMine && isOpen) {
            s = "[$]";
        }

        return s;
    }
}


