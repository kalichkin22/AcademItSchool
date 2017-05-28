package ru.academits.kalichkin.minesweeper.common;

import ru.academits.kalichkin.minesweeper.model.Action;
import ru.academits.kalichkin.minesweeper.model.Field;

import java.io.IOException;


public interface View {
    void startApplication();

    void onDraw(Field field) throws IOException;

    void setViewListener(ViewListener listener);

    Action onAction(int button);

    void onDefeat() throws IOException;

    String onIsWin() throws IOException;
}
