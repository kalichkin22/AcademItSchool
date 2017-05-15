package ru.academits.kalichkin.minesweeper.common;

import ru.academits.kalichkin.minesweeper.model.Click;
import ru.academits.kalichkin.minesweeper.model.Field;

public interface View {
    void startApplication();

    void onDraw(Field field);

    boolean onCheckDefeat(Click click);

    void setViewListener(ViewListener listener);
}
