package ru.academits.kalichkin.minesweeper.gui;


import ru.academits.kalichkin.minesweeper.common.ViewListener;
import ru.academits.kalichkin.minesweeper.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;


class Menu {
    private JFrame frame;
    private ViewListener listener;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem scores;
    private JMenuItem setting;
    private JMenuItem about;
    private JMenuItem exit;


    Menu(JFrame frame, ViewListener listener) {
        this.frame = frame;
        this.listener = listener;
    }

    void addOnFrame(String title) {
        createMenu(title);
        createMenuBar();
        initEvents();
    }


    private void createMenuBar() {
        menuBar = new JMenuBar();
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);
    }


    private void createMenu(String title){
        Font font = new Font("Verdana", Font.PLAIN, 12);
        menu = new JMenu(title);
        menu.setFont(font);

        scores = new JMenuItem("Таблица рекордов");
        scores.setFont(font);
        menu.add(scores);

        setting = new JMenuItem("Настройки");
        setting.setFont(font);
        menu.add(setting);

        about = new JMenuItem("О игре");
        about.setFont(font);
        menu.add(about);

        menu.addSeparator();

        exit = new JMenuItem("Exit");
        exit.setFont(font);
        menu.add(exit);
    }


    private void initEvents() {
        scores.addActionListener(e -> {
            try {
                HighScoresDialog dialog = new HighScoresDialog(frame, "Таблица рекордов", listener.needReadScores(Controller.SCORES_FILE_NAME));
                dialog.createDialog();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        });

        exit.addActionListener(e -> System.exit(0));
    }

}