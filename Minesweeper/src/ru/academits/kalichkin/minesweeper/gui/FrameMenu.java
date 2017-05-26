package ru.academits.kalichkin.minesweeper.gui;


import ru.academits.kalichkin.minesweeper.common.ViewListener;
import ru.academits.kalichkin.minesweeper.controller.Controller;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.io.FileNotFoundException;

class FrameMenu extends JFrame {
    private ViewListener listener;
    private JButton newGame = new JButton("Начать игру");
    private JButton scores = new JButton("Таблица рекордов");
    private JButton setting = new JButton("Настройки");
    private JButton about = new JButton("О игре");
    private JButton exit = new JButton("Выход");

    private final static boolean SHOULD_WEIGHT_X = true;


    FrameMenu(String title, ViewListener listener) throws HeadlessException {
        super(title);
        this.listener = listener;
        this.setMinimumSize(new Dimension(300, 400));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        addComponentsToPanel(this);
        initEvents();
    }


    private void initEvents() {
        scores.addActionListener(e -> {
            try {
                HighScoresDialog dialog = new HighScoresDialog(this, "Таблица рекордов", listener.needReadScores(Controller.SCORES_FILE_NAME));
                dialog.createDialog();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        });
    }


    private void addComponentsToPanel(Container contentPane) {
        GridBagLayout gbl = new GridBagLayout();
        contentPane.setLayout(gbl);

        GridBagConstraints c = new GridBagConstraints();
        Font font = new Font("Helvetica", Font.PLAIN, 25);
        c.insets = new Insets(10, 10, 0, 10);

        if (SHOULD_WEIGHT_X) {
            c.weightx = 0.5;
        }

        c.gridx = 0;
        c.gridy = 0;
        contentPane.add(newGame, c);
        newGame.setFont(font);
        newGame.setBorder(new BasicBorders.ButtonBorder(Color.LIGHT_GRAY, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.DARK_GRAY));
        newGame.setFocusPainted(true);


        c.gridx = 0;
        c.gridy = 1;
        contentPane.add(scores, c);
        scores.setFont(font);
        scores.setBorder(new BasicBorders.ButtonBorder(Color.LIGHT_GRAY, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.DARK_GRAY));
        scores.setFocusPainted(true);

        c.gridx = 0;
        c.gridy = 2;
        contentPane.add(setting, c);
        setting.setFont(font);
        setting.setBorder(new BasicBorders.ButtonBorder(Color.LIGHT_GRAY, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.DARK_GRAY));

        c.gridx = 0;
        c.gridy = 3;
        contentPane.add(about, c);
        about.setFont(font);
        about.setBorder(new BasicBorders.ButtonBorder(Color.LIGHT_GRAY, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.DARK_GRAY));

        c.gridx = 0;
        c.gridy = 4;
        contentPane.add(exit, c);
        exit.setFont(font);
        exit.setBorder(new BasicBorders.ButtonBorder(Color.LIGHT_GRAY, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.DARK_GRAY));
    }
}
