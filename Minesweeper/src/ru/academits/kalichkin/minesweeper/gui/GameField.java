package ru.academits.kalichkin.minesweeper.gui;

import ru.academits.kalichkin.minesweeper.model.Cell;
import ru.academits.kalichkin.minesweeper.model.Field;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.IOException;

class GameField extends JPanel {
    private Field field;
    private JButton[][] buttons;

    private static final int BUTTON_SIZE = 30;
    private static final int[] COLOR_OF_NUMBERS = {0x0000FF, 0x008000, 0xFF0000, 0x800000, 0x0};

    private int row;
    private int column;
    private int button;


    GameField(Field field) {
        this.field = field;
        this.buttons = new JButton[field.getFieldRow()][field.getFieldColumn()];
        this.setLayout(new GridLayout(field.getFieldRow(), field.getFieldColumn()));

        for (int i = 0; i < field.getFieldRow(); i++) {
            for (int j = 0; j < field.getFieldColumn(); j++) {
                buttons[i][j] = new JButton();
                this.add(buttons[i][j]);
                buttons[i][j].setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));
            }
        }
        setListeners();
    }


    private void setListeners() {
        for (int i = 0; i < field.getFieldRow(); i++) {
            for (int j = 0; j < field.getFieldColumn(); j++) {
                int buttonsRow = i;
                int buttonsColumn = j;
                buttons[i][j].addActionListener(e -> {
                    row = buttonsRow;
                    column = buttonsColumn;
                });
                buttons[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        button = e.getButton();
                    }
                });
            }
        }
    }

    int getRow() {
        return row;
    }

    int getColumn() {
        return column;
    }

    int getButton() {
        return button;
    }


    private void paint(int x, int y) {
        Cell cell = field.getCell(x, y);
        JButton button = buttons[x][y];
        button.setBackground(Color.lightGray);
        if (!cell.isOpen()) {
            if (cell.isFlag()) {
                button.setIcon(new ImageIcon("Flag.png"));
            }
        } else if (cell.isMine()) {
            button.setIcon(new ImageIcon("mine24.png"));
        } else if (cell.getAmountMinesNear() > 0) {
            button.setText(Integer.toString(cell.getAmountMinesNear()));
            button.setForeground(new Color(COLOR_OF_NUMBERS[cell.getAmountMinesNear() - 1]));
        }
    }
}






