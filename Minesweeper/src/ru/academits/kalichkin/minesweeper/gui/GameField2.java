package ru.academits.kalichkin.minesweeper.gui;


import ru.academits.kalichkin.minesweeper.model.*;
import ru.academits.kalichkin.minesweeper.model.Action;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class GameField2 extends JPanel {
    private static final int BLOCK_SIZE = 30;
    private static final int[] COLOR_OF_NUMBERS = {0x0000FF, 0x008000, 0xFF0000, 0x800000, 0x0};
    private Field field;


    GameField2(Field field) {
        this.field = field;
        this.setLayout(new GridLayout(field.getFieldRow(), field.getFieldColumn()));
        JLabel[][] labels = new JLabel[field.getFieldRow()][field.getFieldColumn()];

        for (int i = 0; i < field.getFieldRow(); i++) {
            for (int j = 0; j < field.getFieldColumn(); j++) {
                labels[i][j] = new JLabel();
                this.add(labels[i][j]);
                labels[i][j].setPreferredSize(new Dimension(30, 30));
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
        g.drawImage(image, x * BLOCK_SIZE + 2, y * BLOCK_SIZE + 1, color, null);
    }

    private void paintString(Graphics g, String str, int x, int y, Color color) {
        g.setColor(color);
        g.setFont(new Font("Helvetica", Font.PLAIN, 25));
        g.drawString(str, x * BLOCK_SIZE + 8, y * BLOCK_SIZE + 26);
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
                paintImage(g, img, x, y, Color.lightGray);
            }
        } else if (cell.isMine()) {
            BufferedImage img = ImageIO.read(new File("mine24.png"));
            BufferedImage img2 = ImageIO.read(new File("bang.png"));
            paintImage(g, cell.isMine() ? img : img2, x, y, Color.white);
        } else if (cell.getAmountMinesNear() > 0) {
            paintString(g, Integer.toString(cell.getAmountMinesNear()), x, y, new Color(COLOR_OF_NUMBERS[cell.getAmountMinesNear() - 1]));
        }
    }
}

