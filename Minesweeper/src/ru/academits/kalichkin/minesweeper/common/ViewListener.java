package ru.academits.kalichkin.minesweeper.common;

import ru.academits.kalichkin.minesweeper.model.Action;
import ru.academits.kalichkin.minesweeper.model.Cell;
import ru.academits.kalichkin.minesweeper.model.Click;


public interface ViewListener {
    void needSetMines();

    void needSetNumberMinesNear();

    void needDraw();

    boolean needClick(Click click);

    int getCountFlagTrue();

    boolean needCheckDefeat(Click click);

    boolean needCheckWin();

    void needShowAll();

    int needGetSize();

    Cell needGetCell(int row, int column);

    Action needAction(int button);

    void needBeginnerLevel();

    void needIntermediateLevel();

    void needExpertLevel();

    void needUserLevel(int row, int column, int numberOfMines);
}
