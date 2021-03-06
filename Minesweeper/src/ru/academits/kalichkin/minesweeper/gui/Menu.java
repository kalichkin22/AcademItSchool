package ru.academits.kalichkin.minesweeper.gui;

import ru.academits.kalichkin.minesweeper.common.ViewListener;
import ru.academits.kalichkin.minesweeper.controller.Controller;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;


class Menu {
    private JFrame frame;
    private ViewListener listener;
    private JMenu menu;
    private JMenuItem newGame;
    private JMenuItem scores;
    private JMenuItem beginner;
    private JMenuItem intermediate;
    private JMenuItem expert;
    private JMenuItem user;
    private JMenuItem about;
    private JMenuItem exit;


    Menu(ViewListener listener, JFrame frame) {
        this.frame = frame;
        this.listener = listener;
    }


    void addOnFrame(AppView view) {
        createMenu();
        createMenuBar();
        initEvents(view);
    }


    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);
    }


    private void createMenu() {
        Font font = new Font("Verdana", Font.PLAIN, 12);
        menu = new JMenu("Меню");
        menu.setFont(font);

        newGame = new JMenuItem("Новая игра");
        newGame.setFont(font);
        menu.add(newGame);

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

        exit = new JMenuItem("Выход");
        exit.setFont(font);
        menu.add(exit);
    }


    private void initEvents(AppView view) {
        newGame.addActionListener(e -> view.setNewGame());

        newGame.setMnemonic('X');
        newGame.setAccelerator(KeyStroke.getKeyStroke('X', KeyEvent.CTRL_MASK));

        scores.addActionListener(e -> {
            try {
                HighScoresDialog dialog = new HighScoresDialog(frame, "Таблица рекордов", listener.needReadScores(Controller.SCORES_FILE_NAME));
                dialog.createDialog();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        });

        scores.setMnemonic('S');
        scores.setAccelerator(KeyStroke.getKeyStroke('S', KeyEvent.CTRL_MASK));


        beginner.addActionListener(e -> {
            listener.needBeginnerLevel();
            frame.setSize(new Dimension(320, 420));
            view.setNewGame();
        });

        beginner.setMnemonic('A');
        beginner.setAccelerator(KeyStroke.getKeyStroke('A', KeyEvent.CTRL_MASK));

        intermediate.addActionListener(e -> {
            listener.needIntermediateLevel();
            frame.setSize(new Dimension(500, 600));
            view.setNewGame();
        });

        intermediate.setMnemonic('D');
        intermediate.setAccelerator(KeyStroke.getKeyStroke('D', KeyEvent.CTRL_MASK));

        expert.addActionListener(e -> {
            listener.needExpertLevel();
            frame.setSize(new Dimension(650, 900));
            view.setNewGame();
        });

        expert.setMnemonic('F');
        expert.setAccelerator(KeyStroke.getKeyStroke('F', KeyEvent.CTRL_MASK));

        user.addActionListener(e -> {
            UserLevelDialog dialog = new UserLevelDialog();
            if (dialog.show(frame)) {
                listener.needUserLevel(dialog.getColumn(), dialog.getRow(), dialog.getMines());
                frame.setSize(new Dimension(650, 900));
                view.setNewGame();
            }
        });

        user.setMnemonic('X');
        user.setAccelerator(KeyStroke.getKeyStroke('X', KeyEvent.CTRL_MASK));


        about.addActionListener(e -> {
            AboutFrame aboutFrame = new AboutFrame();
            aboutFrame.createFrame();
        });

        about.setMnemonic('Z');
        about.setAccelerator(KeyStroke.getKeyStroke('Z', KeyEvent.CTRL_MASK));

        exit.addActionListener(e -> System.exit(0));

        exit.setMnemonic('W');
        exit.setAccelerator(KeyStroke.getKeyStroke('W', KeyEvent.CTRL_MASK));
    }

}