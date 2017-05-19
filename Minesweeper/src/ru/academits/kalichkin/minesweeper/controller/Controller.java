package ru.academits.kalichkin.minesweeper.controller;

import ru.academits.kalichkin.minesweeper.common.View;
import ru.academits.kalichkin.minesweeper.common.ViewListener;
import ru.academits.kalichkin.minesweeper.model.*;

import java.io.FileNotFoundException;
import java.util.List;


public class Controller implements ViewListener {
    private View view;
    private Field field;
    private TimerGame timerGame;
    private HighScores highScores = new HighScores();

    private static final int MIN_COUNT_ROW = 8;
    private static final int MAX_COUNT_ROW = 30;
    private static final int MIN_COUNT_COLUMN = 8;
    private static final int MAX_COUNT_COLUMN = 24;

    private static final int BEGINNER_MINES = 10;
    private static final int BEGINNER_NUMBER_ROWS = 9;
    private static final int BEGINNER_NUMBER_COLUMNS = 9;

    private static final int INTERMEDIATE_MINES = 50;
    private static final int INTERMEDIATE_NUMBER_ROWS = 15;
    private static final int INTERMEDIATE_NUMBER_COLUMNS = 15;


    private static final int EXPERT_MINES = 100;
    private static final int EXPERT_NUMBER_ROWS = 20;
    private static final int EXPERT_NUMBER_COLUMNS = 20;


    public Controller(Field field, View view) {
        this.view = view;
        this.field = field;
    }

    @Override
    public boolean needClick(Click click) {
        return view.onCheckFinish(field.actionCell(click));
    }


    @Override
    public int getCountFlagTrue() {
        return field.getCountFlagTrue();
    }


    @Override
    public void needDraw() {
        view.onDraw(field);
    }


    public boolean needCheckDefeat(Click click) {
        return field.getCell(click.getRow(), click.getColumn()).isMine()
                && !field.getCell(click.getRow(), click.getColumn()).isFlag() && field.getCell(click.getRow(), click.getColumn()).isOpen();

    }


    public boolean needCheckWin() {
        return field.isWin();
    }


    @Override
    public void needShowAll() {
        field.showAll();
    }


    @Override
    public Cell needGetCell(int row, int column) {
        return field.getCell(row, column);
    }


    @Override
    public Action needAction(int button) {
        return view.onAction(button);
    }


    @Override
    public void needBeginnerLevel() {
        field = new Field(BEGINNER_NUMBER_ROWS, BEGINNER_NUMBER_COLUMNS, BEGINNER_MINES);
    }

    @Override
    public void needIntermediateLevel() {
        field = new Field(INTERMEDIATE_NUMBER_ROWS, INTERMEDIATE_NUMBER_COLUMNS, INTERMEDIATE_MINES);
    }

    @Override
    public void needExpertLevel() {
        field = new Field(EXPERT_NUMBER_ROWS, EXPERT_NUMBER_COLUMNS, EXPERT_MINES);
    }

    @Override
    public void needUserLevel(int row, int column, int numberOfMines) {
        if (row < MIN_COUNT_ROW || column < MIN_COUNT_COLUMN
                || row > MAX_COUNT_ROW || column > MAX_COUNT_COLUMN) {
            throw new IllegalArgumentException();
        }

        field = new Field(row, column, numberOfMines);
    }

    @Override
    public void needStartTimer() {
        timerGame = new TimerGame();
        timerGame.startTimer();
    }

    @Override
    public String needStopTimer() {
        return timerGame.stopTimer();
    }

    @Override
    public void needWriteScores(String fileName, String name, String time) throws FileNotFoundException {
        highScores.writeScores(fileName, name, time);
    }

    @Override
    public String needScoresFilename() {
        return highScores.getFileName();
    }

    @Override
    public List<PersonWin> needReadScores(String fileName) throws FileNotFoundException {
        return highScores.readScores(fileName);
    }
}





