package ru.academits.kalichkin.minesweeper.gui;


import ru.academits.kalichkin.minesweeper.common.View;
import ru.academits.kalichkin.minesweeper.common.ViewListener;
import ru.academits.kalichkin.minesweeper.model.*;
import ru.academits.kalichkin.minesweeper.model.Action;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AppView implements View {
    private ViewListener listener;
    private final JFrame frame = new JFrame("Minesweeper");
    private JLabel labelMines = new JLabel();
    private JButton newGame = new JButton();
    private JPanel gamePanel = new JPanel();
    private JLabel labelTime = new JLabel();
    private GameField2 gameField2;
    private JMenuItem menu = new JMenuItem();
    private Click click;

    private final static boolean SHOULD_WEIGHT_X = true;
    private static final String SMILE_HAPPY = "/res/Happy.png";
    private static final String SMILE_SAD = "/res/Sad.png";
    private static final String BANG = "/res/bang.png";


    private void createFrame() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(300, 400));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void initEvents() {
        gameField2.setBackground(Color.white);
        listener.needStartTimer();
        gameField2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Action action = listener.needAction(e.getButton());
                int row = e.getX() / GameField2.BLOCK_SIZE;
                int column = e.getY() / GameField2.BLOCK_SIZE;
                click = new Click(row, column, action);
                listener.needClick(click);

                labelTime.setText(listener.needGetTime());
                labelMines.setText(String.valueOf(listener.needNumberOfFlags()));
                gameField2.repaint();
            }
        });

        newGame.addActionListener(e -> {
            gamePanel.removeAll();
            labelTime.setText("00:00");
            listener.needBeginnerLevel();
            addComponentsToPanel(frame);
            initEvents();
        });
    }


    private void addComponentsToPanel(Container contentPane) {
        GridBagLayout gbl = new GridBagLayout();
        contentPane.setLayout(gbl);

        GridBagConstraints c = new GridBagConstraints();

        if (SHOULD_WEIGHT_X) {
            c.weightx = 0.5;
        }

        c.fill = GridBagConstraints.CENTER;
        c.anchor = GridBagConstraints.CENTER;

        c.gridx = 0;
        c.gridy = 0;
        contentPane.add(menu, c);

        c.gridx = 0;
        c.gridy = 1;
        contentPane.add(labelMines, c);
        labelMines.setForeground(Color.DARK_GRAY);
        labelMines.setText(String.valueOf(listener.needNumberOfFlags()));
        labelMines.setBorder(new BasicBorders.ButtonBorder(Color.LIGHT_GRAY, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.DARK_GRAY));
        labelMines.setPreferredSize(new Dimension(85, 50));
        labelMines.setHorizontalAlignment(SwingConstants.CENTER);
        labelMines.setFont(new Font("Helvetica", Font.PLAIN, 25));

        c.gridx = 1;
        c.gridy = 1;
        newGame.setIcon(new ImageIcon(getClass().getResource(SMILE_HAPPY)));
        newGame.setBorderPainted(false);
        contentPane.add(newGame, c);

        c.gridx = 2;
        c.gridy = 1;
        labelTime.setPreferredSize(new Dimension(85, 50));
        labelTime.setForeground(Color.DARK_GRAY);
        labelTime.setHorizontalAlignment(SwingConstants.CENTER);
        labelTime.setFont(new Font("Helvetica", Font.PLAIN, 25));
        labelTime.setBorder(new BasicBorders.FieldBorder(Color.LIGHT_GRAY, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.DARK_GRAY));
        contentPane.add(labelTime, c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 3;
        gamePanel.setBorder(new BasicBorders.FieldBorder(Color.LIGHT_GRAY, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.DARK_GRAY));
        contentPane.add(gamePanel, c);
        listener.needDraw();
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
        if (button == MouseEvent.BUTTON1) {
            return Action.OPEN;
        } else if (button == MouseEvent.BUTTON3) {
            return Action.SET_MARKED;
        } else if (button == MouseEvent.BUTTON2) {
            return Action.OPEN_AROUND;
        }
        return Action.NOT_ACTION;
    }


    @Override
    public void onDefeat() {
        gameField2.getLabel(click.getColumn(), click.getRow()).setIcon(new ImageIcon(getClass().getResource(BANG)));
        newGame.setIcon(new ImageIcon(getClass().getResource(SMILE_SAD)));
    }

    @Override
    public String onIsWin() {
        gameField2.repaint();
        Object[] message = new Object[]{"ВЫ ВЫИГРАЛИ! ПОЗДРАВЛЯЕМ!!!", "Введите Ваше имя:"};
        String name = JOptionPane.showInputDialog(frame, message, "Конец игры", JOptionPane.PLAIN_MESSAGE);

        if (name == null) {
            name = "Неизвестный игрок";
        }
        return name;
    }
}

