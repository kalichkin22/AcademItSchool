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
    private JLabel[][] labels;

    private BufferedImage mine = ImageIO.read(getClass().getResource("/res/mine.png"));
    private BufferedImage flag = ImageIO.read(getClass().getResource("/res/flag.png"));
    private BufferedImage question = ImageIO.read(getClass().getResource("/res/question.png"));
    private BufferedImage img1 = ImageIO.read(getClass().getResource("/res/1.png"));
    private BufferedImage img2 = ImageIO.read(getClass().getResource("/res/2.png"));
    private BufferedImage img3 = ImageIO.read(getClass().getResource("/res/3.png"));
    private BufferedImage img4 = ImageIO.read(getClass().getResource("/res/4.png"));
    private BufferedImage img5 = ImageIO.read(getClass().getResource("/res/5.png"));
    private BufferedImage img6 = ImageIO.read(getClass().getResource("/res/6.png"));
    private BufferedImage img7 = ImageIO.read(getClass().getResource("/res/7.png"));
    private BufferedImage img8 = ImageIO.read(getClass().getResource("/res/8.png"));
    private BufferedImage brokenFlag = ImageIO.read(getClass().getResource("/res/broken_flag.png"));
    private BufferedImage question2 = ImageIO.read(getClass().getResource("/res/Question-30.png"));
    private BufferedImage question3 = ImageIO.read(getClass().getResource("/res/Question3.png"));

    static final int BLOCK_SIZE = 30;

    GameField(Field field) throws IOException {
        this.field = field;
        this.setMinimumSize(new Dimension(650, 900));
        this.setLayout(new GridLayout(field.getFieldColumn(), field.getFieldRow()));
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
                addImage(g, flag, row, column, Color.lightGray);
            } else if (cell.isQuestion()) {
                addImage(g, question, row, column, Color.lightGray);
            }
        } else if (cell.isMine() && !cell.isFlag()) {
            addImage(g, mine, row, column, Color.white);
        } else if (cell.getAmountMinesNear() > 0) {
            if (cell.getAmountMinesNear() == 1) {
                addImage(g, img1, row, column, Color.white);
            } else if (cell.getAmountMinesNear() == 2) {
                addImage(g, img2, row, column, Color.white);
            } else if (cell.getAmountMinesNear() == 3) {
                addImage(g, img3, row, column, Color.white);
            } else if (cell.getAmountMinesNear() == 4) {
                addImage(g, img4, row, column, Color.white);
            } else if (cell.getAmountMinesNear() == 5) {
                addImage(g, img5, row, column, Color.white);
            } else if (cell.getAmountMinesNear() == 6) {
                addImage(g, img6, row, column, Color.white);
            } else if (cell.getAmountMinesNear() == 7) {
                addImage(g, img7, row, column, Color.white);
            } else if (cell.getAmountMinesNear() == 8) {
                addImage(g, img8, row, column, Color.white);
            }
        }

        if (cell.isFlag() && cell.isOpen()) {
            addImage(g, flag, row, column, Color.white);
        }

        if (cell.isFlag() && !cell.isMine() && cell.isOpen()) {
            addImage(g, brokenFlag, row, column, Color.white);
        }

        if (cell.isQuestion() && !cell.isMine() && cell.isOpen()) {
            addImage(g, question2, row, column, Color.white);
        }

        if (cell.isQuestion() && cell.isMine() && cell.isOpen()) {
            addImage(g, question3, row, column, Color.white);
        }
    }

    JLabel getLabel(int row, int column) {
        return labels[row][column];
    }

}



