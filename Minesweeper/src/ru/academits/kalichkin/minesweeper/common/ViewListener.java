package ru.academits.kalichkin.minesweeper.common;

import ru.academits.kalichkin.minesweeper.model.Click;
import ru.academits.kalichkin.minesweeper.model.Field;

public interface ViewListener {
    void needSetMines();

    void needSetNumberMinesNear();

    void needDraw();

    boolean needClick(Click click);

    int getCountFlagTrue();

    boolean needCheckDefeat(Click click);

    boolean needCheckWin();

    void needShowAll();
}
