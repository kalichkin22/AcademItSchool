package ru.academits.kalichkin.minesweeper.model;

import java.util.*;

public class Field {
    private Cell[][] field;
    private int numberOfMines;
    private int numberOfFlags;


    public Field(int fieldSize, int numberOfMines) {
        this.numberOfMines = numberOfMines;
        field = new Cell[fieldSize][fieldSize];

        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                field[i][j] = new Cell();
            }
        }
    }


    public void setMines() {
        Random random = new Random();
        int countMines = 0;
        while (countMines < numberOfMines) {
            int row;
            int column;
            do {
                row = random.nextInt(field.length);
                column = random.nextInt(field.length);
            } while (field[row][column].isMine());
            field[row][column].setMine();
            countMines++;
        }
    }


    public void setNumberMinesNear() {
        for (int row = 0; row < field.length; row++) {
            for (int column = 0; column < field.length; column++) {
                if (!field[column][row].isMine()) {
                    int count = 0;
                    for (int i = -1; i <= 1; i++) {
                        for (int j = -1; j <= 1; j++) {
                            int x = row + i;
                            int y = column + j;
                            if (x < 0 || y < 0 || x > field.length - 1 || y > field.length - 1) {
                                x = row;
                                y = column;
                            }
                            count += field[y][x].isMine() ? 1 : 0;
                        }
                    }
                    field[column][row].setAmountMinesNear(count);
                }
            }
        }
    }


    public Cell getCell(int x, int y) {
        return field[x][y];
    }


    public Click actionCell(Click click) {
        Action clickResult = field[click.row][click.column].actionCell(click.button);
        switch (clickResult) {
            case OPEN:
                if (field[click.row][click.column].getAmountMinesNear() == 0) {
                    for (int i = -1; i <= 1; i++) {
                        for (int j = -1; j <= 1; j++) {
                            if ((click.row + i >= 0) && (click.row + i < field.length)
                                    && (click.column + j >= 0) && (click.column + j < field.length)) {
                                Click fakeClick = new Click(click.row + i, click.column + j, click.button);
                                actionCell(fakeClick);
                            }
                        }
                    }
                }
                break;

            case SET_FLAG:
                if (!this.field[click.row][click.column].isFlag() && numberOfFlags < numberOfMines) {
                    this.field[click.row][click.column].setFlag(true);
                    numberOfFlags++;
                } else {
                    numberOfFlags--;
                }
                break;

            case SET_QUESTION:
                this.field[click.row][click.column].setQuestion(true);
                numberOfFlags--;
                break;

            case OPEN_AROUND:
                if (field[click.row][click.column].getAmountMinesNear() == checkMinesNear(click)) {
                    for (int i = -1; i <= 1; i++) {
                        for (int j = -1; j <= 1; j++) {
                            Cell cell = field[click.row + i][click.column + j];
                            if (!cell.isMine() || !cell.isQuestion() || !cell.isFlag()) {
                                field[click.row + i][click.column + j].setOpen();
                            }
                        }
                    }
                }
            case NOT_ACTION:
                break;
        }
        return click;
    }


    public void showAll() {
        for (Cell[] row : field) {
            for (Cell cell : row) {
                cell.setOpen();
            }
        }
    }


    public int getCountFlagTrue() {
        int count = 0;
        for (Cell[] row : field) {
            for (Cell cell : row) {
                if (cell.isFlag() && cell.isMine()) {
                    count++;
                }
            }
        }
        return count;
    }


    public boolean isWin() {
        int countNotOpen = 0;
        for (Cell[] row : field) {
            for (Cell cell : row) {
                if (!cell.isOpen()) {
                    countNotOpen++;
                }
            }
        }
        return countNotOpen == numberOfMines || numberOfMines == getCountFlagTrue();
    }


    private int checkMinesNear(Click click) {
        int countMines = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                Cell cell = this.field[click.row + i][click.column + j];
                if (cell.isFlag() && cell.isMine()) {
                    countMines++;
                }
            }
        }
        return countMines;
    }


    public int size() {
        return field.length;
    }
}
