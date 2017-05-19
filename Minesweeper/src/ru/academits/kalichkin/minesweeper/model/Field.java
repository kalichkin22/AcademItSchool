package ru.academits.kalichkin.minesweeper.model;

import java.util.*;

public class Field {
    private Cell[][] field;
    private int numberOfMines;
    private int numberOfFlags;
    private int fieldRow;
    private int fieldColumn;
    private int countClick;


    public Field(int fieldRow, int fieldColumn, int numberOfMines) {
        this.fieldRow = fieldRow;
        this.fieldColumn = fieldColumn;
        this.numberOfMines = numberOfMines;

        field = new Cell[fieldRow][fieldColumn];

        for (int i = 0; i < fieldRow; i++) {
            for (int j = 0; j < fieldColumn; j++) {
                field[i][j] = new Cell();
            }
        }
    }


    public int getFieldRow() {
        return fieldRow;
    }

    public int getFieldColumn() {
        return fieldColumn;
    }

    private void setMines() {
        int countMines = 0;
        Random random = new Random();
        while (countMines < this.numberOfMines) {
            int row;
            int column;
            do {
                row = random.nextInt(fieldRow);
                column = random.nextInt(fieldColumn);
            } while (field[row][column].isMine());
            field[row][column].setMine(true);
            countMines++;
        }
    }


    private void setNumberMinesNear() {
        for (int row = 0; row < fieldRow; row++) {
            for (int column = 0; column < fieldColumn; column++) {
                if (!field[column][row].isMine()) {
                    int count = 0;
                    for (int i = -1; i <= 1; i++) {
                        for (int j = -1; j <= 1; j++) {
                            int x = row + i;
                            int y = column + j;
                            if (x < 0 || y < 0 || x > fieldRow - 1 || y > fieldColumn - 1) {
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


    public Cell getCell(int row, int column) {
        return field[row][column];
    }

    private Cell cellWithoutMine(Click click) {
        Cell cell;
        for (Cell[] row : field) {
            for (Cell cell2 : row) {
                if (cell2 == field[click.getRow()][click.getColumn()]) {
                    continue;
                }
                if (!cell2.isMine()) {
                    cell = cell2;
                    return cell;
                }
            }
        }
        return null;
    }

    public Click actionCell(Click click) {
        Cell cell = this.field[click.getRow()][click.getColumn()];

        if (countClick == 0) {
            setMines();
            if (cell.isMine()) {
                cell.setMine(false);
                Cell cell2 = cellWithoutMine(click);

                if (cell2 != null) {
                    cell2.setMine(true);
                }
            }
            setNumberMinesNear();
        }

        switch (click.getAction()) {
            case OPEN:
                if (!cell.isOpen()) {
                    countClick++;
                    if (!cell.isFlag() && !cell.isQuestion()) {
                        cell.setOpen();
                        if (cell.getAmountMinesNear() == 0) {
                            for (int i = -1; i <= 1; i++) {
                                for (int j = -1; j <= 1; j++) {
                                    if ((click.getRow() + i >= 0) && (click.getRow() + i < fieldRow)
                                            && (click.getColumn() + j >= 0) && (click.getColumn() + j < fieldColumn)) {
                                        Click fakeClick = new Click(click.getRow() + i, click.getColumn() + j, click.getAction());
                                        actionCell(fakeClick);
                                    }
                                }
                            }
                        }
                    }
                }
                break;

            case SET_MARKED:
                if (!cell.isOpen()) {
                    if (cell.isQuestion()) {
                        cell.setQuestion(false);
                    } else if (!cell.isFlag() && numberOfFlags < numberOfMines) {
                        cell.setFlag(true);
                        numberOfFlags++;
                    } else if (cell.isFlag()) {
                        cell.setFlag(false);
                        numberOfFlags--;
                        cell.setQuestion(true);
                    }
                }
                break;

            case OPEN_AROUND:
                if (cell.getAmountMinesNear() == checkMinesNear(click)) {
                    for (int i = -1; i <= 1; i++) {
                        for (int j = -1; j <= 1; j++) {
                            Cell cell2 = field[click.getRow() + i][click.getColumn() + j];
                            if (!cell2.isMine() || !cell2.isQuestion() || !cell2.isFlag()) {
                                field[click.getRow() + i][click.getColumn() + j].setOpen();
                            }
                        }
                    }
                }
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
                Cell cell = this.field[click.getRow() + i][click.getColumn() + j];
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
