package ru.academits.kalichkin.minesweeper.gui;


import ru.academits.kalichkin.minesweeper.model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JLabel;
import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.IOException;


public class GameField extends JPanel {
    private Field field;
    static final int BLOCK_SIZE = 30;
    private JLabel[][] labels;

    private static final String FLAG = "/res/flag.png";
    private static final String QUESTION = "/res/question.png";
    private static final String MINE = "/res/mine.png";
    private static final String NUMBER_1 = "/res/1.png";
    private static final String NUMBER_2 = "/res/2.png";
    private static final String NUMBER_3 = "/res/3.png";
    private static final String NUMBER_4 = "/res/4.png";
    private static final String NUMBER_5 = "/res/5.png";
    private static final String NUMBER_6 = "/res/6.png";
    private static final String NUMBER_7 = "/res/7.png";
    private static final String NUMBER_8 = "/res/8.png";
    private static final String BROKEN_FLAG = "/res/broken_flag.png";
    private static final String QUESTION_WITHOUT_MINE = "/res/Question-30.png";
    private static final String QUESTION_WITH_MINE = "/res/Question3.png";


    GameField(Field field) {
        this.field = field;
        this.setLayout(new GridLayout(field.getFieldColumn(), field.getFieldRow()));
        this.setMinimumSize(new Dimension(650, 900));
        labels = new JLabel[field.getFieldColumn()][field.getFieldRow()];
        for (int i = 0; i < field.getFieldColumn(); i++) {
            for (int j = 0; j < field.getFieldRow(); j++) {
                labels[i][j] = new JLabel();
                labels[i][j].setPreferredSize(new Dimension(BLOCK_SIZE, BLOCK_SIZE));
                this.add(labels[i][j]);
            }
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
                BufferedImage flag = ImageIO.read(getClass().getResource(FLAG));
                addImage(g, flag, row, column, Color.lightGray);
            } else if (cell.isQuestion()) {
                BufferedImage question = ImageIO.read(getClass().getResource(QUESTION));
                addImage(g, question, row, column, Color.lightGray);
            }
        } else if (cell.isMine() && !cell.isFlag()) {
            BufferedImage mine = ImageIO.read(getClass().getResource(MINE));
            addImage(g, mine, row, column, Color.white);
        } else if (cell.getAmountMinesNear() > 0) {
            if (cell.getAmountMinesNear() == 1) {
                BufferedImage img1 = ImageIO.read(getClass().getResource(NUMBER_1));
                addImage(g, img1, row, column, Color.white);
            } else if (cell.getAmountMinesNear() == 2) {
                BufferedImage img2 = ImageIO.read(getClass().getResource(NUMBER_2));
                addImage(g, img2, row, column, Color.white);
            } else if (cell.getAmountMinesNear() == 3) {
                BufferedImage img3 = ImageIO.read(getClass().getResource(NUMBER_3));
                addImage(g, img3, row, column, Color.white);
            } else if (cell.getAmountMinesNear() == 4) {
                BufferedImage img4 = ImageIO.read(getClass().getResource(NUMBER_4));
                addImage(g, img4, row, column, Color.white);
            } else if (cell.getAmountMinesNear() == 5) {
                BufferedImage img5 = ImageIO.read(getClass().getResource(NUMBER_5));
                addImage(g, img5, row, column, Color.white);
            } else if (cell.getAmountMinesNear() == 6) {
                BufferedImage img6 = ImageIO.read(getClass().getResource(NUMBER_6));
                addImage(g, img6, row, column, Color.white);
            } else if (cell.getAmountMinesNear() == 7) {
                BufferedImage img7 = ImageIO.read(getClass().getResource(NUMBER_7));
                addImage(g, img7, row, column, Color.white);
            } else if (cell.getAmountMinesNear() == 8) {
                BufferedImage img8 = ImageIO.read(getClass().getResource(NUMBER_8));
                addImage(g, img8, row, column, Color.white);
            }
        }

        if (cell.isFlag() && cell.isOpen()) {
            BufferedImage flag = ImageIO.read(getClass().getResource(FLAG));
            addImage(g, flag, row, column, Color.white);
        }

        if (cell.isFlag() && !cell.isMine() && cell.isOpen()) {
            BufferedImage brokenFlag = ImageIO.read(getClass().getResource(BROKEN_FLAG));
            addImage(g, brokenFlag, row, column, Color.white);
        }

        if (cell.isQuestion() && !cell.isMine() && cell.isOpen()) {
            BufferedImage question = ImageIO.read(getClass().getResource(QUESTION_WITHOUT_MINE));
            addImage(g, question, row, column, Color.white);
        }

        if (cell.isQuestion() && cell.isMine() && cell.isOpen()) {
            BufferedImage question = ImageIO.read(getClass().getResource(QUESTION_WITH_MINE));
            addImage(g, question, row, column, Color.white);
        }
    }

    JLabel getLabel(int row, int column) {
        return labels[row][column];
    }
}



