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

    int getBanknote() {
        return (int) banknote.getSelectedItem();
    }

    int getSlider() {
        return slider.getValue();
    }


    Object[] createData() {
        slider.setPaintTrack(true);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(e1 -> count.setText(String.valueOf(slider.getValue())));

        count.setHorizontalAlignment(SwingConstants.CENTER);
        count.setFont(new Font("Helvetica", Font.PLAIN, 16));

        return new Object[]{"Выберете номинал банкноты:", banknote, "Выберете количество банкнот:", count, slider};
    }
}
