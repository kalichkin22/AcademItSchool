package ru.academits.kalichkin.minesweeper.gui;

import ru.academits.kalichkin.minesweeper.model.PersonWin;

import javax.swing.*;
import java.awt.*;
import java.util.List;


class HighScoresDialog extends JDialog {
    private List<PersonWin> list;

    HighScoresDialog(Frame owner, String title, List<PersonWin> list) {
        super(owner, title, true);
        this.list = list;
    }

    void createDialog() {
        this.setMinimumSize(new Dimension(300, 400));
        this.pack();
        this.setLocationRelativeTo(null);

        String[] columnNames = {"Имя", "Время"};
        PersonWin[] personWins = list.toArray(new PersonWin[list.size()]);
        String[][] data = new String[personWins.length][columnNames.length];

        for (int i = 0; i < personWins.length; i++) {
            for (int j = 0; j < columnNames.length; j++) {
                data[i][j] = personWins[i].getTime();
                data[i][0] = personWins[i].getName();
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
