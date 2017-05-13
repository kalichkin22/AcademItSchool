package ru.academits.kalichkin.minesweeper.text;

import ru.academits.kalichkin.minesweeper.model.Click;
import ru.academits.kalichkin.minesweeper.model.Field;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Text {
    public static void main(String[] args) {
        Field field = new Field(9, 10);
        Scanner scanner = new Scanner(System.in);
        field.draw();
        try {
            while (true) {
                try {
                    System.out.println("Выведите кооридинаты ячейки: ");
                    System.out.println("Строка: ");
                    int row = scanner.nextInt() - 1;

                    System.out.println("Столбец: ");
                    int column = scanner.nextInt() - 1;

                    System.out.println("Если хотите открыть ячейку введите 0, если поставить флаг, то 1");
                    int button = scanner.nextInt();

                    Click click = new Click(row, column, button);
                    field.actionCell(click);
                    field.draw();

                    if (field.getCell(row, column).isMine() && !field.getCell(row, column).isFlag() && field.getCell(row, column).isOpen()) {
                        System.out.println("Вы проиграли!");
                        System.out.println("Угадано мин: " + field.getCountFlagTrue());
                        return;
                    }
                    if (field.getWin()) {
                        System.out.println("Вы выиграли! Поздравляем!");
                        return;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Выход за границы поля, попробуйте еще раз.");
                } catch (IllegalArgumentException e) {
                    System.out.println("Номер клавиши должен быть 0 или 1");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Координаты ячейки должны быть цифрой");
        }
    }
}

