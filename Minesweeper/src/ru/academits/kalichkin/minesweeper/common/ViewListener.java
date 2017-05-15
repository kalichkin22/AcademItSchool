package ru.academits.kalichkin.minesweeper.common;

import ru.academits.kalichkin.minesweeper.model.Cell;
import ru.academits.kalichkin.minesweeper.model.Click;

public interface ViewListener {
    void needSetMines();

    void needSetNumberMinesNear();

    void needDraw();

    void needClick(Click click);

    int getCountFlagTrue();

    boolean needCheckDefeat(Click click);

    boolean needCheckWin();

    Cell needGetCell(int row, int column);

    void needShowAll();
}
