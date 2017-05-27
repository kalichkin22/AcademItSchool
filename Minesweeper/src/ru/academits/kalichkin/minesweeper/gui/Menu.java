package ru.academits.kalichkin.minesweeper.gui;


import ru.academits.kalichkin.minesweeper.common.ViewListener;
import ru.academits.kalichkin.minesweeper.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;


class Menu {
    private JFrame frame;
    private ViewListener listener;
    private JMenu menu;
    private JMenuItem scores;
    private JMenuItem beginner;
    private JMenuItem intermediate;
    private JMenuItem expert;
    private JMenuItem user;
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
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);
    }


    private void createMenu(String title) {
        Font font = new Font("Verdana", Font.PLAIN, 12);
        menu = new JMenu(title);
        menu.setFont(font);

        scores = new JMenuItem("Таблица рекордов");
        scores.setFont(font);
        menu.add(scores);

        JMenu level = new JMenu("Уровень");
        level.setFont(font);
        menu.add(level);

        beginner = new JMenuItem("Новичек");
        beginner.setFont(font);
        level.add(beginner);

        intermediate = new JMenuItem("Любитель");
        intermediate.setFont(font);
        level.add(intermediate);

        expert = new JMenuItem("Профессионал");
        expert.setFont(font);
        level.add(expert);

        user = new JMenuItem("Пользовательский");
        user.setFont(font);
        level.add(user);

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


        beginner.addActionListener(e -> {
            listener.needBeginnerLevel();
            frame.setSize(new Dimension(320, 420));

        });

        intermediate.addActionListener(e -> {
            listener.needIntermediateLevel();
            frame.setSize(new Dimension(500, 600));
        });

        expert.addActionListener(e -> {
            listener.needExpertLevel();
            frame.setSize(new Dimension(650, 900));
        });

        user.addActionListener(e -> {
            UserLevelDialog dialog = new UserLevelDialog();
            if (dialog.show(frame)) {
                listener.needUserLevel(dialog.getRow(), dialog.getColumn(), dialog.getMines());
                frame.setSize(new Dimension(650, 900));
            }
        });

        exit.addActionListener(e -> System.exit(0));
    }

}