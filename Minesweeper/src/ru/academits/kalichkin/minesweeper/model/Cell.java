package ru.academits.kalichkin.minesweeper.model;

public class Cell {
    private boolean isMine;
    private boolean isOpen;
    private boolean isFlag;
    private int amountMinesNear;


    Cell() {
        this.isOpen = false;
        this.isFlag = false;
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


    private void setOpen() {
        isOpen = true;
    }


    public boolean isFlag() {
        return isFlag;
    }


    void setFlag(boolean isFlag) {
        this.isFlag = isFlag;
    }


    int getAmountMinesNear() {
        return amountMinesNear;
    }


    void setAmountMinesNear(int amountMinesNear) {
        this.amountMinesNear = amountMinesNear;
    }


    Action actionCell(int button) {
        if (button != 0 && button != 1) {
            throw new IllegalArgumentException();
        }
        if (!isOpen) {
            if (button == 0 && !isFlag) {
                this.setOpen();
                return Action.OPEN;
            } else if (button == 1) {
                if (isFlag) {
                    this.setFlag(false);
                } else {
                    return Action.SET_FLAG;
                }
            }
        }
        return Action.NOT_ACTION;
    }


    void show() {
        this.setOpen();
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
        if (isFlag && !isMine() && isOpen) {
            s = "[̶f̶]";
        }
        return s;
    }
}


