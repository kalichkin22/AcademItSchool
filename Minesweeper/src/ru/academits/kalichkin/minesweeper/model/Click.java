package ru.academits.kalichkin.minesweeper.model;


public class Click {
    private int row;
    private int column;
    private Action action;

    public Click(int row, int column, Action action) {
        this.row = row;
        this.column = column;
        this.action = action;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    Action getAction() {
        return action;
    }

}