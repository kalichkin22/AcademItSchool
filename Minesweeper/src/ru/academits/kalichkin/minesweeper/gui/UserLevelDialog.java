package ru.academits.kalichkin.minesweeper.gui;


import ru.academits.kalichkin.minesweeper.controller.Controller;

import javax.swing.*;
import java.awt.*;

class UserLevelDialog {
    private JSlider rowBox;
    private JLabel row = new JLabel();
    private JSlider columnBox;
    private JLabel column = new JLabel();

    private JTextField minesBox;

    UserLevelDialog() {
        rowBox = new JSlider();
        rowBox = new JSlider(Controller.MIN_COUNT_COLUMN, Controller.MAX_COUNT_COLUMN);
        rowBox.setValueIsAdjusting(true);

        row.setFont(new Font("Helvetica", Font.PLAIN, 16));
        row.setHorizontalAlignment(SwingConstants.CENTER);
        row.setText(String.valueOf(rowBox.getValue()));

        rowBox.setPaintTicks(true);
        rowBox.addChangeListener(e -> row.setText(String.valueOf(rowBox.getValue())));

        columnBox = new JSlider(Controller.MIN_COUNT_ROW, Controller.MAX_COUNT_ROW);
        columnBox.setValueIsAdjusting(true);

        column.setFont(new Font("Helvetica", Font.PLAIN, 16));
        column.setHorizontalAlignment(SwingConstants.CENTER);
        column.setText(String.valueOf(columnBox.getValue()));

        columnBox.setPaintTicks(true);
        columnBox.addChangeListener(e -> column.setText(String.valueOf(columnBox.getValue())));

        minesBox = new JTextField();
    }


    int getColumn() {
        return columnBox.getValue();
    }


    int getRow() {
        return rowBox.getValue();
    }

    int getMines() {
        return Integer.parseInt(minesBox.getText());
    }

    private Object[] createData() {
        return new Object[]{"Выберете колличество строк:", row, rowBox, "Выберете колличество столбцов:", column, columnBox,
                "Введите колличество мин, не больше чем:", minesBox};
    }


    boolean show(JFrame frame) {
        int option = JOptionPane.showConfirmDialog(frame, createData(), "Пользовательские настройки", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        return option == JOptionPane.OK_OPTION;
    }

}
