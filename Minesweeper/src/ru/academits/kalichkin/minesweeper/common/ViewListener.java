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

    boolean needCheckDefeat(Click click);

    boolean needCheckWin();

    void needShowAll();

    Cell needGetCell(int row, int column);

    Action needAction(int button);

    void needBeginnerLevel();

    void needIntermediateLevel();

    void needExpertLevel();

    void needUserLevel(int row, int column, int numberOfMines);

    void needStartTimer();

    String needStopTimer();

    void needWriteScores(String fileName, String name, String time) throws FileNotFoundException;

    String needScoresFilename();

    List<PersonWin> needReadScores(String fileName) throws FileNotFoundException;
}
