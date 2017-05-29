package ru.academits.kalichkin.minesweeper.common;

import ru.academits.kalichkin.minesweeper.model.*;
import ru.academits.kalichkin.minesweeper.model.Action;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


public interface ViewListener {

    void needDraw() throws IOException;

    boolean needClick(Click click) throws IOException;

    int getCountFlagTrue();

    Action needAction(int button);

    void needBeginnerLevel();

    void needIntermediateLevel();

    void needExpertLevel();

    void needUserLevel(int row, int column, int numberOfMines);

    void needSetTimer(TimerGame timerGame);

    List<PersonWin> needReadScores(String fileName) throws FileNotFoundException;

    int needNumberOfFlags();

    void needNewGame();

    void setFirstClick(int firstClick);

    Field needGetField();
}
