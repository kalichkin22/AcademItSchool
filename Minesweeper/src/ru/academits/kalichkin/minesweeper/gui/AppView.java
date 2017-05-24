package ru.academits.kalichkin.minesweeper.gui;


import ru.academits.kalichkin.minesweeper.common.View;
import ru.academits.kalichkin.minesweeper.common.ViewListener;
import ru.academits.kalichkin.minesweeper.model.*;
import ru.academits.kalichkin.minesweeper.model.Action;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AppView implements View {
    private ViewListener listener;
    private final JFrame frame = new JFrame("Minesweeper");
    private JLabel labelMines = new JLabel("Mines");
    private JLabel labelTime = new JLabel("Time");
    private JButton newGame = new JButton("New game");
    private JPanel gamePanel = new JPanel();
    private GameField gameField;


    private void createFrame() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(300, 400));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void initEvents() {
        listener.needDraw();
        Action action = listener.needAction(gameField.getButton());
        Click click = new Click(gameField.getRow(), gameField.getColumn(), action);
        listener.needClick(click);

    }


    private void addComponentsToPanel(Container contentPane) {
        GridBagLayout gbl = new GridBagLayout();
        contentPane.setLayout(gbl);

        GridBagConstraints c = new GridBagConstraints();

        c.ipadx = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(20, 20, 0, 20);

        c.ipady = 0;
        c.weighty = 0.0;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 0;
        contentPane.add(labelMines, c);

        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        contentPane.add(newGame, c);

        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 0;
        contentPane.add(labelTime, c);

        GridBagConstraints c1 = new GridBagConstraints();
        c1.gridwidth = 3;
        c1.gridx = 0;
        c1.gridy = 1;
        contentPane.add(gamePanel, c1);
    }


    @Override
    public void startApplication() {
        SwingUtilities.invokeLater(() -> {
            createFrame();
            addComponentsToPanel(frame);
            initEvents();
        });
    }


    @Override
    public void onDraw(Field field) {
        gameField = new GameField(field);
        gamePanel.add(gameField);
    }


    @Override
    public void setViewListener(ViewListener listener) {
        this.listener = listener;
    }


    @Override
    public Action onAction(int button) {
        if (button == 1) {
            return Action.OPEN;
        } else if (button == 3) {
            return Action.SET_MARKED;
        } else if (button == MouseEvent.MOUSE_WHEEL) {
            return Action.OPEN_AROUND;
        }
        return Action.NOT_ACTION;
    }


    @Override
    public void onDefeat() {

    }

    @Override
    public String onIsWin() {
        return null;
    }

}

