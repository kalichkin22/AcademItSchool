package ru.academits.kalichkin.minesweeper.gui;


import ru.academits.kalichkin.minesweeper.model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class GameField2 extends JPanel {
    private Field field;
    static final int BLOCK_SIZE = 30;


    GameField2(Field field) {
        this.field = field;
        this.setLayout(new GridLayout(field.getFieldRow(), field.getFieldColumn()));
        this.setMinimumSize(new Dimension(720, 900));
        JLabel[][] labels = new JLabel[field.getFieldRow()][field.getFieldColumn()];

        for (int i = 0; i < field.getFieldRow(); i++) {
            for (int j = 0; j < field.getFieldColumn(); j++) {
                labels[i][j] = new JLabel();
                this.add(labels[i][j]);
                labels[i][j].setPreferredSize(new Dimension(BLOCK_SIZE, BLOCK_SIZE));
            }
        }
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int x = 0; x < field.getFieldRow(); x++) {
            for (int y = 0; y < field.getFieldColumn(); y++) {
                try {
                    this.paintAll(g, x, y);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void addImage(Graphics g, Image image, int x, int y, Color color) {
        g.drawImage(image, x * BLOCK_SIZE + 1, y * BLOCK_SIZE + 1, color, null);
    }


    private void paintAll(Graphics g, int x, int y) throws IOException {
        Cell cell = field.getCell(x, y);
        g.setColor(Color.lightGray);
        g.drawRect(x * BLOCK_SIZE, y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);

        if (!cell.isOpen()) {
            g.setColor(Color.lightGray);
            g.fill3DRect(x * BLOCK_SIZE, y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE, true);

            if (cell.isFlag()) {
                BufferedImage img = ImageIO.read(new File("Flag.png"));
                addImage(g, img, x, y, Color.lightGray);
            } else if (cell.isQuestion()) {
                BufferedImage img = ImageIO.read(new File("question.png"));
                addImage(g, img, x, y, Color.lightGray);
            }
        } else if (cell.isMine() && !cell.isFlag()) {
            BufferedImage img = ImageIO.read(new File("mine.png"));
            BufferedImage img2 = ImageIO.read(new File("bang.png"));
            addImage(g, cell.isMine() ? img : img2, x, y, Color.white);

        } else if (cell.getAmountMinesNear() > 0) {
            if (cell.getAmountMinesNear() == 1) {
                BufferedImage img1 = ImageIO.read(new File("1.png"));
                addImage(g, img1, x, y, Color.white);
            } else if (cell.getAmountMinesNear() == 2) {
                BufferedImage img2 = ImageIO.read(new File("2.png"));
                addImage(g, img2, x, y, Color.white);
            } else if (cell.getAmountMinesNear() == 3) {
                BufferedImage img3 = ImageIO.read(new File("3.png"));
                addImage(g, img3, x, y, Color.white);
            } else if (cell.getAmountMinesNear() == 4) {
                BufferedImage img4 = ImageIO.read(new File("4.png"));
                addImage(g, img4, x, y, Color.white);
            } else if (cell.getAmountMinesNear() == 5) {
                BufferedImage img5 = ImageIO.read(new File("5.png"));
                addImage(g, img5, x, y, Color.white);
            } else if (cell.getAmountMinesNear() == 6) {
                BufferedImage img6 = ImageIO.read(new File("6.png"));
                addImage(g, img6, x, y, Color.white);
            } else if (cell.getAmountMinesNear() == 7) {
                BufferedImage img7 = ImageIO.read(new File("7.png"));
                addImage(g, img7, x, y, Color.white);
            } else if (cell.getAmountMinesNear() == 8) {
                BufferedImage img8 = ImageIO.read(new File("8.png"));
                addImage(g, img8, x, y, Color.white);
            }
        }

        if (cell.isFlag() && cell.isOpen()) {
            BufferedImage img = ImageIO.read(new File("Flag.png"));
            addImage(g, img, x, y, Color.white);
        }

        if (cell.isFlag() && !cell.isMine() && cell.isOpen()) {
            BufferedImage img = ImageIO.read(new File("broken_flag.png"));
            addImage(g, img, x, y, Color.white);
        }

        if (cell.isQuestion() && !cell.isMine() && cell.isOpen()) {
            BufferedImage img = ImageIO.read(new File("Question2.png"));
            addImage(g, img, x, y, Color.white);
        }

        if (cell.isQuestion() && cell.isMine() && cell.isOpen()) {

        }

    }
}



