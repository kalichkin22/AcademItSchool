package ru.academits.kalichkin.minesweeper.gui;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;


class AboutFrame extends JFrame {
    private Style heading;
    private Style normal;
    private final String STYLE_heading = "heading";
    private final String[][] TEXT = {
            {"Сапер: правила и общие сведения", "heading"},
            {System.lineSeparator(), "normal"},
            {"Игра сапёр очень проста. ", "normal"},
            {System.lineSeparator(), "normal"},
            {"Начните с открытия одной ячейки. ", "normal"},
            {System.lineSeparator(), "normal"},
            {"Число в ячейке показывает, сколько мин скрыто вокруг данной ячейки. " +
                    "Это число поможет понять вам, где находятся безопасные ячейки, а где находятся бомбы. " +
                    "Если рядом с открытой ячейкой есть пустая ячейка, то она откроется автоматически. " +
                    "Если вы открыли ячейку с миной, то игра проиграна. Что бы пометить ячейку, в которой находится бомба, " +
                    "нажмите её правой кнопкой мыши. После того, как вы отметите все мины, можно навести курсор на открытую ячейку и нажать на колесико. " +
                    "Тогда откроются все свободные ячейки вокруг неё. Если в ячейке указано число, оно показывает, сколько мин скрыто в восьми ячейках вокруг этой ячейки. " +
                    "Это число помогает понять, где находятся безопасные ячейки. Игра продолжается до тех пор, пока вы не откроете все не заминированные ячейки. ", "normal"},
            {System.lineSeparator(), "normal"},
            {"Удачной игры!", "heading"}};


    AboutFrame() {
        super("О игре");
        setMinimumSize(new Dimension(320, 420));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    void createFrame() {
        JTextPane textEditor = new JTextPane();
        createStyles(textEditor);
        loadText(textEditor);
        getContentPane().add(new JScrollPane(textEditor));
    }


    private void createStyles(JTextPane editor) {

        String STYLE_normal = "normal";
        normal = editor.addStyle(STYLE_normal, null);
        String FONT_style = "Times New Roman";
        StyleConstants.setFontFamily(normal, FONT_style);
        StyleConstants.setFontSize(normal, 16);

        heading = editor.addStyle(STYLE_heading, normal);
        StyleConstants.setFontSize(heading, 24);
        StyleConstants.setBold(heading, true);
    }


    private void loadText(JTextPane editor) {
        for (String[] aTEXT : TEXT) {
            Style style = (aTEXT[1].equals(STYLE_heading)) ? heading : normal;
            insertText(editor, aTEXT[0], style);
        }
    }


    private void insertText(JTextPane editor, String string, Style style) {
        try {
            Document doc = editor.getDocument();
            doc.insertString(doc.getLength(), string, style);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}