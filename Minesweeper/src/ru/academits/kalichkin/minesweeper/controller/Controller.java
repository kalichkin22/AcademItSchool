package ru.academits.kalichkin.minesweeper.controller;

import ru.academits.kalichkin.minesweeper.common.View;
import ru.academits.kalichkin.minesweeper.common.ViewListener;

import ru.academits.kalichkin.minesweeper.model.*;
import ru.academits.kalichkin.minesweeper.model.Action;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class Controller implements ViewListener {
    private View view;
    private Field field;
    private TimerGame timerGame;
    private HighScores highScores = new HighScores();
    private int firstClick;

    public static final String SCORES_FILE_NAME = "scores.txt";

    public static final int MIN_COUNT_ROW = 8;
    public static final int MAX_COUNT_ROW = 20;
    public static final int MIN_COUNT_COLUMN = 8;
    public static final int MAX_COUNT_COLUMN = 20;

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

    public void setFirstClick(int firstClick) {
        this.firstClick = firstClick;
    }


    @Override
    public boolean needClick(Click click) throws IOException {
        field.actionCell(click);

        if (firstClick == 0) {
            timerGame.startTimer();
        }

        firstClick++;
        if (field.isDefeat(click)) {
            timerGame.stopTimer();
            field.showAll();
            view.onDefeat();
            firstClick = 0;
            return false;
        } else if (field.isWin()) {
            String time = timerGame.stopTimer();
            field.showAll();
            try {
                highScores.writeScores(SCORES_FILE_NAME, view.onIsWin(), time);
            } catch (FileNotFoundException e) {
                System.out.printf("Файл %s не найден", SCORES_FILE_NAME);
            }
            firstClick = 0;
            return false;
        }
        return true;
    }


    @Override
    public int getCountFlagTrue() {
        return field.getCountFlagTrue();
    }


    @Override
    public void needDraw() throws IOException {
        view.onDraw(field);
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
    public void needSetTimer(TimerGame timerGame) {
        this.timerGame = timerGame;
    }


    @Override
    public ArrayList<PersonWin> needReadScores(String fileName) throws FileNotFoundException {
        return highScores.readScores(fileName);
    }


    @Override
    public int needNumberOfFlags() {
        return field.getNumberOfFlags();
    }


    public void needNewGame() {
        this.field = field.getField();
    }
}





