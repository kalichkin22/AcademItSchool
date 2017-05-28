package ru.academits.kalichkin.minesweeper.gui;

import javax.swing.*;
import javax.swing.text.*;


class AboutDialog extends JFrame {
    private Style heading;
    private Style normal;
    private final String STYLE_heading = "heading";
    private final String[][] TEXT = {
            {"Сапер: правила и общие сведения", "heading"},
            {"\r\n", "normal"},
            {"Игра сапёр очень проста. ", "normal"},
            {"\r\n", "normal"},
            {"Начните с открытия одной ячейки. ", "normal"},
            {"\r\n", "normal"},
            {"Число в ячейке показывает, сколько мин скрыто вокруг данной ячейки. ", "normal"},
            {"Это число поможет понять вам, ", "normal"},
            {"где находятся безопасные ячейки, а где находятся бомбы. ", "normal"},
            {"Если рядом с открытой ячейкой есть пустая ячейка, ", "normal"},
            {"то она откроется автоматически. ", "normal"},
            {"Если вы открыли ячейку с миной, то игра проиграна. ", "normal"},
            {"Что бы пометить ячейку, в которой находится бомба, ", "normal"},
            {"нажмите её правой кнопкой мыши. ", "normal"},
            {"После того, как вы отметите все мины, ", "normal"},
            {"можно навести курсор на открытую ячейку и нажать на колесико. ", "normal"},
            {"Тогда откроются все свободные ячейки вокруг неё. ", "normal"},
            {"Если в ячейке указано число, оно показывает, ", "normal"},
            {"сколько мин скрыто в восьми ячейках вокруг данной. ", "normal"},
            {"Это число помогает понять, где находятся безопасные ячейки. ", "normal"},
            {"Игра продолжается до тех пор, ", "normal"},
            {"пока вы не откроете все не заминированные ячейки. ", "normal"},
            {"\r\n", "normal"},
            {"Удачной игры!", "heading"}};


    AboutDialog() {
        super("О игре");
        JTextPane textEditor = new JTextPane();
        createStyles(textEditor);
        loadText(textEditor);
        getContentPane().add(new JScrollPane(textEditor));
        setSize(320, 420);
        setLocationRelativeTo(null);
        setVisible(true);
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