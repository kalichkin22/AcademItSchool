package ru.academits.kalichkin.view;

import ru.academits.kalichkin.model.Banknotes;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class CountBanknotesDialog extends JDialog {
    private ArrayList<Banknotes> list;

    CountBanknotesDialog(Frame owner, String title, boolean modal, ArrayList<Banknotes> list) {
        super(owner, title, modal);
        this.list = list;
    }

    void createDialog() {
        this.setMinimumSize(new Dimension(400, 150));
        this.pack();
        this.setLocationRelativeTo(null);

        String[] columnNames = {"Номинал", "Количество"};

        Banknotes[] banknotes = list.toArray(new Banknotes[list.size()]);
        String[][] data = new String[banknotes.length][columnNames.length];

        for (int i = 0; i < banknotes.length; i++) {
            for (int j = 0; j < columnNames.length; j++) {
                data[i][j] = Integer.toString(banknotes[i].getCount());
                data[i][0] = Integer.toString(banknotes[i].getNominal());
            }
        }

        JTable table = new JTable(data, columnNames);
        table.setFont(new Font("TimesNewRoman", Font.PLAIN, 15));
        table.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(table);
        this.getContentPane().add(scrollPane);
        this.setVisible(true);
    }
}
