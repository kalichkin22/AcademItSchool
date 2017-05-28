package ru.academits.kalichkin.minesweeper.gui;


import javax.swing.*;
import java.awt.*;


public class WinDialog extends JDialog {
    private JLabel label;
    private JTextField textField;

    WinDialog(Frame owner) {
        super(owner, true);
        label = new JLabel();
        textField = new JTextField();
    }

    private Object[] createDialog() {
        label.setText("ВЫ ВЫИГРАЛИ! ПОЗДРАВЛЯЕМ!!!");
        return new Object[]{label, "Введите Ваше имя без пробелов: ", textField};
    }

    public String getName() {
        return textField.getName();
    }

    boolean show(JFrame frame) {
        int option = JOptionPane.showConfirmDialog(frame, createDialog(), "Игра окончена", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        return option == JOptionPane.OK_OPTION;
    }
}
