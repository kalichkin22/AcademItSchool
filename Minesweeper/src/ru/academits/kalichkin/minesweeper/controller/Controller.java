package ru.academits.kalichkin.minesweeper.controller;

import ru.academits.kalichkin.minesweeper.common.View;
import ru.academits.kalichkin.minesweeper.common.ViewListener;
import ru.academits.kalichkin.minesweeper.model.Action;
import ru.academits.kalichkin.minesweeper.model.Cell;
import ru.academits.kalichkin.minesweeper.model.Click;
import ru.academits.kalichkin.minesweeper.model.Field;


public class Controller implements ViewListener {
    private View view;
    private Field field;

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
    public void needSetMines() {
        field.setMines();
    }


    @Override
    public void needSetNumberMinesNear() {
        field.setNumberMinesNear();
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
    public int needGetSize() {
        return field.size();
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
        field = new Field(9, 9, 10);
    }

    @Override
    public void needIntermediateLevel() {
        field = new Field(15, 15, 50);
    }

    @Override
    public void needExpertLevel() {
        field = new Field(20, 20, 70);
    }

    @Override
    public void needUserLevel(int row, int column, int numberOfMines) {
        field = new Field(row, column, numberOfMines);
    }
}





