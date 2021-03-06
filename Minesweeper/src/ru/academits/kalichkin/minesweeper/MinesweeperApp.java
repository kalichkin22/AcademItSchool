package ru.academits.kalichkin.minesweeper;

import ru.academits.kalichkin.minesweeper.common.View;
import ru.academits.kalichkin.minesweeper.controller.Controller;
import ru.academits.kalichkin.minesweeper.gui.AppView;
import ru.academits.kalichkin.minesweeper.model.Field;

import java.io.IOException;


public class MinesweeperApp {
    public static void main(String[] args) throws IOException {
        View view = new AppView();
        Field field = new Field(9, 9, 10);
        Controller controller = new Controller(field, view);
        view.setViewListener(controller);
        view.startApplication();
    }
}
