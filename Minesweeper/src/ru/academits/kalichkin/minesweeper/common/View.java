package ru.academits.kalichkin.minesweeper.common;

import ru.academits.kalichkin.minesweeper.model.Click;
import ru.academits.kalichkin.minesweeper.model.Field;

import java.awt.*;

public interface View {
    void startApplication();

    void onDraw(Field field);

    void setViewListener(ViewListener listener);

    boolean onCheckFinish(Click click);

}
