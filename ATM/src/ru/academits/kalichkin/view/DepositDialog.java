package ru.academits.kalichkin.view;

import ru.academits.kalichkin.common.ViewListener;

import javax.swing.*;
import java.awt.*;


class DepositDialog {
    private JSlider slider;
    private JLabel count;
    private JComboBox<Integer> banknote;

    DepositDialog(ViewListener listener) {
        slider = new JSlider(1, listener.needValidCountBanknote());
        count = new JLabel(String.valueOf(slider.getValue()));
        banknote = new JComboBox<>(listener.needGetNominalBanknote());
    }

    int getBanknoteNominal() {
        return (int) banknote.getSelectedItem();
    }

    int getCountBanknote() {
        return slider.getValue();
    }


    private void createView() {
        slider.setPaintTrack(true);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(e1 -> count.setText(String.valueOf(slider.getValue())));

        count.setHorizontalAlignment(SwingConstants.CENTER);
        count.setFont(new Font("Helvetica", Font.PLAIN, 16));
    }

    Object[] getData() {
        createView();
        return new Object[]{"Выберете номинал банкноты:", banknote, "Выберете количество банкнот:", count, slider};
    }

    boolean show(JFrame frame){
        int option = JOptionPane.showConfirmDialog(frame, getData(), "Пополнение счета", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        return option == JOptionPane.OK_OPTION;
    }
}
