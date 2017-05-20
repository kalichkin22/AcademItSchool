package ru.academits.kalichkin.minesweeper.common;

import ru.academits.kalichkin.minesweeper.model.PersonWin;
import ru.academits.kalichkin.minesweeper.model.Action;
import ru.academits.kalichkin.minesweeper.model.Cell;
import ru.academits.kalichkin.minesweeper.model.Click;

import java.io.FileNotFoundException;
import java.util.List;


public interface ViewListener {

    void needDraw();

    boolean needClick(Click click);

    int getCountFlagTrue();

    Cell needGetCell(int row, int column);

    Action needAction(int button);

    void needBeginnerLevel();

    void needIntermediateLevel();

    void needExpertLevel();

    void needUserLevel(int row, int column, int numberOfMines);

    List<PersonWin> needReadScores(String fileName) throws FileNotFoundException;
}
