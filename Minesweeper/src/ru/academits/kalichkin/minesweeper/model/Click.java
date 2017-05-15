package ru.academits.kalichkin.minesweeper.model;


public class Click {
    public int row;
    public int column;
    int button;

    public Click(int row, int column, int button) {
        this.row = row;
        this.column = column;
        this.button = button;
    }
}