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
    private static final int[] COLOR_OF_NUMBERS = {0x0000FF, 0x008000, 0xFF0000, 0x800000, 0x0};
    private Field field;
    private static final int BLOCK_SIZE = 30;


    GameField2(Field field) {
        this.field = field;
        this.setLayout(new GridLayout(field.getFieldRow(), field.getFieldColumn()));
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
    public void paint(Graphics g) {
        super.paint(g);
        for (int x = 0; x < field.getFieldRow(); x++) {
            for (int y = 0; y < field.getFieldColumn(); y++) {
                try {
                    this.paint(g, x, y);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void paintImage(Graphics g, Image image, int x, int y, Color color) {
        g.drawImage(image, x * BLOCK_SIZE + 1, y * BLOCK_SIZE + 1, color, null);
    }

    private void paint(Graphics g, int x, int y) throws IOException {
        Cell cell = field.getCell(x, y);
        g.setColor(Color.lightGray);
        g.drawRect(x * BLOCK_SIZE, y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);

        if (!cell.isOpen()) {
            g.setColor(Color.lightGray);
            g.fill3DRect(x * BLOCK_SIZE, y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE, true);

            if (cell.isFlag()) {
                BufferedImage img = ImageIO.read(new File("Flag.png"));
                paintImage(g, img, x, y, Color.white);
            }
        } else if (cell.isMine()) {
            BufferedImage img = ImageIO.read(new File("mine24.png"));
            BufferedImage img2 = ImageIO.read(new File("bang.png"));
            paintImage(g, cell.isMine() ? img : img2, x, y, Color.white);
        } else if (cell.getAmountMinesNear() > 0) {
            if (cell.getAmountMinesNear() == 1) {
                BufferedImage img1 = ImageIO.read(new File("1.png"));
                paintImage(g, img1, x, y, Color.white);
            } else if (cell.getAmountMinesNear() == 2) {
                BufferedImage img2 = ImageIO.read(new File("2.png"));
                paintImage(g, img2, x, y, Color.white);
            } else if (cell.getAmountMinesNear() == 3) {
                BufferedImage img3 = ImageIO.read(new File("3.png"));
                paintImage(g, img3, x, y, Color.white);
            } else if (cell.getAmountMinesNear() == 4) {
                BufferedImage img4 = ImageIO.read(new File("4.png"));
                paintImage(g, img4, x, y, Color.white);
            } else if (cell.getAmountMinesNear() == 5) {
                BufferedImage img5 = ImageIO.read(new File("5.png"));
                paintImage(g, img5, x, y, Color.white);
            } else if (cell.getAmountMinesNear() == 6) {
                BufferedImage img6 = ImageIO.read(new File("6.png"));
                paintImage(g, img6, x, y, Color.white);
            } else if (cell.getAmountMinesNear() == 7) {
                BufferedImage img7 = ImageIO.read(new File("7.png"));
                paintImage(g, img7, x, y, Color.white);
            } else if (cell.getAmountMinesNear() == 8) {
                BufferedImage img8 = ImageIO.read(new File("8.png"));
                paintImage(g, img8, x, y, Color.white);
            }
        }
    }
}



