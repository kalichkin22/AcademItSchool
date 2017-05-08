package ru.academits.kalichkin.view;


import ru.academits.kalichkin.common.ViewListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class WithdrawDialog {
    private JSlider slider;
    private JTextField sum;
    private JComboBox<Integer> banknote;
    private int minNominal;
    private int balance;

    WithdrawDialog(ViewListener listener) {
        minNominal = listener.needGetMinNominal();
        balance = listener.getBalance();
        slider = new JSlider(minNominal, balance);
        sum = new JTextField();
        banknote = new JComboBox<>(listener.needGetNominalBanknote());
    }

    int getBanknote() {
        return (int) banknote.getSelectedItem();
    }

    int getSum() {
        return Integer.parseInt(sum.getText());
    }


    Object[] createData() {
        slider.setValueIsAdjusting(true);
        slider.setValue(minNominal);

        sum.setFont(new Font("Helvetica", Font.PLAIN, 16));
        sum.setHorizontalAlignment(SwingConstants.CENTER);
        sum.setText(String.valueOf(slider.getValue()));

        slider.setMajorTickSpacing(5000);
        slider.setPaintTicks(true);
        slider.addChangeListener(e12 -> sum.setText(String.valueOf(slider.getValue())));

        sum.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                String typed = sum.getText();
                slider.setValue(0);
                if (!typed.matches("^\\d+$") || Integer.parseInt(sum.getText()) > balance) {
                    return;
                }
                slider.setValue(Integer.parseInt(typed));
            }
        });


        return new Object[]{"Введите сумму выдачи, кратную: " + minNominal, "Баланс: " + balance, sum, slider,
                "Выберете номинал банкноты:", banknote};
    }
}
