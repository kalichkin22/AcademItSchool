package ru.academits.kalichkin.minesweeper.common;

import ru.academits.kalichkin.minesweeper.model.Action;
import ru.academits.kalichkin.minesweeper.model.Field;


public interface View {
    void startApplication();

    void onDraw(Field field);

    void setViewListener(ViewListener listener);

    Action onAction(int button);

    void onDefeat();

    String onIsWin();
}
