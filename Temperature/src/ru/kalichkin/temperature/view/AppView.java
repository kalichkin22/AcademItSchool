package ru.kalichkin.temperature.view;

import ru.kalichkin.temperature.common.FindTemperature;
import ru.kalichkin.temperature.common.View;
import ru.kalichkin.temperature.common.ViewListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AppView implements View {
    private final ArrayList<ViewListener> listeners = new ArrayList<>();

    private final JFrame frame = new JFrame("Temperature converter");
    private final JTextField tfTemperature = new JTextField();
    private final JButton convertButton = new JButton("Convert");
    private final JTextField resultLabel = new JTextField();

    private JComboBox<String> comboBox = new JComboBox<>(FindTemperature.items);
    private JComboBox<String> comboBox2 = new JComboBox<>();
    private JLabel infoLabel = new JLabel();

    private final static boolean SHOULD_WEIGHT_X = true;


    private void createFrame() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(400, 280));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    private void initEvents() {
        convertButton.addActionListener(e -> {
            try {
                double temperature = Double.parseDouble(tfTemperature.getText());
                listeners.forEach(listener -> listener.needConvertTemperature(temperature, comboBox.getSelectedItem().toString(),
                        comboBox2.getSelectedItem().toString()));
                infoLabel.setText("");
            } catch (NumberFormatException ex) {
                infoLabel.setText("Температура должна быть цифрой.");
            }
        });

        comboBox.addActionListener(e -> {
            itemsComboBox2();
            try {
                double temperature = Double.parseDouble(tfTemperature.getText());
                listeners.forEach(listener -> listener.needConvertTemperature(temperature, comboBox.getSelectedItem().toString(),
                        comboBox2.getSelectedItem().toString()));
            } catch (NumberFormatException ex) {
                infoLabel.setText("");
                tfTemperature.setText(Integer.toString(0));
            }
        });
    }


    private void addComponentsToPanel(Container contentPane) {
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        if (SHOULD_WEIGHT_X) {
            c.weightx = 0.5;
        }

        GridBagConstraints c1 = new GridBagConstraints();
        c1.fill = GridBagConstraints.HORIZONTAL;
        c1.gridx = 0;
        c1.gridy = 1;
        contentPane.add(comboBox, c1);

        c.gridx = 0;
        c.gridy = 2;
        contentPane.add(new JLabel("Enter temperature"), c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 1;
        itemsComboBox2();
        contentPane.add(comboBox2, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 100;
        c.weightx = 0;
        c.gridx = 0;
        c.gridy = 4;
        tfTemperature.setFont(new Font("Verdana", Font.ITALIC, 25));
        tfTemperature.setHorizontalAlignment(JLabel.CENTER);
        contentPane.add(tfTemperature, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 100;
        c.weightx = 0.0;
        c.gridx = 1;
        c.gridy = 4;
        resultLabel.setFont(new Font("Verdana", Font.ITALIC, 25));
        resultLabel.setHorizontalAlignment(JLabel.CENTER);
        resultLabel.setEditable(false);
        contentPane.add(resultLabel, c);

        c.gridwidth = 2;
        c.ipady = 0;
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 5;
        contentPane.add(infoLabel, c);
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        infoLabel.setForeground(Color.RED);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.weighty = 1.0;
        c.anchor = GridBagConstraints.PAGE_END;
        c.insets = new Insets(10, 0, 0, 0);
        c.gridx = 1;
        c.gridwidth = 2;
        c.gridy = 6;
        contentPane.add(convertButton, c);
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
    public void onTemperatureConverted(double convertedTemperature) {
        resultLabel.setForeground(Color.BLACK);
        resultLabel.setFont(new Font("Verdana", Font.ITALIC, 25));
        resultLabel.setText(String.format("%.2f", convertedTemperature));
    }


    @Override
    public void addViewListener(ViewListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }


    @Override
    public void removeViewListener(ViewListener listener) {
        listeners.remove(listener);
    }


    private void itemsComboBox2() {
        comboBox2.removeAllItems();
        for (String item : FindTemperature.items) {
            if (!comboBox.getSelectedItem().equals(item)) {
                comboBox2.addItem(item);
            }
        }
    }
}
