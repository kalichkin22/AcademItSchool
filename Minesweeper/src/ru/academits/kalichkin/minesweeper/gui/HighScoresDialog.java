package ru.academits.kalichkin.minesweeper.gui;

import ru.academits.kalichkin.minesweeper.model.PersonWin;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;


class HighScoresDialog extends JDialog {
    private List<PersonWin> list;

    HighScoresDialog(JFrame owner, String title, List<PersonWin> list) {
        super(owner, title, true);
        this.list = list;
    }

    void createDialog() {
        this.setMinimumSize(new Dimension(320, 420));
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
        NumberedModel numberedModel = new NumberedModel(table.getModel());
        table.setModel(numberedModel);
        table.setFont(new Font("TimesNewRoman", Font.PLAIN, 15));
        table.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(table);
        this.getContentPane().add(scrollPane);
        this.setVisible(true);
    }


    private static class NumberedModel extends AbstractTableModel {

        private TableModel tableModel;

        NumberedModel(TableModel tableModel) {
            this.tableModel = tableModel;
        }

        @Override
        public int getRowCount() {
            return tableModel.getRowCount();
        }

        @Override
        public int getColumnCount() {
            return tableModel.getColumnCount() + 1;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if (columnIndex == 0) {
                return rowIndex + 1;
            } else {
                return tableModel.getValueAt(rowIndex, columnIndex - 1);
            }
        }

        @Override
        public String getColumnName(int column) {
            if (column == 0) {
                return "Номер";
            } else {
                return tableModel.getColumnName(column - 1);
            }
        }
    }
}
