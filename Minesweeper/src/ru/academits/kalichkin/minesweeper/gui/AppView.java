package ru.academits.kalichkin.minesweeper.gui;

import ru.academits.kalichkin.minesweeper.common.View;
import ru.academits.kalichkin.minesweeper.common.ViewListener;
import ru.academits.kalichkin.minesweeper.model.*;
import ru.academits.kalichkin.minesweeper.model.Action;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;

import java.awt.event.*;
import java.io.IOException;

public class AppView implements View {
    private ViewListener listener;
    private final JFrame frame = new JFrame("Minesweeper");
    private JLabel labelMines = new JLabel();
    private JButton newGame = new JButton();
    private JPanel gamePanel = new JPanel();
    private GameField gameField;
    private TimerGame timerGame = new TimerGame();
    private Click click;

    private final static boolean SHOULD_WEIGHT_X = true;

    private ImageIcon smileSad = new ImageIcon(getClass().getResource("/res/Sad.png"));
    private ImageIcon smileHappy = new ImageIcon(getClass().getResource("/res/Happy.png"));
    private ImageIcon bang = new ImageIcon(getClass().getResource("/res/bang.png"));

    public AppView() throws IOException {
    }


    private void createFrame() throws IOException {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(320, 420));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void initEvents() {
        try {
            listener.needDraw();
        } catch (IOException e) {
            e.printStackTrace();
        }
        listener.needSetTimer(timerGame);
        gameField.setBackground(Color.white);
        gameField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                try {
                    Action action = listener.needAction(e.getButton());
                    int row = e.getX() / GameField.BLOCK_SIZE;
                    int column = e.getY() / GameField.BLOCK_SIZE;
                    click = new Click(row, column, action);
                    listener.needClick(click);

                    labelMines.setText(String.valueOf(listener.needNumberOfFlags()));
                    gameField.repaint();
                } catch (IllegalStateException el) {
                    JOptionPane.showMessageDialog(frame, "Игра окончена, начните новую ;)", null, JOptionPane.PLAIN_MESSAGE);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });


        newGame.addActionListener(e -> setNewGame());
    }


    private void addComponentsToPanel(Container contentPane) {
        Menu menu = new Menu(listener, frame);
        menu.addOnFrame(this);

        GridBagLayout gbl = new GridBagLayout();
        contentPane.setLayout(gbl);

        GridBagConstraints c = new GridBagConstraints();

        if (SHOULD_WEIGHT_X) {
            c.weightx = 0.5;
        }


        c.fill = GridBagConstraints.CENTER;
        c.anchor = GridBagConstraints.CENTER;

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
        newGame.setIcon(smileHappy);
        newGame.setBackground(Color.lightGray);
        contentPane.add(newGame, c);
        newGame.setBorderPainted(false);

        c.gridx = 2;
        c.gridy = 1;

        timerGame.setText("00:00");
        timerGame.setPreferredSize(new Dimension(85, 50));
        timerGame.setForeground(Color.DARK_GRAY);
        timerGame.setHorizontalAlignment(SwingConstants.CENTER);
        timerGame.setFont(new Font("Helvetica", Font.PLAIN, 25));
        timerGame.setBorder(new BasicBorders.FieldBorder(Color.LIGHT_GRAY, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.DARK_GRAY));
        contentPane.add(timerGame, c);


        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 3;
        gamePanel.setBorder(new BasicBorders.FieldBorder(Color.LIGHT_GRAY, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.DARK_GRAY));
        contentPane.add(gamePanel, c);
    }


    @Override
    public void startApplication() {
        SwingUtilities.invokeLater(() -> {
            try {
                createFrame();
            } catch (IOException e) {
                e.printStackTrace();
            }
            addComponentsToPanel(frame);

            initEvents();

        });
    }


    @Override
    public void onDraw(Field field) {
        try {
            gameField = new GameField(field);
        } catch (IOException e) {
            e.printStackTrace();
        }
        gamePanel.add(gameField);
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
        gameField.getLabel(click.getColumn(), click.getRow()).setIcon(bang);
        newGame.setIcon(smileSad);
    }

    @Override
    public String onIsWin() {
        gameField.repaint();
        Object[] message = new Object[]{"ВЫ ВЫИГРАЛИ! ПОЗДРАВЛЯЕМ!!!", "Введите Ваше имя без пробелов:"};
        String name = JOptionPane.showInputDialog(frame, message, "Конец игры", JOptionPane.QUESTION_MESSAGE);

        if (name == null || name.equals("")) {
            name = "Неизвестный";
        }
        return name;
    }

    void setNewGame() {
        frame.remove(timerGame);
        frame.remove(newGame);
        gamePanel.removeAll();
        timerGame = new TimerGame();
        newGame = new JButton();
        listener.setFirstClick(0);
        listener.needNewGame();
        addComponentsToPanel(frame);
        initEvents();
    }
}


