package ru.academits.kalichkin.minesweeper.gui;


import ru.academits.kalichkin.minesweeper.common.View;
import ru.academits.kalichkin.minesweeper.common.ViewListener;
import ru.academits.kalichkin.minesweeper.model.*;
import ru.academits.kalichkin.minesweeper.model.Action;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AppView implements View {
    private ViewListener listener;
    private final JFrame frame = new JFrame("Minesweeper");
    private JLabel labelMines = new JLabel("Mines");
    private JLabel labelTime = new JLabel("Time");
    private JButton newGame = new JButton();
    private JPanel gamePanel = new JPanel();
    private GameField2 gameField2;

    private final static boolean SHOULD_WEIGHT_X = true;


    private void createFrame() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(330, 400));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void initEvents() {
        listener.needDraw();
        gameField2.setBackground(Color.white);
        gameField2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Action action = listener.needAction(e.getButton());
                Click click = new Click(e.getX() / GameField2.BLOCK_SIZE, e.getY() / GameField2.BLOCK_SIZE, action);
                listener.needClick(click);
                listener.needStartTimer();
                gameField2.repaint();
            }
        });
    }


    private void addComponentsToPanel(Container contentPane) {
        GridBagLayout gbl = new GridBagLayout();
        contentPane.setLayout(gbl);

        GridBagConstraints c = new GridBagConstraints();

        if (SHOULD_WEIGHT_X) {
            c.weightx = 0.5;
        }

        c.ipadx = 0;
        c.fill = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 20, 0, 0);


        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 0;
        contentPane.add(labelMines, c);

        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 1;
        c.gridy = 0;
        newGame.setIcon(new ImageIcon(getClass().getResource("/resources/Happy.png")));
        newGame.setBackground(Color.lightGray);
        newGame.setBorderPainted(false);
        contentPane.add(newGame, c);

        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 0, 20);
        c.gridx = 2;
        c.gridy = 0;
        contentPane.add(labelTime, c);

        c.insets = new Insets(0, 20, 0, 20);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 3;
        contentPane.add(gamePanel, c);
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
        gameField2 = new GameField2(field);
        gamePanel.add(gameField2);
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
        System.out.println("вывываываы");
    }

    @Override
    public String onIsWin() {
        return null;
    }
}

