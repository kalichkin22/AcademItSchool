package ru.academits.kalichkin.minesweeper.controller;

import ru.academits.kalichkin.minesweeper.common.View;
import ru.academits.kalichkin.minesweeper.common.ViewListener;
import ru.academits.kalichkin.minesweeper.model.Click;
import ru.academits.kalichkin.minesweeper.model.Field;


public class Controller implements ViewListener {
    private final View view;
    private final Field field;

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
        return field.getCell(click.row, click.column).isMine()
                && !field.getCell(click.row, click.column).isFlag() && field.getCell(click.row, click.column).isOpen();

    }


    public boolean needCheckWin() {
        return field.isWin();
    }


    @Override
    public void needShowAll() {
        field.showAll();
    }

}





