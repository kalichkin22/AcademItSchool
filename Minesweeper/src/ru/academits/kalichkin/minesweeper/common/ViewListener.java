package ru.academits.kalichkin.minesweeper.common;

import ru.academits.kalichkin.minesweeper.model.*;
import ru.academits.kalichkin.minesweeper.model.Action;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.List;


public interface ViewListener {

    void needDraw();

    boolean needClick(Click click);

    int getCountFlagTrue();

    Action needAction(int button);

    void needBeginnerLevel();

    void needIntermediateLevel();

    void needExpertLevel();

    void needUserLevel(int row, int column, int numberOfMines);

    void needStartTimer(TimerGame label);

    List<PersonWin> needReadScores(String fileName) throws FileNotFoundException;

    int needNumberOfFlags();

    void needNewGame();

    void setFirstClick(int firstClick);
}
