package ru.academits.kalichkin.minesweeper.gui;


import ru.academits.kalichkin.minesweeper.model.*;
import ru.academits.kalichkin.minesweeper.model.Action;

import javax.swing.*;
import javax.swing.JLabel;
import java.awt.*;

import java.io.IOException;

import static javax.imageio.ImageIO.*;

public class GameField extends JPanel {
    private Field field;
    private JLabel[][] labels;

    private Image mine = read(getClass().getResource("/res/mine.png"));
    private Image flag = read(getClass().getResource("/res/flag.png"));
    private Image question = read(getClass().getResource("/res/question.png"));
    private Image brokenFlag = read(getClass().getResource("/res/broken_flag.png"));
    private Image question2 = read(getClass().getResource("/res/Question-30.png"));
    private Image question3 = read(getClass().getResource("/res/Question3.png"));
    private Image[] imagesOfNumbers = new Image[8];

    static final int BLOCK_SIZE = 30;

    GameField(Field field) throws IOException {
        this.field = field;
        this.setLayout(new GridLayout(field.getFieldColumn(), field.getFieldRow()));
        labels = new JLabel[field.getFieldColumn()][field.getFieldRow()];
        for (int i = 0; i < field.getFieldColumn(); i++) {
            for (int j = 0; j < field.getFieldRow(); j++) {
                labels[i][j] = new JLabel();
                labels[i][j].setPreferredSize(new Dimension(BLOCK_SIZE, BLOCK_SIZE));
                this.add(labels[i][j]);
            }
        }
        for (int i = 0; i < imagesOfNumbers.length; i++) {
            imagesOfNumbers[i] = read(getClass().getResource(String.format("/res/%d.png", i + 1)));
        }
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int row = 0; row < field.getFieldRow(); row++) {
            for (int column = 0; column < field.getFieldColumn(); column++) {
                try {
                    this.paintAll(g, row, column);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void addImage(Graphics g, Image image, int row, int column, Color color) {
        int offset = 1;
        g.drawImage(image, row * BLOCK_SIZE + offset, column * BLOCK_SIZE + offset, color, null);
    }


    private void paintAll(Graphics g, int row, int column) throws IOException {
        Cell cell = field.getCell(row, column);
        g.setColor(Color.lightGray);
        g.drawRect(row * BLOCK_SIZE, column * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);

        if (!cell.isOpen()) {
            g.setColor(Color.lightGray);
            g.fill3DRect(row * BLOCK_SIZE, column * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE, true);

            if (cell.isFlag()) {
                addImage(g, flag, row, column, Color.lightGray);
            } else if (cell.isQuestion()) {
                addImage(g, question, row, column, Color.lightGray);
            }
        } else if (cell.isMine() && !cell.isFlag()) {
            addImage(g, mine, row, column, Color.white);
        } else if (cell.getAmountMinesNear() > 0) {
            addImage(g, imagesOfNumbers[cell.getAmountMinesNear() - 1], row, column, Color.white);
        }

        if (cell.isFlag() && cell.isOpen()) {
            addImage(g, flag, row, column, Color.white);
        }

        if (cell.isFlag() && !cell.isMine() && cell.isOpen()) {
            addImage(g, brokenFlag, row, column, Color.white);
        }

        if (field.isOpen()) {
            if (cell.isQuestion() && !cell.isMine() && cell.isOpen()) {
                addImage(g, question2, row, column, Color.white);
            }

            if (cell.isQuestion() && cell.isMine() && cell.isOpen()) {
                addImage(g, question3, row, column, Color.white);
            }
        }
    }

    JLabel getLabel(int row, int column) {
        return labels[row][column];
    }

}



